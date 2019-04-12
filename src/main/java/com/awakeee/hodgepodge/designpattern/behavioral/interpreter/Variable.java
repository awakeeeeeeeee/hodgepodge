package com.awakeee.hodgepodge.designpattern.behavioral.interpreter;

//变量
public class Variable implements Expression {

    @Override
    public int interpreter(ExpressionContext expressionContext) {
        return expressionContext.lookUpValue(this);
    }
}
