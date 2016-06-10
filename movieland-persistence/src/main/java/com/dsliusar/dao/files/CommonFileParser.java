package com.dsliusar.dao.files;

import org.springframework.context.annotation.Configuration;

import java.io.*;

@Configuration
public class CommonFileParser {

    private static final String PROJECT_ENCODING = "UTF-8";

    public BufferedReader readFromFile(String inFilePath) throws FileNotFoundException, UnsupportedEncodingException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(inFilePath).getFile());
        return new BufferedReader(new InputStreamReader(new FileInputStream(file), PROJECT_ENCODING));
    }

}
