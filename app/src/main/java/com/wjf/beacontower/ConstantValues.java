package com.wjf.beacontower;

/**
 * @author xiaom
 * Time: 2019/12/25 12:56
 * Author: Lin.Li
 * Description:
 */
public interface ConstantValues {

    String COLLECTION_INFO_PATH_NAME = "info";
    String COLLECTION_INFO_TXT_NAME = "data.txt";

    String POWER_SUPPLY_XML_NAME = "power_supply_data.xml";

    String MAP_KEY_SUPPLY = "powerSupplyName";
    String MAP_KEY_TRANSFORMER = "transformer";
    String MAP_KEY_LINE_NAME = "lineName";
    String MAP_KEY_LINE_DUTY = "lineDuty";
    String MAP_KEY_CONTACT = "contactInfo";
    String BUNDLE_KEY_BASE_INFO = "main_base_info";

    String[] ITEMS_LINE_TYPE = new String[]{"干线", "支线"};
    String[] ITEMS_TOWER_TEXTURE = new String[]{"水泥杆", "钢杆"};
    String[] ITEMS_TOWER_USE = new String[]{"直线杆", "跨越杆", "耐张杆", "转角杆", "T接杆", "终端杆", "换位杆"};
    String[] ITEMS_TOWER_SETUP = new String[]{"无", "2回", "3回", "4回"};
    String[] ITEMS_TOWER_TERRAIN = new String[]{"路边", "农田", "林区", "果木", "高大树木旁"};

}
