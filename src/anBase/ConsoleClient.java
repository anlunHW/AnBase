package anBase;

import anBase.exceptions.CommandExecutionException;
import anBase.exceptions.UnknownCommandException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class ConsoleClient {
	public void start() throws IOException {
		System.out.println("Print some commands, and I do smth.\nTo quit - write \"q\"");
		CommandExecutor executor = new LocalCommandExecutor();

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			while (true) {
				String curCommandStr = reader.readLine();
				if (curCommandStr.equals("q"))
					break;
				if (curCommandStr.trim().equals(""))
					continue;

				try {
					Command curCommand = Command.fromString(curCommandStr);
					System.out.println(executor.execute(curCommand));

				} catch (UnknownCommandException e) {
					System.out.println("Unknown command! Sorry...");

				} catch (CommandExecutionException e) {
					System.out.println("Problem with command execution.");
				}

			}

		} catch (IOException e) {
			e.printStackTrace();


		} finally {
			reader.close();
		}
	}

	public static void main(String[] args) throws IOException {
		ConsoleClient cl = new ConsoleClient();
		cl.start();
	}
}
