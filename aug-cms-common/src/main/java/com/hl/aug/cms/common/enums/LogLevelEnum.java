package com.hl.aug.cms.common.enums;

public enum LogLevelEnum {

    ERROR("ERROR"),

    WARN("WARN"),

    INFO("INFO"),

    DEBUG("DEBUG"),

    TRACE("TRACE"),

    ALL("ALL");

    private String name;

    LogLevelEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
