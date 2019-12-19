package com.awakeee.hodgepodge.Reliabilityanalysis;

import org.springframework.util.CollectionUtils;

import java.util.*;


public class NodeService {

    private double res = 1.0;

    private static Map<String,Double> serialMap = new HashMap<>();

    private Double nodesNamesVal = 1.0;

    private boolean checkSerialNodes(Node node1 , Node node2){
        if(!CollectionUtils.isEmpty(node1.getNextNodes()) && node1.getNextNodes().size() == 1){
            if(!CollectionUtils.isEmpty(node2.getPreNodes()) && node2.getPreNodes().size() == 1 && !node2.getNodeName().equals("結束")){
                if(node1.getNextNodes().get(0).equals(node2)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkParallelNodes(Node node1 , Node node2){
        if(!CollectionUtils.isEmpty(node1.getPreNodes()) && node1.getPreNodes().size() == 1){
            if(!CollectionUtils.isEmpty(node2.getPreNodes()) && node2.getPreNodes().size() == 1){
                if(node1.getPreNodes().get(0).equals(node2.getPreNodes().get(0))){
                    return true;
                }
            }
        }
        return false;
    }

    //获取从当前节点向后到最后一个和当前节点串联的节点map
    private static Map<String,Double> getSerialUnion(Node node){
        //开始节点
        if(node.getCurVal() == null){
            return null;
        }

        serialMap.put(node.getNodeName(),node.getCurVal());

        if(node.getNextNodes() == null){
            return null;
        }

        if(node.getNextNodes() !=  null && node.getNextNodes().size()>1){
            return null;
        }else {
            getSerialUnion(node.getNextNodes().get(0));
        }

        return serialMap;
    }


    //将串联的各个节点组成一个单元
    private static Union getUnion(Map<String,Double> map){
        List<String> list = new ArrayList<>();
        Double val = 1.0;
        Iterator<Map.Entry<String, Double>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Double> entry = it.next();
            list.add(entry.getKey());
            val *= entry.getValue();
        }
        return new Union(list,val);
    }

    //获取从当前节点到最后一个和当前节点串联的节点union
    private static Union getUnion(Node node){
        return getUnion(getSerialUnion(node));
    }

    //将并联节点组成一个union,并联的节点组成完后必须要找它们前面的串联节点组成一个完整的单元才行
    private static Union getParalle(List<Node> nodes){
        List<Union> unions = new ArrayList<>();
        for(Node node : nodes){
            //并联上的串联节点
            Union union = getUnion(getSerialUnion(node));
            unions.add(union);

            //并联上的并联节点?????
            Union union1 = getParalle(node.getNextNodes());
            unions.add(union1);
        }
        Union union = new Union();
        List<String> list = new ArrayList<>();
        Double val = 1.0;
        for(Union u : unions){
            list.addAll(u.getNodesNames());
            val *= (1 - u.getValue());
        }
        union.setNodesNames(list);
        union.setValue(1 - val);

        //获取并联前面的节点，重新组成一个完整的单元



        return union;
    }


    // 1-(1-r1)*(1-(1-r2)*(1-r3))
    //如何将并联节点转换成串联节点？？？？？？？？

    //根据某个分支节点，获取这个分支节点所在分支中的最后一个并联节点集合
    private Union getParalleUnion(Node bNode){

        //
        if(bNode.getNextNodes() == null){

        }

        if(bNode.getNextNodes() != null && bNode.getNextNodes().size()<2){
            getParalleUnion(bNode.getNextNodes().get(0));
        }
        List<Node> nodes = bNode.getNextNodes();
        for(Node node : nodes){

        }
        return null;
    }





    //将并联的各个节点组成一个单元
    private Union getUnion(List<Map<String,Double>> maps){
//        List<String> list = new ArrayList<>();
//        Double val = 1.0;
//
//        for(Map<String,Double> map : maps){
//
//            Iterator<Map.Entry<String, Double>> it = map.entrySet().iterator();
//            while (it.hasNext()) {
//                Map.Entry<String, Double> entry = it.next();
//                list.add(entry.getKey());
//                val *= entry.getValue();
//            }
//
//        }
//
//        Iterator<Map.Entry<String, Double>> it = map.entrySet().iterator();
//        while (it.hasNext()) {
//            Map.Entry<String, Double> entry = it.next();
//            list.add(entry.getKey());
//            val *= entry.getValue();
//        }
//
//        Double val1 = 1.0;
//        Iterator<Map.Entry<String, Double>> it1 = map.entrySet().iterator();
//        while (it1.hasNext()) {
//            Map.Entry<String, Double> entry = it.next();
//            list.add(entry.getKey());
//            val1 *= entry.getValue();
//        }
//
//        Double res = 1 - (1-val)*(1-val1);
//        return new Union(list,res);
        return null;
    }

    //计算全部的单元
    private static Double calUnion(List<Union> unions){
        Double res = 1.0;
        for(Union union : unions){
            res *= union.getValue();
        }
        return res;
    }







    private Node init(){
        Node snode = new Node();
        snode.setCurVal(null);
        snode.setPreNodes(null);
        snode.setNodeName("开始");

        Node node1 = new Node();
        node1.setCurVal(0.2);
        node1.setPreNodes(Arrays.asList(snode));
        node1.setNodeName("r1");

        snode.setNextNodes(Arrays.asList(node1));

        Node node2 = new Node();
        node2.setCurVal(0.8);
        node2.setPreNodes(Arrays.asList(node1));
        node2.setNodeName("r2");

        Node node3 = new Node();
        node3.setCurVal(0.8);
        node3.setPreNodes(Arrays.asList(node1));
        node3.setNodeName("r3");

        Node enode = new Node();
        enode.setCurVal(null);
        enode.setPreNodes(Arrays.asList(node2,node3));
        enode.setNodeName("结束");
        enode.setNextNodes(null);

        node2.setNextNodes(Arrays.asList(enode));
        node3.setNextNodes(Arrays.asList(enode));



        return snode;
    }


    public static void main(String[] args) {
        NodeService nodeService = new NodeService();
        Node node = nodeService.init();
        System.out.println(nodeService.caculate(node));
    }


    private static Double caculate(Node node){
        List<Union> unions = new ArrayList<>();
        if(node.getNextNodes() !=  null && node.getNextNodes().size()>1){
            Union union = getParalle(node.getNextNodes());
            unions.add(union);
        }else {
            Union union = getUnion(getSerialUnion(node));
            unions.add(union);
        }
        return calUnion(unions);

    }

}
