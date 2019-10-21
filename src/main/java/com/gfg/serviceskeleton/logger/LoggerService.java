package com.gfg.serviceskeleton.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class LoggerService {
    private Logger logger;

    public LoggerService() {
        this.logger = LogManager.getLogger("CONSOLE_JSON_APPENDER");
    }

    public Logger getLogger() {
        return logger;
    }
}
