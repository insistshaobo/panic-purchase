package swagger;

import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;


/**
 * @ClassName SwaggerConfiguration
 * @Description
 * @Author wangjm
 * @Date 2023/4/27
 * @Version 1.0
 */

@Configuration
public class SwaggerConfiguration {
    @Bean
    public OperationCustomizer customGlobalHeaders() {

        return (Operation operation, HandlerMethod handlerMethod) -> {

            Parameter head1 = new Parameter()
                    .in(ParameterIn.HEADER.toString())
                    .schema(new StringSchema())
                    .name("token")
                    .description("请求头参数token")
                    .required(true);

            Parameter head2 = new Parameter()
                    .in(ParameterIn.HEADER.toString())
                    .schema(new StringSchema())
                    .name("accountToken")
                    .description("请求头参数accountToken")
                    .required(false);

            Parameter head3 = new Parameter()
                    .in(ParameterIn.HEADER.toString())
                    .schema(new StringSchema())
                    .name("source")
                    .description("请求头参数source")
                    .required(true);


            operation.addParametersItem(head1);
            operation.addParametersItem(head2);
            operation.addParametersItem(head3);

            return operation;
        };
    }
}
