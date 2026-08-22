package com.qa.gorest.configurations;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationManager {

    private Properties prop;
    private FileInputStream ip;

    public Properties initProp() {
        prop = new Properties();
        try {
            ip=new FileInputStream("D:\\Workspace\\IntelliJIdea\\rest-assured-JsonDrivenFramework_2026\\src\\test\\resources\\config\\config.properties");
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

}
