package com.pe.ventas.back.utilidades.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4J2PropertiesConf {

    private static Logger LOG = LogManager.getLogger();

    public void performSomeTask() {
        LOG.debug("This is a debug message");
        LOG.info("This is an info message");
        LOG.warn("This is a warn message");
        LOG.error("This is an error message");
        LOG.fatal("This is a fatal message");
    }

}
