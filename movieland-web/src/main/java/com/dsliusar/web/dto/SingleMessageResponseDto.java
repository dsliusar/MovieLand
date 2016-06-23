package com.dsliusar.web.dto;

public class SingleMessageResponseDto {

    private String message;

    public SingleMessageResponseDto(){}

    public SingleMessageResponseDto(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "SingleMessageResponseDto{" +
                "message='" + message + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SingleMessageResponseDto that = (SingleMessageResponseDto) o;

        return !(message != null ? !message.equals(that.message) : that.message != null);

    }

    @Override
    public int hashCode() {
        return message != null ? message.hashCode() : 0;
    }
}
