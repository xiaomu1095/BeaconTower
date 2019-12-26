package com.wjf.beacontower.util;

import com.wjf.beacontower.model.PowerLineData;
import com.wjf.beacontower.model.PowerSupplyData;
import com.wjf.beacontower.model.TransformerStationData;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * @author xiaom
 * Time: 2019/12/26 16:43
 * Author: Lin.Li
 * Description:
 */
public class XMLPowerSupplyData {

    /**
     * 所有省
     */
    private String[] mPowerSupplyDatas;
    /**
     * key - 省 value - 市
     */
    private Map<String, String[]> mTransformerDatasMap = new HashMap<>();
    /**
     * key - 市 values - 区
     */
    private Map<String, String[]> mLineDatasMap = new HashMap<>();




    public String[] getmPowerSupplyDatas() {
        return mPowerSupplyDatas;
    }

    public Map<String, String[]> getmTransformerDatasMap() {
        return mTransformerDatasMap;
    }

    public Map<String, String[]> getmLineDatasMap() {
        return mLineDatasMap;
    }

    public void initProvinceDatas(InputStream input) {
        /**
         * 当前供电所的名称
         */
        String mCurrentSupplyName;
        /**
         * 当前变电站的名称
         */
        String mCurrentTransformerName;
        /**
         * 当前线路的名称
         */
        String mCurrentLineName = "";


        List<PowerSupplyData> powerSupplyList = null;
        try {
            // 创建一个解析xml的工厂对象
            SAXParserFactory spf = SAXParserFactory.newInstance();
            // 解析xml
            SAXParser parser = spf.newSAXParser();
            XmlParserHandler handler = new XmlParserHandler();
            parser.parse(input, handler);
            input.close();
            // 获取解析出来的数据
            powerSupplyList = handler.getDataList();
            if (powerSupplyList == null) {
                return;
            }
            //*/ 初始化默认选中的省、市、区
            if (!powerSupplyList.isEmpty()) {
                mCurrentSupplyName = powerSupplyList.get(0).getName();
                List<TransformerStationData> transformerList = powerSupplyList.get(0).getTransformerList();
                if (transformerList != null && !transformerList.isEmpty()) {
                    mCurrentTransformerName = transformerList.get(0).getName();
                    List<PowerLineData> lineList = transformerList.get(0).getLineList();
                    if (lineList != null && !lineList.isEmpty()) {
                        mCurrentLineName = lineList.get(0).getName();
                    }
                }
            }
            mPowerSupplyDatas = new String[powerSupplyList.size()];
            for (int i = 0; i < powerSupplyList.size(); i++) {
                // 遍历所有省的数据
                mPowerSupplyDatas[i] = powerSupplyList.get(i).getName();
                List<TransformerStationData> transformerList = powerSupplyList.get(i).getTransformerList();
                String[] transformerNames = new String[transformerList.size()];
                for (int j = 0; j < transformerList.size(); j++) {
                    // 遍历省下面的所有市的数据
                    transformerNames[j] = transformerList.get(j).getName();
                    List<PowerLineData> lineList = transformerList.get(j).getLineList();
                    String[] distrinctNameArray = new String[lineList.size()];
//                    PowerLineData[] distrinctArray = new PowerLineData[lineList.size()];
                    for (int k = 0; k < lineList.size(); k++) {
                        // 遍历市下面所有区/县的数据
                        PowerLineData districtModel = new PowerLineData(lineList.get(k).getName());
//                        distrinctArray[k] = districtModel;
                        distrinctNameArray[k] = districtModel.getName();
                    }
                    // 市-区/县的数据，保存到mDistrictDatasMap
                    mLineDatasMap.put(transformerNames[j], distrinctNameArray);
                }
                // 省-市的数据，保存到mCitisDatasMap
                mTransformerDatasMap.put(powerSupplyList.get(i).getName(), transformerNames);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
