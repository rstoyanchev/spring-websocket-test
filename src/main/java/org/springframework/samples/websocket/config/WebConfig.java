package org.springframework.samples.websocket.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.samples.websocket.echo.EchoWebSocketHandler;
import org.springframework.samples.websocket.echo.sockjs.EchoSockJsHandler;
import org.springframework.sockjs.server.support.DefaultSockJsService;
import org.springframework.sockjs.server.support.SockJsHttpRequestHandler;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.websocket.server.support.WebSocketHttpRequestHandler;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private RootConfig rootConfig;


	@Bean
	public SimpleUrlHandlerMapping handlerMapping() {

		SockJsHttpRequestHandler sockJsHandler = sockJsRequestHandler();

		Map<String, Object> urlMap = new HashMap<String, Object>();
		urlMap.put(sockJsHandler.getMappingPattern(), sockJsHandler);
		urlMap.put("/echoWebSocket", webSocketHttpRequestHandler());

		SimpleUrlHandlerMapping handlerMapping = new SimpleUrlHandlerMapping();
		handlerMapping.setOrder(1);
		handlerMapping.setUrlMap(urlMap);

		return handlerMapping;
	}

	@Bean
	public SockJsHttpRequestHandler sockJsRequestHandler() {
		EchoSockJsHandler sockJsHandler = new EchoSockJsHandler(this.rootConfig.echoService());
		return new SockJsHttpRequestHandler(sockJsService(), sockJsHandler);
	}

	@Bean
	public DefaultSockJsService sockJsService() {
		DefaultSockJsService sockJsService = new DefaultSockJsService("/echoSockJS");
		sockJsService.setHeartbeatTime(10000);
		return sockJsService;
	}

	@Bean
	public WebSocketHttpRequestHandler webSocketHttpRequestHandler() {
		EchoWebSocketHandler webSocketHandler = new EchoWebSocketHandler(this.rootConfig.echoService());
		return new WebSocketHttpRequestHandler(webSocketHandler);
	}

	// Allow serving HTML files through the default Servlet

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

}
