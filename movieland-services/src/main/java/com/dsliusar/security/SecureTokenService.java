package com.dsliusar.security;

/**
 * Created by DSliusar on 17.06.2016.
 */
public interface SecureTokenService {

    String generateToken();
    String checkToken(String token);
}
