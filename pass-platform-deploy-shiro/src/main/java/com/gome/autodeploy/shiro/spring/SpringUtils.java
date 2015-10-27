/**
 * 
 */
package com.gome.autodeploy.shiro.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;


/**
 * @author bailu-ds
 *
 */
@SuppressWarnings("unchecked")
public final class SpringUtils implements BeanFactoryPostProcessor {

	private static ConfigurableListableBeanFactory beanFactory; // Spring应用上下文环境
	
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		SpringUtils.beanFactory = beanFactory;
	}
	
	public static <T> T getBean (String name) throws BeansException {
		return (T) beanFactory.getBean(name);
	}
	
	public static <T> T getBean (Class<T> clazz) throws BeansException {
		T result = (T) beanFactory.getBean(clazz);
        return result;
	}
	
	public static boolean containsBean(String name) {
		return beanFactory.containsBean(name);
	}
	
	public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
        return beanFactory.isSingleton(name);
    }
	
	public static Class<?> getType(String name) throws NoSuchBeanDefinitionException {
        return beanFactory.getType(name);
    }
	
	public static String[] getAliases(String name) throws NoSuchBeanDefinitionException {
        return beanFactory.getAliases(name);
    }

	
	
}
