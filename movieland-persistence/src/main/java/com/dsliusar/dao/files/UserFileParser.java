package com.dsliusar.dao.files;

import com.dsliusar.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class UserFileParser {

    @Value("${users.filePath}")
    private String filePath;

    private Map<String, User> userMap = new HashMap<>();

    public void ParseUsersIntoList() {
        String fileLine;
        int sequenceUser = 0;
        int counter = 0;
        String userName = "";
        try {
            BufferedReader bufReader = new CommonFileParser().readFromFile(filePath);
            User user = returnUser();
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
                    user.setUserLogin(fileLine);
                }
                counter++;

                if (fileLine.isEmpty() == true) {
                    userMap.put(userName,user);
                    user = returnUser();
                    counter = 0;
                    continue;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  Map<String, User> getParsedUserMap(){
        return userMap;
    }

    private static User returnUser() {
        return new User();
    }

}
