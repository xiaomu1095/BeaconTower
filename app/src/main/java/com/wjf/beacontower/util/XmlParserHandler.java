package com.wjf.beacontower.util;

import com.wjf.beacontower.model.PowerLineData;
import com.wjf.beacontower.model.PowerSupplyData;
import com.wjf.beacontower.model.TransformerStationData;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaom
 * Time: 2019/12/26 16:37
 * Author: Lin.Li
 * Description:
 */
public class XmlParserHandler extends DefaultHandler {

    /**
     * 存储所有的解析对象
     */
    private List<PowerSupplyData> provinceList = new ArrayList<>();

    public XmlParserHandler() {

    }

    public List<PowerSupplyData> getDataList() {
        return provinceList;
    }

    @Override
    public void startDocument() throws SAXException {
        // 当读到第一个开始标签的时候，会触发这个方法
    }

    PowerSupplyData provinceModel = new PowerSupplyData();
    TransformerStationData cityModel = new TransformerStationData();
    PowerLineData districtModel = new PowerLineData();

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        // 当遇到开始标记的时候，调用这个方法
        if (qName.equals("power")) {
            provinceModel = new PowerSupplyData();
            provinceModel.setName(attributes.getValue(0));
            provinceModel.setTransformerList(new ArrayList<TransformerStationData>());
        } else if (qName.equals("transformer")) {
            cityModel = new TransformerStationData();
            cityModel.setName(attributes.getValue(0));
            cityModel.setLineList(new ArrayList<PowerLineData>());
        } else if (qName.equals("line")) {
            districtModel = new PowerLineData();
            districtModel.setName(attributes.getValue(0));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        // 遇到结束标记的时候，会调用这个方法
        if (qName.equals("line")) {
            cityModel.getLineList().add(districtModel);
        } else if (qName.equals("transformer")) {
            provinceModel.getTransformerList().add(cityModel);
        } else if (qName.equals("power")) {
            provinceList.add(provinceModel);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
    }
}
