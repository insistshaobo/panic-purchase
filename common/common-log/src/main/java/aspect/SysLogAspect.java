//
//
//package aspect;
//
//import com.cescloud.saas.archive.service.modular.common.log.annotation.SysLog;
//import com.cescloud.saas.archive.service.modular.common.log.event.SysLogEvent;
//import com.cescloud.saas.archive.service.modular.common.log.util.SysLogUtils;
//import lombok.AllArgsConstructor;
//import lombok.SneakyThrows;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.context.ApplicationEventPublisher;
//
///**
// * 操作日志使用spring event异步入库
// */
//@Slf4j
//@Aspect
//@AllArgsConstructor
//public class SysLogAspect {
//	private final ApplicationEventPublisher publisher;
//
//	@SneakyThrows
//	@Around("@annotation(sysLog)")
//	public Object around(ProceedingJoinPoint point, SysLog sysLog) {
//		String strClassName = point.getTarget().getClass().getName();
//		String strMethodName = point.getSignature().getName();
//		log.debug("[类名]:{},[方法]:{}", strClassName, strMethodName);
//
//		com.cescloud.saas.archive.api.modular.log.entity.SysLog logVo = SysLogUtils.getSysLog();
//		logVo.setTitle(sysLog.value());
//		// 发送异步日志事件
//		Long startTime = System.currentTimeMillis();
//		Object obj = point.proceed();
//		Long endTime = System.currentTimeMillis();
//		logVo.setTime(endTime - startTime);
//		publisher.publishEvent(new SysLogEvent(logVo));
//		return obj;
//	}
//
//}
