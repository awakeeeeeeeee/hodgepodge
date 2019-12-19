package com.awakeee.hodgepodge.Reliabilityanalysis;

import java.util.ArrayList;
import java.util.List;

public class Node {

    private List<Node> preNodes;

    private List<Node> nextNodes;

    private Double curVal;

    private String nodeName;

    public List<Node> getPreNodes() {
        return preNodes;
    }

    public void setPreNodes(List<Node> preNodes) {
        this.preNodes = preNodes;
    }

    public List<Node> getNextNodes() {
        return nextNodes;
    }

    public void setNextNodes(List<Node> nextNodes) {
        this.nextNodes = nextNodes;
    }

    public Double getCurVal() {
        return curVal;
    }

    public void setCurVal(Double curVal) {
        this.curVal = curVal;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }


    public static void main(String[] args) {

        Node node = new Node();
        Node node1 = new Node();
        List<Node> list = new ArrayList<>();
        list.add(node1);
        node.setNextNodes(list);


        System.out.println(node.getNextNodes().get(0).equals(node1));


    }
}
