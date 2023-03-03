package com.example;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrintService {

    private static Logger logger = LoggerFactory.getLogger(PrintService.class);

    public void print(MessageModel model) {
        String message = StringUtils.trim(model.getMessage());
        logger.info(message);
        System.out.println(message);
    }
}
