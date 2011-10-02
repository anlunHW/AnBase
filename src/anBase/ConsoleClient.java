package anBase;

import anBase.exceptions.CommandExecutionException;
import anBase.exceptions.UnknownCommandException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class ConsoleClient {
	public ConsoleClient() {
		executor = new LocalCommandExecutor();
	}

	public ConsoleClient(CommandExecutor executor) {
		this.executor = executor;
	}

	public void start() throws IOException {
		System.out.println("Print some commands, and I do smth.\nTo quit - write \"quit\"");

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			while (true) {
				String curCommandStr = reader.readLine();
				String executorAnswer = executor.execute(curCommandStr);
				System.out.println(executorAnswer);
				if (executorAnswer.equals("Bye!"))
					break;
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

	private CommandExecutor executor;
}
