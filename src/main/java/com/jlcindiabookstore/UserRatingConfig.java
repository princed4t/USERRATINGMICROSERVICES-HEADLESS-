package com.jlcindiabookstore;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;

import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UserRatingConfig implements WebMvcConfigurer {
	// UserRating Message
	// A.UserRating Exchange
	@Bean(name = "myUserRatingsExchange")
	Exchange createUserRatingsExchange() {
		return ExchangeBuilder.topicExchange("myuser.ratings.exchange").build();
	}

	// B.UserRating Queue
	@Bean(name = "myUserRatingsQueue")
	Queue createUserRatingsQueue() {
		return QueueBuilder.durable("myuser.ratings.queue").build();
	}

	@Bean
	Binding userRatingBinding(Queue myUserRatingsQueue, TopicExchange myUserRatingsExchange) {
		return BindingBuilder.bind(myUserRatingsQueue).to(myUserRatingsExchange).with("myuser.ratings.key");
	}

}
