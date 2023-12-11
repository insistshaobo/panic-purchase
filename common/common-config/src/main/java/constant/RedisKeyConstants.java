package constant;

/**
 * @author lengfj
 * @version 1.0
 * @date 2023/10/12
 **/
public interface RedisKeyConstants {

    String COMMON_PREFIX = "lbb:finance:";

    /**
     * 支付相关
     */
    interface Pay {
        String COMMON_PREFIX = RedisKeyConstants.COMMON_PREFIX + "pay:";

        /**
         * 全局订单号
         */
        String GLOBAL_ORDER_NO = Pay.COMMON_PREFIX + "global_order_no:";

        /**
         * 转账回调消费者
         */
        String TRANSFER_CALLBACK_LOCK_PREFIX = COMMON_PREFIX + "transfer:callback:consume:";

        /**
         * 转账核心消费者
         */
        String TRANSFER_LOCK_PREFIX = COMMON_PREFIX + "transfer:consume:";

        /**
         * 预支付外部渠道支付订单号幂等
         */
        String PREPAY_OUT_ORDER_NO_PREFIX = COMMON_PREFIX + "prepay:create:";

    }
}
