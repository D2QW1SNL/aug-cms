package com.hl.aug.cms.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description: 异常枚举类
 * @Author: summer
 * @CreateDate: 2025/4/17 10:41
 * @Version: 1.0.0
 */
@AllArgsConstructor
@Getter
public enum ErrorCodeEnums {

    Success("200", "请求成功！", 0, true),

    GLOABLE_ERROR("5000", "网络异常！", 0),

    LOGIN_success("", "", 0, true),

    origin("5000", "%s", 1),


    /**
     * 默认异常 5000
     */
    YYPT_ERROR_DEFAULT("5000", "系统异常，请联系运营人员！", 0),

    /**
     * 不是跟着项目运行而产生的异常 51xx开始
     */
    GENERATOR_CODE_CONFIG_ERROR("5101", "逆向生成代码工具配置阶段异常,异常原因%s", 1),
    Mapper_GENERATOR_CODE_CONFIG_ERROR("5102", "%s#%s生成的sql异常，原因:%s", 3),
    DATASYN_ERROR("5103", "数据同步模块异常，异常信息为%s", 1),

    /**
     * 随着项目运行而产生的异常，55xx开始
     */
    ENUMS_CONFIG_ERROR("5501", "获取%s的%s的属性异常,原因%s", 3),

    VALIDATOR_CHECK_ERROR("5003", "非法入参，原因%s", 1),

    ROUTER_CONFIG_ERROR("5502", "路由寻址异常，原因%s", 1),

    yypt_file_error("5503", "文件%s异常，异常原因：%s", 2),

    json_convert_error("5004", "%s转换%s异常", 2),

    READD_ERROR("5005", "重新激活失败，原因%s", 2),

    Message("5006", "%s", 1),

    NACOS_Config_ERRor("5007", "nacos配置异常", 0),

    READ_LIMIT_ERROR("5008", "您请求过于频繁，请稍后重试！", 0),

    UN_SUPPOT_FUNCTION("5009", "该对象暂不支持该方法的调用！", 0),

    DUBBO_MATCH_ERROR("5011", "请求路径异常，请检查", 0),

    DUBBO_PARAM_MATCH_ERROR("5012", "非法入参，请检查", 0),

    DUBBO_DEPEND_ON_ERROR("5013", "远程依赖服务异常", 0),

    MVELERROR("5010,", "mvel脚本转换异常,原因:%s", 1),

    CACHE_TYPE_MATCH_ERROR("5015", "非法入参，缺少缓存类型字段", 0),

    CACHE_KEY_MATCH_ERROR("5014", "非法入参，请检验入参个数", 0),

    Mybaties_second_cache("5016", "mybatis二级缓存异常", 0),

    Mybaties_second_cache_MATCH("5017", "非法使用mybatis专属二级缓存", 0),

    ThreadLocal_error("5018", "%禁止为空", 1),

    import_entity_excel("5019", "部分sheet页记录无法导入，详情如下：%s", 1),

    YOUZAN_TOKEN_EXPIRES("6000", "有赞token过期", 0);


    /**
     * 异常编码
     */
    public String errorCode;

    /**
     * 异常信息
     */
    public String errorMsg;

    /**
     * 占位符数量
     */
    int palaceHoldCounts;

    boolean success;

    ErrorCodeEnums(String errorCode, String errorMsg, int palaceHoldCounts) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.palaceHoldCounts = palaceHoldCounts;
        this.success = false;
    }


    /**
     * @return 该方法的作用：
     * <p>
     * 根据errorCode获取异常枚举类
     * @author wsil
     * @date 2021/11/25
     * @param: errorCode
     */
    public static ErrorCodeEnums getEnumsByCode(String errorCode) {

        ErrorCodeEnums[] yyptErrorCodeEnumses = ErrorCodeEnums.values();

        for (ErrorCodeEnums yyptErrorCodeEnums : yyptErrorCodeEnumses) {
            if (yyptErrorCodeEnums.getErrorCode().equalsIgnoreCase(errorCode)) {

                return yyptErrorCodeEnums;
            }
        }
        return ErrorCodeEnums.DATASYN_ERROR;
    }

}
