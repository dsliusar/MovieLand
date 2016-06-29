package com.dsliusar.services.cache.invalidator;

import com.dsliusar.tools.annotations.CacheInvalidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.util.HashMap;
import java.util.Map;

public class CacheInvalidatorBeanPostProcessor implements BeanPostProcessor {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private Map<String, Class> beanMap = new HashMap<>();
    private CacheInvalidatorController invalidatorController = new CacheInvalidatorController();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        if (beanClass.isAnnotationPresent(CacheInvalidator.class)){
            beanMap.put(beanName, beanClass);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class beanClass = beanMap.get(beanName);
        if(beanClass != null){
//            return Proxy.newProxyInstance(beanClass.getClassLoader(),
//                    beanClass.getInterfaces(), new InvocationHandler() {
//                        @Override
//                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                         if (invalidatorController.isEnableCountryCacheInvalidator() ||
//                                    invalidatorController.isEnableGenreCacheInvalidator() ||
//                                    invalidatorController.isEnableReviewCacheInvalidator()){
//
//                            LOGGER.info("Invalidating cache for {}", beanClass.getName());
//                            long startTime = System.currentTimeMillis();
//                            //Object invoke = method.invoke(bean, args);
//                            LOGGER.info("Cache updated for {}, it took {}", beanClass.getName(), System.currentTimeMillis() - startTime);
//                            return null;
//
//                        } else {
//                           // return method.invoke(bean,args);
//                             return null;
//                            }
//                        }
//                    });
            System.out.println("Hi");
        }
        return null;
    }
}
