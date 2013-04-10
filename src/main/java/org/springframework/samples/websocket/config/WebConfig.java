package org.springframework.samples.websocket.config;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.samples.websocket.echo.EchoWebSocketHandler;
import org.springframework.samples.websocket.echo.sockjs.EchoSockJsHandler;
import org.springframework.sockjs.server.support.DefaultSockJsService;
import org.springframework.sockjs.server.support.SockJsServiceHandlerMapping;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.websocket.server.endpoint.handshake.EndpointHandshakeHandler;
import org.springframework.websocket.server.support.HandshakeHttpRequestHandler;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private RootConfig rootConfig;


	// Spring MVC handler mapping for SockJS service

	@Bean
	public SockJsServiceHandlerMapping sockJsHandlerMapping() {
		SockJsServiceHandlerMapping hm = new SockJsServiceHandlerMapping(echoSockJsService());
		hm.setOrder(0);
		return hm;
	}

	// SockJS service at "/echoSockJS"

	@Bean
	public DefaultSockJsService echoSockJsService() {
		EchoSockJsHandler handler = new EchoSockJsHandler(this.rootConfig.echoService());
		return new DefaultSockJsService("/echoSockJS", handler);
	}

	// Other mappings

	@Bean
	public SimpleUrlHandlerMapping handlerMapping() {

		EndpointHandshakeHandler endpointHandler = new EndpointHandshakeHandler(new EchoWebSocketHandler());

		Map<String, Object> urlMap = new HashMap<String, Object>();
		urlMap.put("/restart", createEchoRestartHandler());
		urlMap.put("/echoHandler", new HandshakeHttpRequestHandler(endpointHandler));

		SimpleUrlHandlerMapping handlerMapping = new SimpleUrlHandlerMapping();
		handlerMapping.setOrder(1);
		handlerMapping.setUrlMap(urlMap);

		return handlerMapping;
	}

	// A handler that restarts the EchoEndpointConnectionManager WebSocket client

	private HttpRequestHandler createEchoRestartHandler() {
		return new HttpRequestHandler() {
			@Override
			public void handleRequest(HttpServletRequest req, HttpServletResponse res) {
				rootConfig.echoEndpointConnectionManager().stop();
				rootConfig.echoEndpointConnectionManager().start();
			}
		};
	}

	// Allow serving HTML files through the default Servlet

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

}
