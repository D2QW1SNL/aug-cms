package com.hl.aug.cms.common.enums;

public enum WebHeaderEnum {

    TRACE_ID("traceid", "[必填]跟踪ID"),

    PLATFORM("platform", "[必填]平台-ios,android,h5,小程序"),

    SOURCE("source", "来源: 比如ios平台下打开的h5页面，不填默认是和platform一样"),

    APP_VERSION("appVersion", "[APP必填]版本号"),

    NONCE_STR("noncestr", "[必填]随机数"),

    DID("did", "[APP必填]设备信息: did"),

    IDFA("idfa", "其他设备信息: idfa"),

    OAID("oaid", "其他设备信息: oaid"),

    IMEI("imei", "其他设备信息: imei"),

    FINGER("finger", "其他设备信息: finger"),

    TOKEN("token", "登录token"),

    TIMESTAMP("timestamp", "[必填]时间戳-13位"),

    SIGN("sign", "[必填]加签结果", true),

    DEVICE("device", "详细设备信息", true),

    STI_DATA("sti-data", "客户端给数仓透传的数据", true),

    REFERER("referer", "来源页面", true),

    USER_AGENT("user-agent", "用户代理", true),

    USER_ID("uid", "用户id", true),

    SOURCE_ID("sourceId","切换用户来源id",true),

    APP_ID("appId","来源APP",true),

    USER_NAME("uname","用户名称",true)
    ;

    private String code;

    private String name;

    private boolean notSign;

    WebHeaderEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    WebHeaderEnum(String code, String name, boolean notSign) {
        this.code = code;
        this.name = name;
        this.notSign = notSign;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }


    public boolean isNotSign() {
        return notSign;
    }
}
