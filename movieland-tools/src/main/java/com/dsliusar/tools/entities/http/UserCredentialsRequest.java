package com.dsliusar.tools.entities.http;

/**
 * Created by Оля on 20.06.2016.
 */
public class UserCredentialsRequest {

    private String userEmail;
    private String userPassword;

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {

        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public String toString() {
        return "UserCredentialsRequest{" +
                "userEmail='" + userEmail + '\'' +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserCredentialsRequest that = (UserCredentialsRequest) o;

        if (userEmail != null ? !userEmail.equals(that.userEmail) : that.userEmail != null) return false;
        return !(userPassword != null ? !userPassword.equals(that.userPassword) : that.userPassword != null);

    }

    @Override
    public int hashCode() {
        int result = userEmail != null ? userEmail.hashCode() : 0;
        result = 31 * result + (userPassword != null ? userPassword.hashCode() : 0);
        return result;
    }
}
