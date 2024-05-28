package com.ensah.proctorsync.helpers;

import com.ensah.proctorsync.services.user.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OperationCheck {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    public static  <T> String OperationCheck(T operationResult, String successMsg, String errorMsg) {
        boolean OperationWithSuccess = operationResult != null;

        if (OperationWithSuccess) {
            LOGGER.info(successMsg + " : {}", operationResult);
            return successMsg;
        } else {
            LOGGER.error(errorMsg + " : {}", operationResult);
            return errorMsg;
        }
    }
}

