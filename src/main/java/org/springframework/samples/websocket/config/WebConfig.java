package org.springframework.samples.websocket.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.samples.websocket.echo.EchoWebSocketHandler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.sockjs.server.support.DefaultSockJsService;
import org.springframework.sockjs.server.support.SockJsHttpRequestHandler;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.websocket.HandlerProvider;
import org.springframework.websocket.WebSocketHandler;
import org.springframework.websocket.server.support.WebSocketHttpRequestHandler;
import org.springframework.websocket.support.BeanCreatingHandlerProvider;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private RootConfig rootConfig;


	@Bean
	public SimpleUrlHandlerMapping handlerMapping() {

		DefaultSockJsService sockJsService = new DefaultSockJsService(sockJsTaskScheduler());

		SockJsHttpRequestHandler sockJsHttpRequestHandler =
				new SockJsHttpRequestHandler("/echoSockJS", sockJsService, echoWebSocketHandler());

		WebSocketHttpRequestHandler webSocketHttpRequestHandler = new WebSocketHttpRequestHandler(echoWebSocketHandler());

		Map<String, Object> urlMap = new HashMap<String, Object>();
		urlMap.put(sockJsHttpRequestHandler.getPattern(), sockJsHttpRequestHandler);
		urlMap.put("/echoWebSocket", webSocketHttpRequestHandler);

		SimpleUrlHandlerMapping hm = new SimpleUrlHandlerMapping();
		hm.setOrder(1);
		hm.setUrlMap(urlMap);

		return hm;
	}

	@Bean
	public HandlerProvider<WebSocketHandler<?>> echoWebSocketHandler() {
		return new BeanCreatingHandlerProvider<WebSocketHandler<?>>(EchoWebSocketHandler.class);
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
