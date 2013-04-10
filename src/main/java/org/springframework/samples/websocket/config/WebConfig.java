package org.springframework.samples.websocket.config;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.samples.websocket.echo.sockjs.EchoSockJsHandler;
import org.springframework.sockjs.server.support.DefaultSockJsService;
import org.springframework.sockjs.server.support.SockJsServiceHandlerMapping;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private RootConfig rootConfig;


	@Bean
	public SockJsServiceHandlerMapping sockJsHandlerMapping() {
		SockJsServiceHandlerMapping hm = new SockJsServiceHandlerMapping(echoSockJsService());
		hm.setOrder(0);
		return hm;
	}

	@Bean
	public DefaultSockJsService echoSockJsService() {
		EchoSockJsHandler handler = new EchoSockJsHandler(this.rootConfig.echoService());
		return new DefaultSockJsService("/echoSockJS", handler);
	}

	@Bean
	public SimpleUrlHandlerMapping handlerMapping() {

		Map<String, Object> urlMap = new HashMap<String, Object>();
		urlMap.put("/restart", createEchoConnectionRestartHandler());
//		urlMap.put("/echoHandler", createHttpRequestHandler(new EchoWebSocketHandler()));

		SimpleUrlHandlerMapping handlerMapping = new SimpleUrlHandlerMapping();
		handlerMapping.setOrder(1);
		handlerMapping.setUrlMap(urlMap);

		return handlerMapping;
	}

	private HttpRequestHandler createEchoConnectionRestartHandler() {
		return new HttpRequestHandler() {
			@Override
			public void handleRequest(HttpServletRequest req, HttpServletResponse res) {
				rootConfig.echoEndpointConnection().stop();
				rootConfig.echoEndpointConnection().start();
			}
		};
	}

//	public HttpRequestHandler createHttpRequestHandler(WebSocketHandler webSocketHandler) {
//		return new EndpointHttpRequestHandler(new EndpointHandshakeRequestHandler(webSocketHandler));
//	}

	// -------------------------------------------------------------

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

}
