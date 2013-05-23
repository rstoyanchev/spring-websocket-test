package org.springframework.samples.websocket.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.samples.websocket.echo.EchoWebSocketHandler;
import org.springframework.samples.websocket.snake.websockethandler.SnakeWebSocketHandler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.WebSocketHttpRequestHandler;
import org.springframework.web.socket.sockjs.SockJsService;
import org.springframework.web.socket.sockjs.support.DefaultSockJsService;
import org.springframework.web.socket.sockjs.support.SockJsHttpRequestHandler;
import org.springframework.web.socket.support.PerConnectionWebSocketHandler;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private RootConfig rootConfig;

	@Bean
	public SimpleUrlHandlerMapping handlerMapping() {

		SockJsService sockJsService = new DefaultSockJsService(sockJsTaskScheduler());

		Map<String, Object> urlMap = new HashMap<String, Object>();

		urlMap.put("/echoWebSocketHandler", new WebSocketHttpRequestHandler(echoWebSocketHandler()));
		urlMap.put("/snakeWebSocketHandler", new WebSocketHttpRequestHandler(snakeWebSocketHandler()));

		urlMap.put("/sockjs/echo/**", new SockJsHttpRequestHandler(sockJsService, echoWebSocketHandler()));
		urlMap.put("/sockjs/snake/**", new SockJsHttpRequestHandler(sockJsService, snakeWebSocketHandler()));

		SimpleUrlHandlerMapping hm = new SimpleUrlHandlerMapping();
		hm.setOrder(-1);
		hm.setUrlMap(urlMap);

		return hm;
	}

	@Bean
	public WebSocketHandler echoWebSocketHandler() {
		return new PerConnectionWebSocketHandler(EchoWebSocketHandler.class);
	}

	@Bean
	public WebSocketHandler snakeWebSocketHandler() {
		return new SnakeWebSocketHandler();
	}

	@Bean
	public ThreadPoolTaskScheduler sockJsTaskScheduler() {
		ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
		taskScheduler.setThreadNamePrefix("SockJS-");
		return taskScheduler;
	}

	// Allow serving HTML files through the default Servlet

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

}
