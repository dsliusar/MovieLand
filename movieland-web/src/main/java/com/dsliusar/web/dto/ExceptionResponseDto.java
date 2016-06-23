package com.dsliusar.web.dto;

public class ExceptionResponseDto {

    private String exceptionMessage;

    public ExceptionResponseDto(){}

    public ExceptionResponseDto(String message){
        this.exceptionMessage = message;
    }


    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    @Override
    public String toString() {
        return "ExceptionResponseDto{" +
                "exceptionMessage='" + exceptionMessage + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExceptionResponseDto that = (ExceptionResponseDto) o;

        return !(exceptionMessage != null ? !exceptionMessage.equals(that.exceptionMessage) : that.exceptionMessage != null);

    }

    @Override
    public int hashCode() {
        return exceptionMessage != null ? exceptionMessage.hashCode() : 0;
    }
}
