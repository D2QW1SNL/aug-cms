package com.hl.aug.cms.common.constant;

public class ConfigProperties {

    public static final String LOCAL_MODE = "local";

    public static final String DEV_MODE = "dev";

    public static final String TEST_MODE = "test";

    public static final String PRO_MODE = "prd";

    public static final String PRE_MODE = "pre";

    public static final String ABROAD_MODE = "abroad";

    private static String deployMode = DEV_MODE;

    private static String serverName;

    private static String serverVersion;

    public static String getDeployMode() {
        return deployMode;
    }

    public static boolean isPreMode() {
        return PRE_MODE.equalsIgnoreCase(deployMode);
    }

    public static boolean isDevMode() {
        return DEV_MODE.equalsIgnoreCase(deployMode);
    }

    public static boolean isTestMode() {
        return TEST_MODE.equalsIgnoreCase(deployMode);
    }

    public static boolean isProMode() {
        return PRO_MODE.equalsIgnoreCase(deployMode);
    }

    public static boolean isLocalMode() {
        return LOCAL_MODE.equalsIgnoreCase(deployMode);
    }

    public static boolean isOnlineMode() {
        return PRO_MODE.equalsIgnoreCase(deployMode) || PRE_MODE.equalsIgnoreCase(deployMode);
    }

    public static boolean isAbroadOnlineMode() {
        return ABROAD_MODE.equalsIgnoreCase(deployMode);
    }

    /**
     * Setter method for property <tt>deployMode</tt>.
     *
     * @param deployMode value to be assigned to property deployMode
     */
    public static void setDeployMode(String deployMode) {
        ConfigProperties.deployMode = deployMode;
    }

    /**
     * Getter method for property <tt>serverName</tt>.
     *
     * @return property value of serverName
     */
    public static String getServerName() {
        return serverName;
    }

    /**
     * Setter method for property <tt>serverName</tt>.
     *
     * @param serverName value to be assigned to property serverName
     */
    public static void setServerName(String serverName) {
        ConfigProperties.serverName = serverName;
    }

    /**
     * Getter method for property <tt>serverVersion</tt>.
     *
     * @return property value of serverVersion
     */
    public static String getServerVersion() {
        return serverVersion;
    }

    /**
     * Setter method for property <tt>serverVersion</tt>.
     *
     * @param serverVersion value to be assigned to property serverVersion
     */
    public static void setServerVersion(String serverVersion) {
        ConfigProperties.serverVersion = serverVersion;
    }


}