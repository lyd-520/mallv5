package com.tuling.tulingmall.service.ums;


import com.baomidou.mybatisplus.extension.service.IService;
import com.tuling.tulingmall.model.UmsMemberLevel;

import java.util.List;

/**
 * <p>
 * 会员等级表 服务类
 * </p>
 *
 * @author XuShu
 *2022-03-09
 */
public interface UmsMemberLevelService extends IService<UmsMemberLevel> {

    List<UmsMemberLevel> list(Integer defaultStatus);
}
