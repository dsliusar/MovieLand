package com.dsliusar.tools.enums;

public enum SortType {
    ASC("asc"),
    DESC("desc");

    private String sortId;

    SortType(String sortId) {
        this.sortId = sortId;
    }

    public static SortType validateSortType(String sortId) throws IllegalArgumentException {
        for (SortType sortValues : SortType.values()) {
            if (sortId.equalsIgnoreCase(sortValues.toString())) {
                return sortValues;
            }
        }
        throw new IllegalArgumentException("Not a valid sort clause");

    }

}
