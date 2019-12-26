package com.wjf.beacontower.model;

/**
 * @author xiaom
 * Time: 2019/12/26 16:31
 * Author: Lin.Li
 * Description:
 */
public class PowerLineData {
    private String name;

    public PowerLineData() {
    }

    public PowerLineData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PowerLineData that = (PowerLineData) o;

        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "PowerLineData{" +
                "name='" + name + '\'' +
                '}';
    }
}
