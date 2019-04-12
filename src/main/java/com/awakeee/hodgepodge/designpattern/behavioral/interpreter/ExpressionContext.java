package com.awakeee.hodgepodge.designpattern.behavioral.interpreter;

import java.util.HashMap;
import java.util.Map;

//上下文类
public class ExpressionContext {

    private Map<Variable,Integer> map = new HashMap<Variable,Integer>();

    //赋值
    public void assign(Variable key ,int value){
        map.put(key,new Integer(value));
    }

    //查询变量
    public int lookUpValue(Variable key){
        return map.get(key);
    }

}
