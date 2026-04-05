package com.wei.ai.domain.agent.service.armory;

import cn.bugstack.wrench.design.framework.tree.AbstractMultiThreadStrategyRouter;
import com.wei.ai.domain.agent.adapter.repository.IAgentRepository;
import com.wei.ai.domain.agent.model.entity.ArmoryCommandEntity;
import com.wei.ai.domain.agent.service.armory.factory.DefaultArmoryStrategyFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeoutException;

public abstract class AbstractArmorySupport extends AbstractMultiThreadStrategyRouter<ArmoryCommandEntity, DefaultArmoryStrategyFactory.DynamicContext,String> {

    private final Logger log = LoggerFactory.getLogger(AbstractArmorySupport.class);

    //作用：创建完对象加载到容器中
    @Resource
    protected ApplicationContext applicationContext;

    @Resource
    protected ThreadPoolExecutor threadPoolExecutor;

    @Resource
    protected IAgentRepository repository;

    @Override
    protected void multiThread(ArmoryCommandEntity armoryCommandEntity, DefaultArmoryStrategyFactory.DynamicContext dynamicContext) throws ExecutionException, InterruptedException, TimeoutException {
        //缺省的
    }

    /**
     *
     * 注册Bean
     * @param beanName bean的名称
     * @param beanClass bean的类型
     * @param beanInstance bean实例
     * @param <T> bean的类型
     */
    protected synchronized <T> void registerBean(String beanName, Class<T> beanClass, T beanInstance){
        //接口能力不足：AutowireCapableBeanFactory 接口只提供自动装配相关的方法
        //✅ 需要实现类的功能：动态注册/删除Bean定义的方法只在 DefaultListableBeanFactory 中提供
        //✅ 实际类型匹配：运行时返回的对象确实是 DefaultListableBeanFactory，所以可以安全强转
        //这是一种常见的Spring编程模式，当需要进行动态Bean注册时，必须使用 DefaultListableBeanFactory 的具体实现。
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
        //创建一个Bean定义的构建器，用于配置将要注册到Spring容器的Bean。
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(beanClass, () -> beanInstance);
        //从构建器中提取出实际的 BeanDefinition 对象。
            // （BeanDefinition = 建筑图纸 📐
            //Bean 实例 = 根据图纸建造的实际房子 🏠
            //Spring 容器 = 建筑公司，负责按照图纸建造房子 🏗️
            //Spring 容器启动时，先读取所有的 BeanDefinition（图纸），然后根据这些定义来创建和管理实际的 Bean 实例（建筑物）。）
        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getRawBeanDefinition();
        //设置Bean的作用域为单例模式
        beanDefinition.setScope(AbstractBeanDefinition.SCOPE_SINGLETON);

        if (defaultListableBeanFactory.containsBeanDefinition(beanName)){
            defaultListableBeanFactory.removeBeanDefinition(beanName);
        }

        defaultListableBeanFactory.registerBeanDefinition(beanName, beanDefinition);
        log.info("成功注册Bean:{}", beanName);

    }
}
