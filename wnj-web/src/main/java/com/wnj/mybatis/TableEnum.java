package com.wnj.mybatis;

public enum TableEnum {
    USER("user","用户表"),
    LEVEL("level","等级表"),

    ;
    private String code;
    private String desc;

    TableEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static TableEnum codeOf(String tableCode) {
        TableEnum[] values = TableEnum.values();
        for (TableEnum value : values) {
            if(value.code.equals(tableCode)){
                return value;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
