package com.awakeee.hodgepodge.designpattern.behavioral.template;

public class App {

    public static void main(String[] args) {

        HtmlTemplate template = new PersonHtml();
        template.createHtml();


        template = new AnimalHtml();
        template.createHtml();

    }
}
