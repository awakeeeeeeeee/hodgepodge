package com.awakeee.hodgepodge.designpattern.behavioral.template;

public class AnimalHtml extends HtmlTemplate {

    @Override
    protected void createContent() {
        System.out.println("create animal content!!!!");
    }
}
