package cn.boot.common.framework.constant;

import cn.boot.common.framework.exception.util.ErrorCode;

public interface SystemErrorCodeConstants {

    /**
     * OAUTH2 模块
     */
    ErrorCode OAUTH2_UNKNOWN = new ErrorCode(1001001000, "未知错误"); // 预留
    /**
     * 预留 1001001001 ~ 1001001099 错误码，方便前端
     */
    ErrorCode OAUTH2_ACCESS_TOKEN_NOT_FOUND = new ErrorCode(1001001001, "访问令牌不存在");
    ErrorCode OAUTH2_ACCESS_TOKEN_TOKEN_EXPIRED = new ErrorCode(1001001002, "访问令牌已过期");
    ErrorCode OAUTH2_REFRESH_TOKEN_NOT_FOUND = new ErrorCode(1001001005, "刷新令牌不存在");
    ErrorCode OAUTH_REFRESH_TOKEN_EXPIRED = new ErrorCode(1001001006, "访问令牌已过期");
    /**
     * 其它 1001001100 开始
     */
    ErrorCode OAUTH_USER_TYPE_ERROR = new ErrorCode(1001001101, "用户类型并不正确");

    /**
     * ========== 管理员模块 1001002000 ==========
     */
    ErrorCode ADMIN_NOT_FOUND = new ErrorCode(1001002000, "管理员不存在");
    ErrorCode ADMIN_PASSWORD_ERROR = new ErrorCode(1001002001, "密码不正确");
    ErrorCode ADMIN_IS_DISABLE = new ErrorCode(100100002, "账号被禁用");
    ErrorCode ADMIN_USERNAME_EXISTS = new ErrorCode(1001002003, "账号已经存在");
    ErrorCode ADMIN_STATUS_EQUALS = new ErrorCode(1001002004, "账号已经是该状态");
    ErrorCode ADMIN_ADMIN_CAN_NOT_UPDATE = new ErrorCode(1001002008, "管理员的账号不允许变更");
    ErrorCode ADMIN_ASSIGN_ROLE_NOT_EXISTS = new ErrorCode(1001002006, "分配员工角色时，有角色不存在");
    ErrorCode ADMIN_USERNAME_NOT_EXISTS = new ErrorCode(1001002009, "账号不存在");

    /**
     *  部门模块 1001003000
     */
    ErrorCode DEPARTMENT_NAME_DUPLICATE = new ErrorCode(1001003001, "已经存在该名字的部门");

    ErrorCode DEPARTMENT_PARENT_NOT_EXITS = new ErrorCode(1001003002, "父级部门不存在");
    ErrorCode DEPARTMENT_NOT_FOUND = new ErrorCode(1001003003, "当前部门不存在");

    ErrorCode DEPARTMENT_PARENT_ERROR = new ErrorCode(1001003005, "不能设置自己为父资源");


    /**
     * 资源模块 1001004000
     */
    ErrorCode RESOURCE_NAME_DUPLICATE = new ErrorCode(1001004000, "已经存在该名字的资源");
    ErrorCode RESOURCE_PARENT_NOT_EXISTS = new ErrorCode(1001004001, "父资源不存在");
    ErrorCode RESOURCE_PARENT_ERROR = new ErrorCode(1001004002, "不能设置自己为父资源");
    ErrorCode RESOURCE_NOT_EXISTS = new ErrorCode(1001004003, "资源不存在");
    ErrorCode RESOURCE_EXISTS_CHILDREN = new ErrorCode(1001004004, "存在子资源，无法删除");
    ErrorCode RESOURCE_PARENT_NOT_MENU = new ErrorCode(1001004005, "父资源的类型必须是菜单");

    /**
     * 角色模块 1001005000
     */
    ErrorCode ROLE_NOT_EXISTS = new ErrorCode(1001005000, "角色不存在");
    ErrorCode ROLE_NAME_DUPLICATE = new ErrorCode(1001005001, "已经存在名为【{}}】的角色");
    ErrorCode ROLE_CODE_DUPLICATE = new ErrorCode(1001005002, "已经存在编码为【{}}】的角色");
    ErrorCode ROLE_CAN_NOT_UPDATE_SYSTEM_TYPE_ROLE = new ErrorCode(1001005004, "不能修改类型为系统内置的角色");
    ErrorCode ROLE_CAN_NOT_DELETE_SYSTEM_TYPE_ROLE = new ErrorCode(1001005005, "不能删除类型为系统内置的角色");

    /**
     *  权限模块 1001006000
     */
    ErrorCode PERMISSION_DEMO_PERMISSION_DENY = new ErrorCode(1001006000, "演示账号，暂不允许写操作");
    ErrorCode PERMISSION_ROLE_ASSIGN_RESOURCE_NOT_EXISTS = new ErrorCode(1001006001, "分配角色资源时，有资源不存在");


    /**
     * 服务降级 1001007000
     */
    ErrorCode SERVICE_DEGRADATION = new ErrorCode(1001007000, "服务降级，请稍后重试");


    // ========== 商品模块 1-003-000-000 ==========


}
