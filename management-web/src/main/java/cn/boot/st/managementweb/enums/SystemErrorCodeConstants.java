package cn.boot.st.managementweb.enums;

import cn.boot.common.framework.exception.util.ErrorCode;

public interface SystemErrorCodeConstants {


    // ========== 管理员模块 1002002000 ==========
    ErrorCode ADMIN_NOT_FOUND = new ErrorCode(1002002000, "管理员不存在");
    ErrorCode ADMIN_USERNAME_EXISTS = new ErrorCode(1002002003, "账号已经存在");
    ErrorCode ADMIN_STATUS_EQUALS = new ErrorCode(1002002004, "账号已经是该状态");
    ErrorCode ADMIN_ADMIN_CAN_NOT_UPDATE = new ErrorCode(1002002008, "管理员的账号不允许变更");

    // ========== 部门模块 1002007000 ==========
    ErrorCode DEPARTMENT_NAME_DUPLICATE = new ErrorCode(1002007001, "已经存在该名字的部门");

    ErrorCode DEPARTMENT_PARENT_NOT_EXITS = new ErrorCode(1002007002,"父级部门不存在");
    ErrorCode DEPARTMENT_NOT_FOUND = new ErrorCode(1002007003, "当前部门不存在");

    ErrorCode DEPARTMENT_PARENT_ERROR = new ErrorCode(1002007005, "不能设置自己为父资源");


}
