package com.tuling.tulingmall.ordercurr.component.rocketmq;//package com.tuling.tulingmall.component.rocketmq;

import com.tuling.tulingmall.ordercurr.domain.OrderParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.apache.skywalking.apm.toolkit.trace.Tag;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderMessageSender {

    @Value("${rocketmq.tulingmall.scheduleTopic}")
    private String scheduleTopic;

    @Value("${rocketmq.tulingmall.transGroup}")
    private String transGroup;

    @Value("${rocketmq.tulingmall.transTopic}")
    private String transTopic;

    @Value("${rocketmq.tulingmall.asyncOrderTopic}")
    private String asyncOrderTopic;

    private String TAG = "cancelOrder";
    private String TXTAG = "trans";
    private String ORDERTAG = "create-order";

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

//    /**
//     * 发送延时订单
//     * @param cancelId
//     *      秒杀订单ID orderId:promotionId 秒杀活动ID
//     * @return
//     */
//    public boolean sendTimeOutOrderMessage(String cancelId){
//        Message message = MessageBuilder.withPayload(cancelId)
//                .setHeader(RocketMQHeaders.KEYS, cancelId)
//                .build();
//        SendResult result = rocketMQTemplate.syncSend(scheduleTopic+":"+TAG,message,5000,15);
//        return SendStatus.SEND_OK == result.getSendStatus();
//    }
//
//    /**
//     * 事务消息,弱关联分布式系统柔性事务解决方案
//     */
//    public LocalTransactionState send2CartTransMessage(){
//        Message message = MessageBuilder.withPayload("").setHeader(RocketMQHeaders.KEYS, "").build();
//        TransactionSendResult transactionSendResult = rocketMQTemplate.sendMessageInTransaction(transGroup, transTopic + ":" + TXTAG, message, null);
//        log.info("事务消息-本地事务执行状态:{}" + transactionSendResult.toString()
//                ,transactionSendResult.getLocalTransactionState().name());
//        return  transactionSendResult.getLocalTransactionState();
//    }

    /**
     * 使用事务消息机制发送订单
     * @return
     */
    @Trace
    @Tag(key="CreateOrderMsg",value="生成支付二维码同时反向通知是否取消订单")
    public boolean sendCreateOrderMsg(Long orderId, Long memberId){
//        SendResult result = rocketMQTemplate.syncSend(asyncOrderTopic+":"+ORDERTAG,message);
        String destination = asyncOrderTopic+":"+ORDERTAG;
        Message<String> message = MessageBuilder.withPayload(orderId+":"+memberId)
                .build();
        //消息实际通知下游服务进行订单取消的。
        TransactionSendResult sendResult = rocketMQTemplate.sendMessageInTransaction(destination,message,orderId);
        return SendStatus.SEND_OK == sendResult.getSendStatus();
    }

//    /**
//     * 发送延时同步库存消息，60s后同步库存
//     * @param productId
//     * @param promotionId
//     * @return
//     */
//    public boolean sendStockSyncMessage(Long productId,Long promotionId){
//        Message message = MessageBuilder.withPayload(productId+":"+promotionId).build();
//        SendResult result = rocketMQTemplate.syncSend("stock-sync",message,5000,5);
//        return SendStatus.SEND_OK == result.getSendStatus();
//    }
}
