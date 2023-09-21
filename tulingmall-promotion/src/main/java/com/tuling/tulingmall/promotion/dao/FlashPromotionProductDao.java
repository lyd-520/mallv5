package com.tuling.tulingmall.promotion.dao;

import com.tuling.tulingmall.promotion.domain.FlashPromotionParam;
import com.tuling.tulingmall.promotion.domain.FlashPromotionProduct;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 首页秒杀活动内容管理自定义Dao
 */
public interface FlashPromotionProductDao {

    /**
     * 查找所有的秒杀活动商品
     * @return
     */
    FlashPromotionParam getFlashPromotion(@Param("spid") Long spid,@Param("status") Integer status);
    @Select("select id from dbase.sms_flash_promotion " +
            "where status = #{status}"
    )
    List<Long> getAllPromotionIds(@Param("status") int status);
}
