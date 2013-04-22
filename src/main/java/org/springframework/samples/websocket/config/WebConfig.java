package org.springframework.samples.websocket.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.samples.websocket.echo.EchoWebSocketHandler;
import org.springframework.sockjs.server.support.DefaultSockJsService;
import org.springframework.sockjs.server.support.SockJsHttpRequestHandler;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.websocket.WebSocketHandler;
import org.springframework.websocket.server.support.WebSocketHttpRequestHandler;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private RootConfig rootConfig;


	@Bean
	public SimpleUrlHandlerMapping handlerMapping() {

		WebSocketHandler wsHandler = new EchoWebSocketHandler(this.rootConfig.echoService());

		SockJsHttpRequestHandler sockJsHttpHandler =
				new SockJsHttpRequestHandler("/echoSockJS", sockJsService(), wsHandler);

		WebSocketHttpRequestHandler webSocketHttpHandler = new WebSocketHttpRequestHandler(wsHandler);

		Map<String, Object> urlMap = new HashMap<String, Object>();
		urlMap.put(sockJsHttpHandler.getPattern(), sockJsHttpHandler);
		urlMap.put("/echoWebSocket", webSocketHttpHandler);

		SimpleUrlHandlerMapping hm = new SimpleUrlHandlerMapping();
		hm.setOrder(1);
		hm.setUrlMap(urlMap);

		return hm;
	}

	// SockJsService

	@Bean
	public DefaultSockJsService sockJsService() {
		DefaultSockJsService sockJsService = new DefaultSockJsService();
		sockJsService.setHeartbeatTime(10000);
		return sockJsService;
	}

	// Allow serving HTML files through the default Servlet

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

}
