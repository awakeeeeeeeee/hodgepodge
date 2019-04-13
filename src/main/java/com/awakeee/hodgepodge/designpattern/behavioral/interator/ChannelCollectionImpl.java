package com.awakeee.hodgepodge.designpattern.behavioral.interator;

import java.util.List;

public class ChannelCollectionImpl implements ChannelCollection{

    private List<Channel> channelList;

    public ChannelCollectionImpl(List<Channel> channelList) {
        this.channelList = channelList;
    }

    @Override
    public void add(Channel channel) {
        channelList.add(channel);
    }

    @Override
    public void remove(Channel channel) {
        channelList.remove(channel);
    }

    @Override
    public ChannelIterator iterator() {
        return new ChannelIteratorImpl();
    }

    class ChannelIteratorImpl implements ChannelIterator{

        private int position;

        public ChannelIteratorImpl() {
            position = 0;
        }

        @Override
        public boolean hasNext() {
            int size = channelList.size();
            if(position <= size-1){
                return true;
            }
            return false;
        }

        @Override
        public Channel next() {
            Channel channel = channelList.get(position);
            position++;
            return channel;
        }
    }
}
