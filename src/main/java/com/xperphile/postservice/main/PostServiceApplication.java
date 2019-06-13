package com.xperphile.postservice.main;

import com.xperphile.postservice.listener.PostServiceListener;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = {"com.xperphile.postservice.controller", "com.xperphile.postservice.dto", "com.xperphile.postservice.service", "com.xperphile.postservice.listener"})
@EntityScan(basePackages = {"com.xperphile.postservice.dao"} )
@EnableJpaRepositories(basePackages = {"com.xperphile.postservice.repository"})
@SpringBootApplication
public class PostServiceApplication {
	
	@Autowired
	private static Environment environment;

	public static String POST_MESSAGE_QUEUE;
	public static String POST_MESSAGE_EXCHANGE;
	public static String POST_MESSAGE_RECEIVER_METHOD;

	static {
		POST_MESSAGE_QUEUE = environment.getProperty("postmessage.queue");
		POST_MESSAGE_EXCHANGE = environment.getProperty("postmessage.exchange");
		POST_MESSAGE_RECEIVER_METHOD = environment.getProperty("postmessage.receivermethod");
	}

	@Bean
	Queue queue() {
		return new Queue(POST_MESSAGE_QUEUE, false);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange(POST_MESSAGE_EXCHANGE);
	}

	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(POST_MESSAGE_QUEUE);
	}

	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
											 MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(POST_MESSAGE_QUEUE);
		container.setMessageListener(listenerAdapter);
		return container;
	}

	@Bean
	MessageListenerAdapter listenerAdapter(PostServiceListener receiver) {
		return new MessageListenerAdapter(receiver, POST_MESSAGE_RECEIVER_METHOD);
	}


	public static void main(String[] args) {
		SpringApplication.run(PostServiceApplication.class, args);
	}

}
