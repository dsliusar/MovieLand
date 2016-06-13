package com.dsliusar.dao.files.facade;

import com.dsliusar.Constant;
import com.dsliusar.dao.files.impl.UserFileParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class FileParserFacade {

    private Map<String, Object> mapOfFiles = new HashMap();

    @Autowired
    private UserFileParser userFileParser;

    public void fillMapOfFiles(){

        mapOfFiles.put(Constant.USER_MAP_NAME, userFileParser.ParseUsersIntoList());

    }
    public Map<String, Object> getMapOfFiles(){
        return mapOfFiles;
    }

}
