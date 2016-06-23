package com.dsliusar.web.dto;

/**
 * Created by Red1 on 6/23/2016.
 */
public class TokenRequestDto {

    private String token;

    public TokenRequestDto(){}

    public TokenRequestDto(String token){
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "TokenRequestDto{" +
                "token='" + token + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TokenRequestDto that = (TokenRequestDto) o;

        return !(token != null ? !token.equals(that.token) : that.token != null);

    }

    @Override
    public int hashCode() {
        return token != null ? token.hashCode() : 0;
    }
}
