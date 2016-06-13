package com.dsliusar.dao.files.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Component
public class CommonFileParser implements ResourceLoaderAware {

    @Autowired
    private ResourceLoader resourceLoader;

    private static final String PROJECT_ENCODING = "UTF-8";

    public BufferedReader readFromFile(String inFilePath) throws IOException {
        Resource resourceFilePath = resourceLoader.getResource(inFilePath);
        System.out.println(resourceFilePath);
        InputStream in = resourceFilePath.getInputStream();
        return new BufferedReader(new InputStreamReader(in, PROJECT_ENCODING));
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;

    }
}
