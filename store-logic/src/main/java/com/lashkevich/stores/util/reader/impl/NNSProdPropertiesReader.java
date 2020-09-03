package com.lashkevich.stores.util.reader.impl;

import com.lashkevich.stores.exception.NNSUtilException;
import com.lashkevich.stores.util.reader.PropertiesReader;

import java.util.Properties;

public class NNSProdPropertiesReader implements PropertiesReader {
    private static final String CONFIG_PATH = "src/main/resources/db_config.properties";

    @Override
    public Properties readProperties() throws NNSUtilException {
        return readProperties(CONFIG_PATH);
    }
}
