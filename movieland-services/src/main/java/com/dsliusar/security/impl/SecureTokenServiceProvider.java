package com.dsliusar.security.impl;

import com.dsliusar.security.SecureTokenService;

/**
 * Created by DSliusar on 17.06.2016.
 */
public class SecureTokenServiceProvider implements SecureTokenService {
    @Override
    public String generateToken() {
        return null;
    }

    @Override
    public Boolean checkToken(String token) {
        return null;
    }

    @Override
    public void deleteToken(String token) {

    }
}
