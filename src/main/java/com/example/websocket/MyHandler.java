package com.example.websocket;

import java.io.IOException;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyHandler extends TextWebSocketHandler {

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        if (message.getPayload().equals("ping")) {
        	log.info("Received ping, sending pong");
        	try {
        		session.sendMessage(new TextMessage("pong"));
        		log.info("Pong sent");
        	} catch (IOException e) {
        		log.error("I/O exception while sending pong");
        		try {
        			session.close(CloseStatus.SERVER_ERROR);
        		} catch (IOException ee) {
        			log.error("I/O error while closing session");
        		}
        	}
        } else if (message.getPayload().equals("pong")) {
        	log.info("Received pong");
        } else {
        	log.warn("Received unrecognised message: '" + message.getPayload() + "'");
        }
    }

    @Override
	public void afterConnectionEstablished(WebSocketSession session) {
    	log.info("WebSocket connection opened, sending ping");
    	try {
    		session.sendMessage(new TextMessage("ping"));
    		log.info("Ping sent");
    	} catch (IOException e) {
    		log.error("I/O exception while sending ping");
    		try {
    			session.close(CloseStatus.SERVER_ERROR);
    		} catch (IOException ee) {
    			log.error("I/O error while closing session");
    		}
    	}
	}

}
