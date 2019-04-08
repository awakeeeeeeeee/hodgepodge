package com.awakeee.hodgepodge.dataStructure.stack;

public interface Iterator<E> {

    Object next();

    boolean hasNext();

    void remove();
}
