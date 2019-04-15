package com.awakeee.hodgepodge.designpattern.behavioral.visitor;

public class Book implements ItemElement {

    private int price;

    private String isbnNumber;

    public Book(int price, String isbnNumber) {
        this.price = price;
        this.isbnNumber = isbnNumber;
    }

    public int getPrice() {
        return price;
    }

    public String getIsbnNumber() {
        return isbnNumber;
    }

    //accept方法 将逻辑转换交给了visitor，从而计算分离出来
    @Override
    public int accept(ShoppingCartVisitor visitor) {
        return visitor.visit(this);
    }
}
