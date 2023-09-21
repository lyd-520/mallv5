package com.tuling.tulingmall.ordercurr.mapper;

import com.tuling.tulingmall.ordercurr.domain.OmsOrderDetail;
import com.tuling.tulingmall.ordercurr.dto.OmsOrderDeliveryParam;
import com.tuling.tulingmall.ordercurr.dto.OmsOrderQueryParam;
import com.tuling.tulingmall.ordercurr.model.OmsOrder;
import com.tuling.tulingmall.ordercurr.model.OmsOrderExample;
import com.tuling.tulingmall.ordercurr.model.OmsOrderItem;
import com.tuling.tulingmall.ordercurr.model.OmsOrderOperateHistory;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
public interface OmsOrderMapper{
    //TODO 订单ABA问题（重试请求导致）-版本号/重试
    long countByExample(OmsOrderExample example);

    int deleteByExample(OmsOrderExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OmsOrder record);

    int insertSelective(OmsOrder record);

    List<OmsOrder> selectByExample(OmsOrderExample example);

    OmsOrder selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OmsOrder record, @Param("example") OmsOrderExample example);

    int updateByExample(@Param("record") OmsOrder record, @Param("example") OmsOrderExample example);

    int updateByPrimaryKeySelective(OmsOrder record);

    int updateByPrimaryKey(OmsOrder record);

    /**
     * 条件查询订单
     */
    @Select("<script>" +
            "SELECT *" +
            "        FROM" +
            "        oms_order" +
            "        WHERE" +
            "        delete_status = 0" +
            "        <if test=\"queryParam.orderSn!=null and queryParam.orderSn!=''\">" +
            "            AND order_sn = #{queryParam.orderSn}" +
            "        </if>" +
            "        <if test=\"queryParam.memberId!=null and queryParam.memberId!=''\">" +
            "            AND member_id = #{queryParam.memberId}" +
            "        </if>" +
            "        <if test=\"queryParam.status!=null\">" +
            "            AND `status` = #{queryParam.status}" +
            "        </if>" +
            "        <if test=\"queryParam.sourceType!=null\">" +
            "            AND source_type = #{queryParam.sourceType}" +
            "        </if>" +
            "        <if test=\"queryParam.orderType!=null\">" +
            "            AND order_type = #{queryParam.orderType}" +
            "        </if>" +
            "        <if test=\"queryParam.createTime!=null and queryParam.createTime!=''\">" +
            "            AND create_time LIKE concat(#{queryParam.createTime},\"%\")" +
            "        </if>" +
            "        <if test=\"queryParam.receiverKeyword!=null and queryParam.receiverKeyword!=''\">" +
            "            AND (" +
            "            receiver_name LIKE concat(\"%\",#{queryParam.receiverKeyword},\"%\")" +
            "            OR receiver_phone LIKE concat(\"%\",#{queryParam.receiverKeyword},\"%\")" +
            "            )" +
            "        </if>" +
            "</script>")
    List<OmsOrder> getList(@Param("queryParam") OmsOrderQueryParam queryParam);

    /**
     * 批量发货
     */
    @Update("<script>" +
            "UPDATE oms_order" +
            "        SET" +
            "        delivery_sn = CASE id" +
            "        <foreach collection=\"list\" item=\"item\">" +
            "            WHEN #{item.orderId} THEN #{item.deliverySn}" +
            "        </foreach>" +
            "        END," +
            "        delivery_company = CASE id" +
            "        <foreach collection=\"list\" item=\"item\">" +
            "            WHEN #{item.orderId} THEN #{item.deliveryCompany}" +
            "        </foreach>" +
            "        END," +
            "        delivery_time = CASE id" +
            "        <foreach collection=\"list\" item=\"item\">" +
            "            WHEN #{item.orderId} THEN now()" +
            "        </foreach>" +
            "        END," +
            "        `status` = CASE id" +
            "        <foreach collection=\"list\" item=\"item\">" +
            "            WHEN #{item.orderId} THEN 2" +
            "        </foreach>" +
            "        END" +
            "        WHERE" +
            "        id IN" +
            "        <foreach collection=\"list\" item=\"item\" separator=\",\" open=\"(\" close=\")\">" +
            "            #{item.orderId}" +
            "        </foreach>" +
            "        AND `status` = 1" +
            "</script>")
    int delivery(@Param("list") List<OmsOrderDeliveryParam> deliveryParamList);

    /**
     * 获取订单详情
     */
    @Select(
            "SELECT o.* FROM  oms_order o  WHERE o.id = #{id}"
            )
    @Results({
            @Result(property = "id",column = "id",javaType = Long.class),
            @Result(property = "orderItemList",column = "id",javaType = List.class,
                    many = @Many(select = "com.tuling.tulingmall.ordercurr.mapper.OmsOrderMapper.getOrderItem",fetchType = FetchType.EAGER )
            ),
            @Result(property ="historyList",column = "id",javaType = List.class,
                    many = @Many(select = "com.tuling.tulingmall.ordercurr.mapper.OmsOrderMapper.getHistoryList")
            )
    })
    OmsOrderDetail getDetail(@Param("id") Long id);
    @Select(" SELECT id,product_id, product_sn, product_pic, product_name, product_brand," +
            " product_price, product_quantity,product_attr, sp1 ,sp2, sp3 " +
            " FROM oms_order_item as oi" +
            " WHERE  oi.order_id = #{orderId} ORDER BY oi.id ASC"
    )
    @Results({
            @Result(property = "productSn",column = "product_sn" ,javaType=String.class)
    })

    List<OmsOrderItem> getOrderItem(@Param("orderId") Long orderId);
    @Select(" select id,operate_man ,create_time,order_status, note" +
            " FROM oms_order_operate_history as oh" +
            " where oh.order_id= #{orderId} ORDER BY oh.create_time DESC")
    List<OmsOrderOperateHistory> getHistoryList(@Param("orderId")Long orderId);
}