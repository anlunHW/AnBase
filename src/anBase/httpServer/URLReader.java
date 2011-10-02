package anBase.httpServer;

import java.net.*;
import java.io.*;

public class URLReader {
    public static void main(String[] args) throws Exception {
	    System.out.println("Please, enter your name: ");
	    BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
	    String name = cin.readLine();

	    URL localhostEx = new URL("http://localhost:8888");
	    URLConnection connection = localhostEx.openConnection();
	    connection.setDoOutput(true);

	    BufferedWriter out = new BufferedWriter(
			    new OutputStreamWriter(connection.getOutputStream()));
	    out.write(name);
	    out.close();

	    BufferedReader in = new BufferedReader(
			    new InputStreamReader(connection.getInputStream()));

		String inputLine;
		while ((inputLine = in.readLine()) != null)
			System.out.println(inputLine);
		in.close();
    }
}