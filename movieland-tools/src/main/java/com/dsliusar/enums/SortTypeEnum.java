package com.dsliusar.enums;

public enum SortTypeEnum {
    ASC("asc"),
    DESC("desc");

    private String sortId;

    SortTypeEnum(String sortId) {
        this.sortId = sortId;
    }

    public static SortTypeEnum validateSortType(String sortId) throws IllegalArgumentException {
        for (SortTypeEnum sortValues : SortTypeEnum.values()) {
            if (sortId.equalsIgnoreCase(sortValues.toString())) {
                return sortValues;
            }
        }
        throw new IllegalArgumentException("Not a valid sort clause");

    }

}
