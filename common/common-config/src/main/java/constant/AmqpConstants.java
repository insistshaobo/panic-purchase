package constant;

/**
 * 异步 mq 常量类
 * @author lengfj
 * @version 1.0
 * @date 2023/9/27
 **/
public interface AmqpConstants {

    /**
     * 预支付相关
     */
    interface Prepay {
        String EXCHANGE_NAME = "lbb.finance.pay";
        String QUEUE_NAME_CREATE = "lbb.finance.pay.create";
    }

    /**
     * 支付回调相关
     */
    interface PayCallback {
        String EXCHANGE_NAME = "lbb.finance.pay.callback";
        String QUEUE_NAME_CALLBACK = "lbb.finance.pay.callback";
    }
    /*
    消息重试
     */
    interface PayMessageRetry {
        String PAY_MESSAGE_DELAY_EXCHANGE = "lbb.finance.dead.message.exchange";
        String PAY_MESSAGE_DELAY_QUEUE= "lbb.finance.message.delay.queue";
        String PAY_CREATE_DEAD_LETTER_EXCHANGE = "lbb.finance.dead.message.exchange";
        String PAY_CREATE_DEAD_LETTER_QUEUE = "lbb.finance.dead.message.queue";
        String PAY_CREATE_DEAD_LETTER_QUEUE_ROUTING_KEY = "lbb.finance.dead.message.queue.rouking.key";
        String PAY_MESSAGE_DELAY_QUEUE_ROUTING_KEY = "lbb.finance.message.delay.queue.rouking.key";

    }

    /**
     * 监控相关
     */
    interface Monitor {
        String EXCHANGE_NAME = "lbb.finance.monitor";
        String QUEUE_NAME_FLOW_RECORD = "lbb.finance.monitor.flow.record";
    }
}
