package com.dsliusar.util.converter;

import org.json.JSONObject;

/**
 * Created by Red1 on 6/14/2016.
 */
public class JsonToXmlConverter {

    public static String jsonToXmlConverter(String jSonString){
        JSONObject o = new JSONObject(jSonString);
        return org.json.XML.toString(o);
    }
}
