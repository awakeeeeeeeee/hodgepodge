package com.awakeee.hodgepodge.netty1.eventmodule;

import org.springframework.util.CollectionUtils;

import java.util.LinkedList;
import java.util.Queue;

public class EventDispatcher {

    private Queue<EventChannel> channelList = new LinkedList<>();

    private static final EventSelector selector = new EventSelector();

    void registerEventChannel(EventChannel eventChannel){
        channelList.add(eventChannel);
    }

    void removeEventChannel(EventChannel eventChannel){
        channelList.remove(eventChannel);
    }

    LinkedList<EventChannel> select(){
        LinkedList<EventChannel> eventChannels = selector.select();
        if(CollectionUtils.isEmpty(eventChannels)){
            channelList.addAll(eventChannels);
        }
        return (LinkedList<EventChannel>) channelList;
    }


    public static void main(String[] args) {


        EventDispatcher dispatcher = new EventDispatcher();
        dispatcher.select();

    }

}
