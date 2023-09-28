package com.tuling.tulingmall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tuling.tulingmall.model.UmsRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 后台用户角色表 Mapper 接口
 * </p>
 *
 * @author macro
 *2020-08-21
 */
public interface UmsRoleMapper extends BaseMapper<UmsRole> {

    /**
     * 获取用户所有角色
     */
    List<UmsRole> getRoleList(@Param("adminId") Long adminId);

}
