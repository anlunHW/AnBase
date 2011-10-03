package anBase;

import java.util.ArrayList;

/*
public class DuplicationExecutor implements CommandExecutor {
	public DuplicationExecutor(ArrayList<CommandExecutor> executorList) {
		lastReadExecutor = 0;
		this.executorList = executorList;
	}

	public String execute(String cmdStr) {
		Command cmd = Command.fromString(cmdStr);

		switch (cmd.type()) {
			case READ: {
			}

			case WRITE:
			case REMOVE:
			case STORE_TO_DISK:
			case RESTORE_FROM_DISK: {
				for (CommandExecutor ex : executorList) {
					ex.execute(cmdStr);
				}
			}

			case UNKNOWN_CMD: {
				return "Sorry, unknown command!";
			}

			case QUIT: {
				return "Bye!";
			}
		}
	}

	private void nextReadExecutor() {
		lastReadExecutor = (lastReadExecutor + 1) % executorList.size();
	}

	private ArrayList<CommandExecutor> executorList;
	private int lastReadExecutor;
}
*/
