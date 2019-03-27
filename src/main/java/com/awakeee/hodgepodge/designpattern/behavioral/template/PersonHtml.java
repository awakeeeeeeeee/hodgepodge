package com.awakeee.hodgepodge.designpattern.behavioral.template;

public class PersonHtml extends HtmlTemplate {

    @Override
    protected void createContent() {
        System.out.println("create person content!!!");
    }
}
