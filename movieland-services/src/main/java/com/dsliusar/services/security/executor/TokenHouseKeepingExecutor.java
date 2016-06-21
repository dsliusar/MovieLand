package com.dsliusar.services.security.executor;

import com.dsliusar.services.security.SecureTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class TokenHouseKeepingExecutor {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private SecureTokenService secureTokenServiceProvider;

    public void performTokenHousekeeping(){
        LOGGER.info("Performing Housekeeping of Tokens");
        int tokensDeleted = secureTokenServiceProvider.performHousekeeping();
        LOGGER.info("Tokens housekeeping finished, next count deleted = {}", tokensDeleted);
    }
}
