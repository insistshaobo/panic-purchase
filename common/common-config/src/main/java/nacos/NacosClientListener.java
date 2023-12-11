package nacos;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.NacosServiceManager;
import com.alibaba.cloud.nacos.registry.NacosRegistration;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingMaintainService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * @author lengfj
 * @version 1.0
 * @date 2023/7/17
 **/
@Slf4j
@Component
public class NacosClientListener implements ApplicationRunner {

    @Autowired
    private NacosRegistration nacosRegistration;
    @Autowired
    private NacosServiceManager nacosServiceManager;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 获取当前实例信息
        String os = System.getProperty("os.name");
        //默认 windows & mac 为本地开发环境
        if (os != null && (!os.toLowerCase().startsWith("win")
                && !os.toLowerCase().startsWith("mac"))) {
            return;
        }
        NacosDiscoveryProperties nacosDiscoveryProperties = nacosRegistration.getNacosDiscoveryProperties();
        CompletableFuture.runAsync(() -> {
            try {
                // 启动后自动下线服务
                Thread.sleep(1000L);
                offlineInstance(nacosDiscoveryProperties);
                Thread.sleep(3000L);
                offlineInstance(nacosDiscoveryProperties);
                Thread.sleep(5000L);
                offlineInstance(nacosDiscoveryProperties);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 下线服务
     * @param nacosDiscoveryProperties
     * @throws NacosException
     */
    public void offlineInstance(NacosDiscoveryProperties nacosDiscoveryProperties) throws NacosException {

        Instance instance = new Instance();
        instance.setIp(nacosDiscoveryProperties.getIp());
        instance.setPort(nacosDiscoveryProperties.getPort());
        instance.setClusterName(nacosDiscoveryProperties.getClusterName());
        instance.setServiceName(nacosDiscoveryProperties.getService());
        instance.setEnabled(false);
        log.info("当前环境本地debug,进行下线处理：{}", instance);
        NamingMaintainService namingMaintainService = nacosServiceManager.getNamingMaintainService(nacosDiscoveryProperties.getNacosProperties());
        namingMaintainService.updateInstance(nacosDiscoveryProperties.getService(), instance);
    }
}
