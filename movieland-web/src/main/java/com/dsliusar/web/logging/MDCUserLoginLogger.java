package com.dsliusar.web.logging;

import org.slf4j.MDC;

import java.util.UUID;

public class MDCUserLoginLogger {

    public static void putMDCuserLogin(String userLogin){
        String requestId = UUID.randomUUID().toString();
        MDC.put(userLogin,requestId);
    }
}
