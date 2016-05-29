package org.ceeker.web.sbootm.common.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.stereotype.Component;

/**
 * admin server统计切面，并将信息上报给admin server
 * @author zhangxiaoling01
 * @date  2016年5月29日 上午1:42:31
 * @see
 */
@Aspect
@Component
public class ServiceMonitor {

    @Autowired
    private CounterService counterService;

    @Autowired
    private GaugeService gaugeService;

    /**
     * 统计方法调用次数
     * @param joinPoint
     */
    @Before("execution(* org.ceeker.web.sbootm.controller.*.*(..))")
    public void countServiceInvoke(JoinPoint joinPoint) {
        counterService.increment(joinPoint.getSignature() + "");
    }

    /**
     * 统计方法调用时长
     * @param pjp
     * @throws Throwable
     */
//    @Around("execution(* org.ceeker.web.sbootm.controller.*.*(..))")
//    public void latencyService(ProceedingJoinPoint pjp) throws Throwable {
//        long start = System.currentTimeMillis();
//        pjp.proceed();
//        long end = System.currentTimeMillis();
//        gaugeService.submit(pjp.getSignature().toString(), end - start);
//    }
}
