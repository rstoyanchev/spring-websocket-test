/*
 * Copyright 2002-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.samples.websocket.echo.DefaultEchoService;
import org.springframework.samples.websocket.echo.EchoWebSocketHandler;
import org.springframework.samples.websocket.snake.SnakeWebSocketHandler;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.PerConnectionWebSocketHandler;

@Configuration
@EnableWebMvc
@EnableWebSocket
public class WebConfig implements WebMvcConfigurer, WebSocketConfigurer {

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {

		registry.addHandler(echoWebSocketHandler(), "/echo", "/echo-issue4");
		registry.addHandler(snakeWebSocketHandler(), "/snake");

		registry.addHandler(echoWebSocketHandler(), "/sockjs/echo").withSockJS();
		registry.addHandler(echoWebSocketHandler(), "/sockjs/echo-issue4").withSockJS().setHttpMessageCacheSize(20000);

		registry.addHandler(snakeWebSocketHandler(), "/sockjs/snake").withSockJS();
	}

	@Bean
	public WebSocketHandler echoWebSocketHandler() {
		return new EchoWebSocketHandler(echoService());
	}

	@Bean
	public WebSocketHandler snakeWebSocketHandler() {
		return new PerConnectionWebSocketHandler(SnakeWebSocketHandler.class);
	}

	@Bean
	public DefaultEchoService echoService() {
		return new DefaultEchoService("Did you say \"%s\"?");
	}

	// Allow serving HTML files through the default Servlet

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

}
