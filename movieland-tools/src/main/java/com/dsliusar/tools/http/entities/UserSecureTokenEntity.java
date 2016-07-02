package com.dsliusar.tools.http.entities;

import java.time.LocalDateTime;

public class UserSecureTokenEntity {

    private Integer userId;
    private String userName;
    private Enum userRole;
    private LocalDateTime validFrom;
    private LocalDateTime validTo;

    public void setUserRole(Enum userRole) {
        this.userRole = userRole;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDateTime getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDateTime validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDateTime getValidTo() {
        return validTo;
    }

    public void setValidTo(LocalDateTime validTo) {
        this.validTo = validTo;
    }

    public Enum getUserRole() {
        return userRole;
    }

    @Override
    public String toString() {
        return "UserSecureTokenEntity{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userRole='" + userRole + '\'' +
                ", validFrom=" + validFrom +
                ", validTo=" + validTo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserSecureTokenEntity that = (UserSecureTokenEntity) o;

        if (userId != that.userId) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        if (userRole != null ? !userRole.equals(that.userRole) : that.userRole != null) return false;
        if (validFrom != null ? !validFrom.equals(that.validFrom) : that.validFrom != null) return false;
        return !(validTo != null ? !validTo.equals(that.validTo) : that.validTo != null);

    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (userRole != null ? userRole.hashCode() : 0);
        result = 31 * result + (validFrom != null ? validFrom.hashCode() : 0);
        result = 31 * result + (validTo != null ? validTo.hashCode() : 0);
        return result;
    }
}
