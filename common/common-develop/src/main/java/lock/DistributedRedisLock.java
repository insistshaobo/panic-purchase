package lock;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Author zhouda
 * @Date 2023-06-25
 * @Version V1.0
 */
@Component
@Slf4j
public class DistributedRedisLock {
    @Autowired
    private RedissonClient redissonClient;
    private static final String DEFAULT_LOCK_NAME = "redisLock_";

    /**
     * 加锁
     * @param lockName
     * @return
     */
    public boolean lock(String lockName) {
        //声明key对象
        String key = DEFAULT_LOCK_NAME + lockName;
        //获取锁对象
        RLock mylock = redissonClient.getLock(key);
        //加锁，并且设置锁过期时间3秒，防止死锁的产生  uuid+threadId
        mylock.lock();
        //加锁成功
        return true;
    }

    public boolean lock(String lockName, long timeout) {
        checkRedissonClient();
        RLock lock = getLock(lockName);
        try {
            if(timeout != -1){
                // timeout:超时时间   TimeUnit.SECONDS：单位
                lock.lock(timeout, TimeUnit.SECONDS);
            }else{
                lock.lock();
            }
            log.debug(" get lock success ,lockKey:{}", lockName);
            return true;
        } catch (Exception e) {
            log.error(" get lock fail,lockKey:{}, cause:{} ",
                    lockName, e.getMessage());
            return false;
        }
    }


    private void checkRedissonClient() {
        if (null == redissonClient) {
            log.error(" redissonClient is null ,please check redis instance ! ");
            throw new RuntimeException("redissonClient is null ,please check redis instance !");
        }
        if (redissonClient.isShutdown()) {
            log.error(" Redisson instance has been shut down !!!");
            throw new RuntimeException("Redisson instance has been shut down !!!");
        }
    }



    /**
     * 解锁
     * @param lockName
     */
    public void unlock(String lockName){
        checkRedissonClient();
        try {
            RLock lock = getLock(lockName);
            if(lock.isLocked() && lock.isHeldByCurrentThread()){
                lock.unlock();
                log.debug("key：{}，unlock success",lockName);
            }else{
                log.debug("key：{}，没有加锁或者不是当前线程加的锁 ",lockName);
            }
        }catch (Exception e){
            log.error("key：{}，unlock error,reason:{}",lockName,e.getMessage());
        }
    }


    private RLock getLock(String lockName) {
        String key = DEFAULT_LOCK_NAME + lockName;
        return redissonClient.getLock(key);
    }


    /**
     * 可中断锁
     * @param lockName 锁名称
     * @param waitTimeout  等待时长
     * @param unit 时间单位
     * @return
     */
    public boolean tryLock(String lockName, long waitTimeout, TimeUnit unit) {
        checkRedissonClient();
        RLock lock = getLock(lockName);
        try {
            boolean res = lock.tryLock(waitTimeout,unit);
            if (!res) {
                log.debug(" get lock fail ,lockKey:{}", lockName);
                return false;
            }
            log.debug(" get lock success ,lockKey:{}", lockName);
            return true;
        } catch (Exception e) {
            log.error(" get lock fail,lockKey:{}, cause:{} ",
                    lockName, e.getMessage());
            return false;
        }
    }
}
