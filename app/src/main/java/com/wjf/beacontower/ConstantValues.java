package com.wjf.beacontower;

/**
 * @author xiaom
 * Time: 2019/12/25 12:56
 * Author: Lin.Li
 * Description:
 */
public abstract class ConstantValues {

    public static final String POWER_SUPPLY_XML_NAME = "power_supply_data.xml";

    public static final String MAP_KEY_SUPPLY = "powerSupplyName";
    public static final String MAP_KEY_TRANSFORMER = "transformer";
    public static final String MAP_KEY_LINE_NAME = "lineName";
    public static final String MAP_KEY_LINE_DUTY = "lineDuty";
    public static final String MAP_KEY_CONTACT = "contactInfo";

    public static final String[] ITEMS_LINE_TYPE = new String[]{"干线", "支线"};
    public static final String[] ITEMS_TOWER_TEXTURE = new String[]{"水泥杆", "钢杆"};
    public static final String[] ITEMS_TOWER_USE = new String[]{"直线杆", "跨越杆", "耐张杆", "转角杆", "T接杆", "终端杆", "换位杆"};
    public static final String[] ITEMS_TOWER_SETUP = new String[]{"无", "2回", "3回", "4回"};
    public static final String[] ITEMS_TOWER_TERRAIN = new String[]{"路边", "农田", "林区", "果木", "高大树木旁"};

}
