package com.awakeee.hodgepodge.designpattern.behavioral.interpreter;

public class App {

    public static void main(String[] args) {

        ExpressionContext expressionContext = new ExpressionContext();

        Constant constant = new Constant(10);
        Variable variable = new Variable();

        expressionContext.assign(variable,20);

        Expression expression = new Add(constant,variable);
        System.out.println(expression.interpreter(expressionContext));

        Expression expression1 = new Substract(variable,constant);
        System.out.println(expression1.interpreter(expressionContext));
    }
}
