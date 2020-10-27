# websocket-test

Demo of WebSocket communication between a Javascript client and a Spring Boot server.
The Spring Boot server accepts the Javascript client's HTTP connection, which is then upgraded to WebSockets.
Both client and server then enter into a simple ping-pong conversation, to demonstrate symmetric and bidirectional communication.

In a real application, some other protocol would be layered over the top of WebSockets - either an existing protocol based on TCP, or a new protocol.
Spring Boot makes it easy to layer STOMP over the top of WebSockets, but possible to use other protocols.
