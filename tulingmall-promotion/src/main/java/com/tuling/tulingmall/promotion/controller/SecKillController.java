package com.tuling.tulingmall.promotion.controller;

import com.tuling.tulingmall.common.api.CommonResult;
import com.tuling.tulingmall.promotion.dao.MiaoShaStockDao;
import com.tuling.tulingmall.promotion.domain.FlashPromotionProduct;
import com.tuling.tulingmall.promotion.service.HomePromotionService;
import com.tuling.tulingmall.promotion.service.ISecKillStaticHtmlService;
import com.tuling.tulingmall.promotion.service.impl.ConstantPromotion;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 秒杀管理Controller
 */
@Slf4j
@Controller
@Api(tags = "SecKillController", description = "秒杀管理")
@RequestMapping("/seckill")
public class SecKillController {

    @Autowired
    private HomePromotionService homePromotionService;
    @Autowired
    private MiaoShaStockDao miaoShaStockDao;
    @Autowired
    private ISecKillStaticHtmlService secKillStaticHtmlService;

    /*获得秒杀内容*/
    @ApiOperation("获取首页所有秒杀产品")
    @RequestMapping(value = "/getHomeSecKillProductList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<List<FlashPromotionProduct>>> getHomeSecKillProductList(
            @RequestParam(required = false,defaultValue = "-1") long secKillId,
            @RequestParam(required = false,defaultValue = "1") int status){
        List<List<FlashPromotionProduct>> result=new ArrayList<>();
        if(secKillId==-1)    result = homePromotionService.secKillContent(status);
        else  {
            List<List<FlashPromotionProduct>> list = new ArrayList<>();
            List<FlashPromotionProduct> flashPromotionProducts = homePromotionService.secKillContent(secKillId, status);
            if(!CollectionUtils.isEmpty(flashPromotionProducts)){
                    list.add(flashPromotionProducts);
                    result=list;
            }
        }
        return CommonResult.success(result);
    }

    @ApiOperation("开启秒杀")
    @RequestMapping(value = "/openSecKill", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Integer> turnOnSecKill(@RequestParam long secKillId){
        int result = homePromotionService.turnOnSecKill(secKillId, ConstantPromotion.SECKILL_OPEN);
        try {
            if(secKillStaticHtmlService.deployHtml(secKillId) == ConstantPromotion.STATIC_HTML_FAILURE){
                return CommonResult.failed("发布秒杀静态页失败！");
            }
        } catch (Exception e) {
            log.error("发布秒杀静态产品页异常：",e);
            homePromotionService.turnOnSecKill(secKillId, ConstantPromotion.SECKILL_CLOSE);
            return CommonResult.failed(e.getMessage());
        }
        return CommonResult.success(result);
    }

    /*扣减库存 要防止库存超卖*/
    @ApiOperation("扣减库存")
    @RequestMapping(value = "/descStock", method = RequestMethod.GET)
    @ResponseBody
    public Integer descStock(@RequestParam("id") Long flashPromotionRelationId,
                             @RequestParam("stock") Integer stock){
        return miaoShaStockDao.descStock(flashPromotionRelationId,stock);
    }

    @ApiOperation("增加库存")
    @RequestMapping(value = "/incStock", method = RequestMethod.GET)
    @ResponseBody
    public Integer incStock(@RequestParam("id") Long flashPromotionRelationId,
                             @RequestParam("stock") Integer stock){
        return miaoShaStockDao.incStock(flashPromotionRelationId,stock);
    }

    @ApiOperation("查询库存")
    @RequestMapping(value = "/getStock", method = RequestMethod.GET)
    @ResponseBody
    public Integer getStock(@RequestParam("id") Long flashPromotionRelationId){
        return miaoShaStockDao.getStock(flashPromotionRelationId);
    }

}
