package com.luxoft.demoshopwithspring.aop;

import com.luxoft.demoshopwithspring.domain.ProductDto;
import com.luxoft.demoshopwithspring.messages.ProductEvent;
import com.luxoft.demoshopwithspring.messages.ProductProducer;
import com.luxoft.demoshopwithspring.utils.mapper.ProductMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class EventAspect {
    private ProductProducer productProducer;
    private ProductMapper productMapper;

    public EventAspect(ProductProducer productProducer, ProductMapper productMapper) {
        this.productProducer = productProducer;
        this.productMapper = productMapper;
    }

    @Pointcut("execution(public * com.luxoft.demoshopwithspring.service.ProductService.*(..) ) && args(productDto)")
    public void productServicesPublicMethods(ProductDto productDto){}

    @Before("productServicesPublicMethods(productDto)")
    public void sendEvent(JoinPoint joinPoint, ProductDto productDto){
        String methodName = joinPoint.getSignature().getName();

        String eventString = methodName.split("(?=\\p{Upper})")[0].toUpperCase();

        ProductEvent.Event event;
        try {
            event = Enum.valueOf(ProductEvent.Event.class, eventString);
        } catch (Exception e) {
            event = ProductEvent.Event.UNKNOWN;
        }

        sendEvent(productDto, event);
    }


    private void sendEvent(ProductDto productDto, ProductEvent.Event eventType) {
        ProductEvent event = productMapper.domainToEvent(productDto);
        event.setEvent(eventType);
        productProducer.sendProductEventMessage(event);
    }
}
