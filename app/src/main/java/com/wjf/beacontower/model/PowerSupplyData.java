package com.wjf.beacontower.model;

import java.util.List;

/**
 * @author xiaom
 * Time: 2019/12/26 16:30
 * Author: Lin.Li
 * Description:
 */
public class PowerSupplyData {
    private String name;
    private List<TransformerStationData> transformerList;

    public PowerSupplyData() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TransformerStationData> getTransformerList() {
        return transformerList;
    }

    public void setTransformerList(List<TransformerStationData> transformerList) {
        this.transformerList = transformerList;
    }
}
