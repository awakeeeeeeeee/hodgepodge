package com.awakeee.hodgepodge.designpattern.behavioral.template;

public abstract class HtmlTemplate {



    void createHtml(){

        createHead();
        createBody();
        createContent();
    }

    protected abstract void createContent();

    private void createBody() {
        System.out.println("create Body!!!");
    }

    private void createHead() {
        System.out.println("create header!!!");
    }
}
