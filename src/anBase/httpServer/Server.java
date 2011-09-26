package anBase.httpServer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Server implements HttpHandler {
	public static void main(String[] args) throws IOException {
		HttpServer server = HttpServer.create(new InetSocketAddress(8888), 10);
		server.createContext("/", new Server());
		server.start();
		System.out.println("Server started\nPress any key to stop...");
		System.in.read();
		server.stop(0);
		System.out.println("Server stopped");
	}

	public void handle(HttpExchange exc) throws IOException {
		exc.sendResponseHeaders(200, 0);
		PrintWriter out = new PrintWriter(exc.getResponseBody());
		out.println("Hello moto!");
		out.close();
		exc.close();
	}
}