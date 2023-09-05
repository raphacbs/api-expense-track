package com.coelho.brasileiro.expensetrack.flow;


import com.coelho.brasileiro.expensetrack.context.Context;

public abstract class AFlowBuilder<T> {
    protected Flow flow;

     protected void run() {
        if (this.flow != null) {
            this.flow.run();
        } else {
            throw new IllegalStateException("FlowFactory has not been created. Call create() method first.");
        }
    }

    public Flow build() {
        if (this.flow != null) {
            return this.flow;
        } else {
            throw new IllegalStateException("FlowFactory has not been created. Call create() method first.");
        }
    }
    public abstract T create(Context context);
}
