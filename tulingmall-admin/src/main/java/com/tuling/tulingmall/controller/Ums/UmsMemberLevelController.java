package com.tuling.tulingmall.controller.Ums;


import com.tuling.tulingmall.common.api.CommonResult;
import com.tuling.tulingmall.model.UmsMemberLevel;
import com.tuling.tulingmall.service.ums.UmsMemberLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 会员等级表 前端控制器
 * </p>
 *
 * @author XuShu
 *2022-03-09
 */
@RestController
@RequestMapping("/memberLevel")
public class UmsMemberLevelController {

    @Autowired
    UmsMemberLevelService memberLevelService;

    /**
     *   url:'/memberLevel/list',
     *     method:'get',
     *     params:{defaultStatus: 0}
     */
    @RequestMapping(value="/list",method = RequestMethod.GET)
    public CommonResult list(
            @RequestParam(value="defaultStatus",defaultValue = "0") Integer defaultStatus)
    {
        List<UmsMemberLevel> list= memberLevelService.list(defaultStatus);
        return CommonResult.success(list);
    }
}

