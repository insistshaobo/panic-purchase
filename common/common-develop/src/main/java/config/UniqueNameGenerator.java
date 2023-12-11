package config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.stereotype.Component;

/**
 * @ClassName UniqueNameGenerator
 * @Description 区分不同报名相同类，因为springboot默认是不区分的
 * @Author wangjm
 * @Date 2023/5/5
 * @Version 1.0
 */

@Component
public class UniqueNameGenerator extends AnnotationBeanNameGenerator {
    @Override
    public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
        //全限定类名
        String beanName = definition.getBeanClassName();
        return beanName;
    }
}
