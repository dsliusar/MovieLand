package com.dsliusar.persistence.dao.files.impl;

import com.dsliusar.persistence.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class UserFileParser {

    @Value("${users.filePath}")
    private String filePath;

    @Autowired
    private CommonFileParser commonFileParser;

    private Map<String, User> userMap = new HashMap<>();

    public Map<String, User> parseUsersIntoList() {
        String fileLine;
        int sequenceUser = 0;
        int counter = 0;
        String userName = "";
        try {
            BufferedReader bufReader = commonFileParser.readFromFile(filePath);
            User user = new User();
            while ((fileLine = bufReader.readLine()) != null) {
                if (counter == 0) {
                    sequenceUser++;
                    user.setUserId(sequenceUser);
                    counter++;
                }
               if (counter == 1) {
                   user.setUserName(fileLine);
                   userName = user.getUserName();
                } else if (counter == 2) {
                    user.setUserEmail(fileLine);
                } else if (counter == 3) {
                    user.setUserPassword(fileLine);
                } else if (counter == 4) {
                   user.setUserRole(fileLine);
               }
                counter++;

                if (fileLine.isEmpty() == true) {
                    userMap.put(userName,user);
                    user = new User();
                    counter = 0;
                    continue;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userMap;
    }

    public  Map<String, User> getParsedUserMap(){
        return userMap;
    }

}
