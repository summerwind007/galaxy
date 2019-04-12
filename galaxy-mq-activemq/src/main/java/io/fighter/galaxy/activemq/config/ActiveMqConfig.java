package io.fighter.galaxy.activemq.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

/**
 * @author xiasx
 * @create 2019-04-12 14:53
 **/

@Configuration
public class ActiveMqConfig {

    @Value(value = "spring.activemq.user")
    private String username;

    @Value(value = "spring.activemq.password")
    private String password;

    @Value(value = "spring.activemq.broker-url")
    private String url;


    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        return new ActiveMQConnectionFactory(username, password, url);
    }

    /**
     * Queue 方式监听器
     * @param connectionFactory
     * @return
     */
    @Bean
    public JmsListenerContainerFactory<?> jmsQueueListener(ActiveMQConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setConnectionFactory(connectionFactory);
        return bean;
    }


    /**
     * Topic 方式监听器
     * @param connectionFactory
     * @return
     */
    @Bean
    public JmsListenerContainerFactory<?> jmsTopicListener(ActiveMQConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setPubSubDomain(true);
        bean.setConnectionFactory(connectionFactory);
        return bean;
    }

}
