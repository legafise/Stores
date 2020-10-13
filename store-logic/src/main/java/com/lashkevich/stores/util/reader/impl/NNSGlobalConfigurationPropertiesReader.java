package com.lashkevich.stores.util.reader.impl;

import com.lashkevich.stores.exception.NNSUtilException;
import com.lashkevich.stores.util.reader.PropertiesReader;

import java.util.Properties;

public class NNSGlobalConfigurationPropertiesReader implements PropertiesReader {
    private static final String CONFIG_PATH = "src/main/resources/global_db_config.properties";
    private static final String STANDARD_IMG_URL_KEY = "standard.image.url";

    @Override
    public Properties readProperties() throws NNSUtilException {
        return readProperties(CONFIG_PATH);
    }

    public String readStandardImgURL() throws NNSUtilException {
        return readProperties().getProperty(STANDARD_IMG_URL_KEY);
    }
}
