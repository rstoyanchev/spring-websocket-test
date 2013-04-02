package org.springframework.samples.websocket.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.samples.websocket.echo.EchoWebSocketHandler;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.websocket.WebSocketHandler;
import org.springframework.websocket.server.endpoint.EndpointHandshakeRequestHandler;
import org.springframework.websocket.support.EndpointHttpRequestHandler;

@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {

//	@Bean
//	public SimpleUrlHandlerMapping handlerMapping() {
//
//		Map<String, Object> urlMap = new HashMap<String, Object>();
//		urlMap.put("/echo", createHttpRequestHandler(new EchoWebSocketHandler()));
//
//		SimpleUrlHandlerMapping handlerMapping = new SimpleUrlHandlerMapping();
//		handlerMapping.setOrder(1);
//		handlerMapping.setUrlMap(urlMap);
//
//		return handlerMapping;
//	}
//
//	public HttpRequestHandler createHttpRequestHandler(WebSocketHandler webSocketHandler) {
//		return new EndpointHttpRequestHandler(new EndpointHandshakeRequestHandler(webSocketHandler));
//	}

	// -------------------------------------------------------------

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

}
