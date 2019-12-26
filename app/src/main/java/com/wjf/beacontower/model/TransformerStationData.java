package com.wjf.beacontower.model;

import java.util.List;

/**
 * @author xiaom
 * Time: 2019/12/26 16:30
 * Author: Lin.Li
 * Description:
 */
public class TransformerStationData {

    private String name;
    private List<PowerLineData> lineList;

    public TransformerStationData() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PowerLineData> getLineList() {
        return lineList;
    }

    public void setLineList(List<PowerLineData> lineList) {
        this.lineList = lineList;
    }

}
