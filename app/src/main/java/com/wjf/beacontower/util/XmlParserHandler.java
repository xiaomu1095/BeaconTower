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

    private String NODE_POWER = "power";
    private String NODE_TRANSFORMER = "transformer";
    private String NODE_LINE = "line";
    /**
     * 存储所有的解析对象
     */
    private List<PowerSupplyData> provinceList = new ArrayList<>();

    private PowerSupplyData provinceModel = new PowerSupplyData();
    private TransformerStationData cityModel = new TransformerStationData();
    private PowerLineData districtModel = new PowerLineData();



    public XmlParserHandler() {

    }

    public List<PowerSupplyData> getDataList() {
        return provinceList;
    }

    @Override
    public void startDocument() throws SAXException {
        // 当读到第一个开始标签的时候，会触发这个方法
        super.startDocument();
    }

    /**
     * 文档解析结束后调用
     */
    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        // 当遇到开始标记的时候，调用这个方法
        if (qName.equals(NODE_POWER)) {
            provinceModel = new PowerSupplyData();
            provinceModel.setName(attributes.getValue(0));
            provinceModel.setTransformerList(new ArrayList<TransformerStationData>());
        } else if (qName.equals(NODE_TRANSFORMER)) {
            cityModel = new TransformerStationData();
            cityModel.setName(attributes.getValue(0));
            cityModel.setLineList(new ArrayList<PowerLineData>());
        } else if (qName.equals(NODE_LINE)) {
            districtModel = new PowerLineData();
            districtModel.setName(attributes.getValue(0));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        super.endElement(uri, localName, qName);
        // 遇到结束标记的时候，会调用这个方法
        if (qName.equals(NODE_LINE)) {
            cityModel.getLineList().add(districtModel);
        } else if (qName.equals(NODE_TRANSFORMER)) {
            provinceModel.getTransformerList().add(cityModel);
        } else if (qName.equals(NODE_POWER)) {
            provinceList.add(provinceModel);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
    }
}
