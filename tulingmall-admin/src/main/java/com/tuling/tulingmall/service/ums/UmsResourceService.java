package com.tuling.tulingmall.service.ums;




import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tuling.tulingmall.dto.ResourceRoleDTO;
import com.tuling.tulingmall.model.UmsResource;

import java.util.List;

/**
 * 后台资源管理Service
 *2022/2/2.
 */
public interface UmsResourceService extends IService<UmsResource> {
    /**
     * 添加资源
     */
    boolean create(UmsResource umsResource);

    /**
     * 修改资源
     */
    boolean update(Long id, UmsResource umsResource);

    /**
     * 删除资源
     */
    boolean delete(Long id);

    /**
     * 分页查询资源
     */
    Page<UmsResource> list(Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum);

    List<ResourceRoleDTO> getAllResourceRole();
}
