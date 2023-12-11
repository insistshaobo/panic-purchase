package sftp;

import com.lbb.finance.domain.common.sftp.SftpFactory;
import com.lbb.finance.domain.common.sftp.SftpGenericObjectPool;
import com.lbb.finance.domain.common.sftp.SftpUtil;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/***
 * @Description: SFTP配置类
 * @Author: zhanglianhai
 * @Date: 2023/3/24
 * @Version V8.5
 **/
@Configuration
public class SftpConfig {

    /***
     * @Description: 工厂
     * @Author: zhanglianhai
     * @Date: 2023/3/24
     * @Version V8.5
     **/
    @Bean
    public SftpFactory sftpFactory() {
        return new SftpFactory();
    }

    @Bean
    @ConfigurationProperties(prefix = "sftp.pool")
    public GenericObjectPoolConfig sftpPoolConfig(){
        return new GenericObjectPoolConfig();
    }

    @Bean
    public SftpGenericObjectPool sftpPool(SftpFactory sftpFactory, GenericObjectPoolConfig sftpPoolConfig){
        return new SftpGenericObjectPool(sftpFactory, sftpPoolConfig);
    }

    @Bean
    public SftpUtil sftpUtil(SftpGenericObjectPool sftpPool){
        return new SftpUtil(sftpPool);
    }

}
