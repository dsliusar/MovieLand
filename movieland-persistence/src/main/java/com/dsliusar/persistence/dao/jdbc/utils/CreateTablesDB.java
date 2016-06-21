package com.dsliusar.persistence.dao.jdbc.utils;

import com.ibatis.common.jdbc.ScriptRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;

/**
 * Created by Red1 on 6/13/2016.
 */

@Repository
public class CreateTablesDB implements ResourceLoaderAware {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private String createTablesSQLPath;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private DataSource dataSource;

    public void initialize() {
        LOGGER.info("Initializing sql file to Create tables in DB");
        long startTime = System.currentTimeMillis();
        Connection conn;
        Resource sqlCreateFilePath = resourceLoader.getResource(createTablesSQLPath);
        try {
            conn = dataSource.getConnection();
            ScriptRunner scriptRunner = new ScriptRunner(conn, false, false);
            InputStream in = sqlCreateFilePath.getInputStream();
            Reader reader = new BufferedReader(
                    new InputStreamReader(in,"UTF-8"));

            scriptRunner.runScript(reader);
        } catch (Exception e) {
            LOGGER.error("Error happened", e);
            throw new RuntimeException(e);

        }
        LOGGER.info("Tables were successfully created, it took {}", System.currentTimeMillis() - startTime);
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}


