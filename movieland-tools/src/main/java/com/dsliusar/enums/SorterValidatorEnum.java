package com.dsliusar.enums;

/**
 * Created by Red1 on 6/17/2016.
 */
public enum SorterValidatorEnum {
    ASC("asc"),
    DESC("desc");

    private String sortId;

    SorterValidatorEnum(String sortId) {
        this.sortId = sortId;
    }

    public static String vaidateOrderClause(String sortId) throws IllegalArgumentException {
        for (SorterValidatorEnum sortValues : SorterValidatorEnum.values()) {
            if(sortId == null){return sortId;}
            if (sortId.equalsIgnoreCase(sortValues.toString())) {
                throw new IllegalArgumentException("Illegal arguments received");
            }
        }
        return sortId;
    }

}
