package anBase.httpServer;

import java.io.*;
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
		BufferedReader in = new BufferedReader(
				new InputStreamReader(exc.getRequestBody()));
		String userName = in.readLine();

		PrintWriter out = new PrintWriter(exc.getResponseBody());
		out.println("Hello, " + userName + "!");
		out.println("<H1>It's a test!</H1>");
		out.close();
		exc.close();
	}
}