package com.awakeee.hodgepodge.designpattern.behavioral.interpreter;

//实现加法
public class Add implements Expression {

    private Expression var1;

    private Expression var2;

    public Add(Expression var1, Expression var2) {
        this.var1 = var1;
        this.var2 = var2;
    }

    @Override
    public int interpreter(ExpressionContext expressionContext) {
        return var1.interpreter(expressionContext) + var2.interpreter(expressionContext);
    }
}
