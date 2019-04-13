package com.awakeee.hodgepodge.designpattern.behavioral.interator;

import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {

        Channel channel = new Channel(1.0,"1");
        Channel channe2 = new Channel(2.0,"2");
        Channel channe3 = new Channel(3.0,"3");
        Channel channe4 = new Channel(4.0,"4");
        Channel channe5 = new Channel(5.0,"5");

        List<Channel> list = new ArrayList<>();


        ChannelCollection channelCollection = new ChannelCollectionImpl(list);
        channelCollection.add(channel);
        channelCollection.add(channe2);
        channelCollection.add(channe3);
        channelCollection.add(channe4);
        channelCollection.add(channe5);

        ChannelIterator iterator = channelCollection.iterator();
        while (iterator.hasNext()){
            Channel channel1 = iterator.next();
            System.out.println(channel1.toString());
        }


    }
}
