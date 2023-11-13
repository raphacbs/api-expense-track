package com.coelho.brasileiro.expensetrack.flow;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.handle.Handler;

public class Flow {
    private Handler action;
    private Context context;

    protected Flow() {

    }

    public void setAction(Handler action) {
        this.action = action;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    protected Handler getAction() {
        return this.action;
    }

    public void run() {
        if (action != null) {
            action.handle(context);
        }
    }
}
