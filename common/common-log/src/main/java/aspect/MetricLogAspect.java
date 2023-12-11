//package aspect;
//
//import com.cescloud.saas.archive.service.modular.common.log.annotation.MetricLog;
//import com.cescloud.saas.archive.service.modular.common.log.config.MetricConfig;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.web.context.request.RequestAttributes;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//import java.lang.reflect.Array;
//import java.time.Duration;
//import java.time.Instant;
//import java.util.Map;
//import java.util.stream.Stream;
//
//import static java.util.stream.Collectors.toMap;
//
///**
// * @author jidehcao
// * @date   2020-04-25
// */
//@Aspect
//@Slf4j
//@Order(Ordered.HIGHEST_PRECEDENCE)
//public class MetricLogAspect {
//	private static final Map<Class<?>, Object> DEFAULT_VALUES = Stream
//			.of(boolean.class, byte.class, char.class, double.class, float.class, int.class, long.class, short.class)
//			.collect(toMap(clazz -> (Class<?>) clazz, clazz -> Array.get(Array.newInstance(clazz, 1), 0)));
//
//	//注入ObjectMapper，以方便通过JSON序列化来记录方法入参和出参
//	@Autowired
//	private ObjectMapper objectMapper;
//
//	@Autowired
//	MetricConfig config;
//
//	public static <T> T getDefaultValue(Class<T> clazz) {
//		return (T) DEFAULT_VALUES.get(clazz);
//	}
//
//	//@within指示器实现对标记了Metrics注解的方法进行匹配
//	@Pointcut("within(@com.cescloud.saas.archive.service.modular.common.log.annotation.MetricLog *)")
//	public void withMetricsAnnotation() {
//	}
//	//within指示器实现了匹配那些类型上标记了@RestController注解的方法
//	@Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
//	public void controllerBean() {
//	}
//
//	@Around("controllerBean() || withMetricsAnnotation()")
//	public Object metrics(ProceedingJoinPoint pjp) throws Throwable {
//		//尝试获取当前方法的类名和方法名
//		MethodSignature signature = (MethodSignature) pjp.getSignature();
//		String name = String.format("【%s】【%s】", signature.getDeclaringType().toString(), signature.toLongString());
//
//		MetricLog metrics = signature.getMethod().getAnnotation(MetricLog.class);
//		if (metrics == null) {
//			metrics = signature.getMethod().getDeclaringClass().getAnnotation(MetricLog.class);
//		}
//		//对于Controller和Repository，我们需要初始化一个@Metrics注解出来
//		if (metrics == null) {
//			@MetricLog
//			final class c {
//			}
//			metrics = c.class.getAnnotation(MetricLog.class);
//		}
//		//对于Web项目我们可以从上下文中获取到额外的一些信息来丰富我们的日志
//		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
//		if (requestAttributes != null) {
//			HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
//			if (request != null) {
//                name += String.format("【%s】", request.getRequestURL().toString());
//            }
//		}
//		//实现的是入参的日志输出
//		if (config.isLogParameters()) {
//            log.info(String.format("【入参日志】调用 %s 的参数是：【%s】", name, objectMapper.writeValueAsString(pjp.getArgs())));
//        }
//		//实现连接点方法的执行，以及成功失败的打点，出现异常的时候还会记录日志
//		//这里我们通过日志方式暂时替代了打点的实现，标准的实现是需要把信息对接打点服务，比如Micrometer
//		Object returnValue;
//		Instant start = Instant.now();
//		try {
//			returnValue = pjp.proceed();
//			if (config.isRecordSuccessMetrics()) {
//                log.info(String.format("【成功打点】调用 %s 成功，耗时：%d ms", name, Duration.between(start, Instant.now()).toMillis()));
//            }
//		} catch (Exception ex) {
//			if (config.isRecordFailMetrics()) {
//                log.info(String.format("【失败打点】调用 %s 失败，耗时：%d ms", name, Duration.between(start, Instant.now()).toMillis()));
//            }
//			if (config.isLogException()) {
//                log.error(String.format("【异常日志】调用 %s 出现异常！", name), ex);
//            }
//
//			//如果忽略异常那么直接返回默认值
//			if (config.isIgnoreException()) {
//                returnValue = getDefaultValue(signature.getReturnType());
//            } else {
//                throw ex;
//            }
//		}
//		//实现了返回值的日志输出
//		if (config.isLogReturn()) {
//            log.info(String.format("【出参日志】调用 %s 的返回是：【%s】", name, returnValue));
//        }
//		return returnValue;
//	}
//}
