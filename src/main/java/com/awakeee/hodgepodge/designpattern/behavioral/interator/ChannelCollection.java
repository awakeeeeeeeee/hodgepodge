package com.awakeee.hodgepodge.designpattern.behavioral.interator;

public interface ChannelCollection {

    void add(Channel channel);

    void remove(Channel channel);

    ChannelIterator iterator();
}
