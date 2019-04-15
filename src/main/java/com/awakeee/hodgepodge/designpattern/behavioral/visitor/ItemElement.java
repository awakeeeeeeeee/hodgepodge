package com.awakeee.hodgepodge.designpattern.behavioral.visitor;

public interface ItemElement {

    public int accept(ShoppingCartVisitor visitor);
}
