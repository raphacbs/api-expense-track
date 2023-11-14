package com.coelho.brasileiro.expensetrack.flow;


import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.handle.Handler;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.function.BiFunction;

@Component
public class FlowFactory {
    private Handler action;
    private Context context;

    private Flow flow;

    private final ApplicationContext applicationContext;

    private boolean isStarted;

    public FlowFactory(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        isStarted = false;
    }

    public FlowFactory start() {
        this.flow = new Flow();
        isStarted = true;
        this.action = null;
        return this;
    }

    public FlowFactory addAction(Class<? extends Handler> handlerClass) {
        verifyStart();
        Handler handler = null;
        try {
            handler = applicationContext.getBean(handlerClass);
            handler = (Handler) handler.clone();
        } catch (BeansException e) {
            try {
                handler = handlerClass.getDeclaredConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException ex) {
                throw new RuntimeException(ex);
            }
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        if (action == null) {
            action = handler;
        } else {
            Handler lastHandler = action;
            Handler nextHandler = lastHandler.getNextHandler();
            while (nextHandler != null) {
                lastHandler = nextHandler;
                nextHandler = nextHandler.getNextHandler();
            }
            lastHandler.setNext(handler);
        }
        return this;
    }

    public FlowFactory addAction(Handler handlerInstance) {
        verifyStart();
        if (action == null) {
            action = handlerInstance;
        } else {
            Handler lastHandler = action;
            Handler nextHandler = lastHandler.getNextHandler();
            while (nextHandler != null) {
                lastHandler = nextHandler;
                nextHandler = nextHandler.getNextHandler();
            }
            lastHandler.setNext(handlerInstance);
        }

        return this;
    }

    public FlowFactory addFLowBuilder(Class<? extends AFlowBuilder> builderClass) {
        verifyStart();
        AFlowBuilder flowBuilder = applicationContext.getBean(builderClass);
        flowBuilder.create(context);
        Handler handler = flowBuilder.flow.getAction();
        if (handler != null) {
            addAction(handler);
        }
        return this;
    }

    public FlowFactory context(Context context) {
        this.context = context;
        return this;
    }

    public Flow build() {
        verifyStart();
        flow.setAction(action);
        flow.setContext(context);
        return flow;
    }

    private void verifyStart() {
        if (!isStarted) {
            throw new IllegalStateException("You must call start() before adding actions.");
        }
    }
}
