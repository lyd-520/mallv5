package com.tuling.tulingmall.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuling.tulingmall.mapper.UmsMemberLevelMapper;
import com.tuling.tulingmall.model.UmsMemberLevel;
import com.tuling.tulingmall.service.ums.UmsMemberLevelService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 会员等级表 服务实现类
 * </p>
 *
 * @author XuShu
 *2022-03-09
 */
@Service
public class UmsMemberLevelServiceImpl extends ServiceImpl<UmsMemberLevelMapper, UmsMemberLevel> implements UmsMemberLevelService {

    @Override
    public List<UmsMemberLevel> list(Integer defaultStatus) {
        QueryWrapper<UmsMemberLevel> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UmsMemberLevel::getDefaultStatus,defaultStatus);
        return this.list(queryWrapper);
    }
}
