package com.lashkevich.stores.util.reader;

import com.lashkevich.stores.exception.NNSUtilException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@FunctionalInterface
public interface PropertiesReader {
    String URL_KEY = "url";
    String COMMIT_STATUS_KEY = "commit.status";

    Properties readProperties() throws NNSUtilException;

    default Properties readProperties(String path) throws NNSUtilException {
        Properties properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new NNSUtilException(e);
        }

        return properties;
    }

    default String readUrl() throws NNSUtilException {
        return readProperties().getProperty(URL_KEY);
    }

    default boolean readCommitStatus() throws NNSUtilException {
        return Boolean.parseBoolean(readProperties().getProperty(COMMIT_STATUS_KEY));
    }
}
