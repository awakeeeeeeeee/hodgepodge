package com.awakeee.hodgepodge.designpattern.behavioral.interpreter;

//常量
public class Constant implements Expression {

    private int i;

    public Constant(int i) {
        this.i = i;
    }

    @Override
    public int interpreter(ExpressionContext expressionContext) {
        return i;
    }
}
