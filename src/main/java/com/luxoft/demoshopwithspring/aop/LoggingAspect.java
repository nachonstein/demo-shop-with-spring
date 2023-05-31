package com.luxoft.demoshopwithspring.aop;

import com.luxoft.demoshopwithspring.domain.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
    @Pointcut("execution(public * com.luxoft.demoshopwithspring.service.ProductService.*(..) ) && args(productDto)")
    public void productServicesPublicMethods(ProductDto productDto){}
    @Pointcut("execution(public * com.luxoft.demoshopwithspring.controller.ProductController.*(..) ) && args(productDto)")
    public void productControllerPublicMethods(ProductDto productDto){}

    @Around("productServicesPublicMethods(productDto) ")
    public void logServices(ProceedingJoinPoint joinPoint, ProductDto productDto) throws Throwable {
        logTrace(joinPoint, productDto);
    }
    @Around("productControllerPublicMethods(productDto) ")
    public void logControllers(ProceedingJoinPoint joinPoint, ProductDto productDto) throws Throwable {
        logTrace(joinPoint, productDto);
    }

    private void logTrace(ProceedingJoinPoint joinPoint, ProductDto productDto) throws Throwable {
        log.info("Before {} {}",joinPoint.getSignature().toShortString(), productDto);

        Object result = joinPoint.proceed();

        log.info("After {} {}",joinPoint.getSignature().toShortString(), result);
    }
}
