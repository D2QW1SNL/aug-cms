package com.hl.aug.cms.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Map;

public class MimeHeadersDTO {

    /**
     * 存储待签名header
     */
    private Map<String, String> signHeaderMap;

    /**
     * [必填]跟踪ID
     */
    @JSONField(name = "traceid")
    private String traceId;

    /**
     * [必填]平台-ios,android,h5,小程序
     */
    private String platform;

    /**
     * 来源: 比如ios平台下打开的h5页面，不填默认是和platform一样
     */
    private String source;

    /**
     * [APP必填]版本号
     */
    private String appVersion;

    /**
     * [必填]随机数
     */
    @JSONField(name = "noncestr")
    private String nonceStr;

    /**
     * [APP必填]设备信息: did
     */
    private String did;

    /**
     * 其他设备信息: idfa
     */
    private String idfa;

    /**
     * 其他设备信息: imei
     */
    private String imei;

    /**
     * 其他设备信息: oaid
     */
    private String oaid;

    /**
     * 其他设备信息: finger
     */
    private String finger;

    /**
     * 登录token
     */
    private String token;

    /**
     * [必填]时间戳-13位
     */
    private String timestamp;

    /**
     * [必填]加签结果
     */
    private String sign;

    /**
     * 详细设备信息
     */
    private String device;

    /**
     * 客户端给数仓透传的数据
     */
    @JSONField(name = "sti-data")
    private String stiData;

    private String uid;

    /**
     * 切换账号来源主账号id
     */
    private String sourceId;

    /**
     * 应用appID 夺冠体育=1 魔力桥国内版=2 魔力桥国外版=3
     */
    private Integer appId;

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Map<String, String> getSignHeaderMap() {
        return signHeaderMap;
    }

    public void setSignHeaderMap(Map<String, String> signHeaderMap) {
        this.signHeaderMap = signHeaderMap;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getIdfa() {
        return idfa;
    }

    public void setIdfa(String idfa) {
        this.idfa = idfa;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getOaid() {
        return oaid;
    }

    public void setOaid(String oaid) {
        this.oaid = oaid;
    }

    public String getFinger() {
        return finger;
    }

    public void setFinger(String finger) {
        this.finger = finger;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getStiData() {
        return stiData;
    }

    public void setStiData(String stiData) {
        this.stiData = stiData;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public Integer getAppId() {
        return appId;
    }
}
