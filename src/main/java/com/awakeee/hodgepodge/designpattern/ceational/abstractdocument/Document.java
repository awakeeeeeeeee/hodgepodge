package com.awakeee.hodgepodge.designpattern.ceational.abstractdocument;

import javax.xml.xpath.XPathFactory;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

public interface Document {

    Object put(String key,Object value);

    Object get(String key);

    <T> Stream<T> children(
            String key,
            Function<Map<String, Object>, T> constructor
    );

    public static void main(String[] args) {

        XPathFactory a  = XPathFactory.newInstance();


    }
}
