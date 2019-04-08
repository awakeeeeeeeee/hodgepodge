package com.awakeee.hodgepodge.datastructure.stack;

public interface Iterator<E> {

    Object next();

    boolean hasNext();

    void remove();
}
