//import com.cescloud.saas.archive.service.modular.common.log.aspect.MetricLogAspect;
//import com.cescloud.saas.archive.service.modular.common.log.config.MetricConfig;
//import lombok.AllArgsConstructor;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@AllArgsConstructor
//@ConditionalOnProperty(
//		prefix = "cescloud.metric",
//		value = {"enabled"},
//		havingValue = "true",
//		matchIfMissing = false
//)
//@EnableConfigurationProperties({MetricConfig.class})
//@ConditionalOnWebApplication
//public class MetricLogAutoConfiguration {
//	@Bean
//	public MetricLogAspect metricLogAspect(){
//		return new MetricLogAspect();
//	}
//}
