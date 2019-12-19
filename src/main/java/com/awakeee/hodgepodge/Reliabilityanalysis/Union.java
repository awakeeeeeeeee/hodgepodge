package com.awakeee.hodgepodge.Reliabilityanalysis;

import java.util.List;

public class Union {

    private List<String> nodesNames;

    private Double value;

    public List<String> getNodesNames() {
        return nodesNames;
    }

    public void setNodesNames(List<String> nodesNames) {
        this.nodesNames = nodesNames;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Union(List<String> nodesNames, Double value) {
        this.nodesNames = nodesNames;
        this.value = value;
    }

    public Union() {
    }
}
