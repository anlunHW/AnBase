package anBase;

import anBase.exceptions.CommandExecutionException;
import anBase.exceptions.FromFileBaseRestoringException;

public class LocalCommandExecutor implements CommandExecutor {
	public LocalCommandExecutor() {
		baseStorage = new HashStorage();
	}

	public LocalCommandExecutor(String storeBasePath) {
		baseStorage = new HashStorage(storeBasePath);
	}

	public String execute(String cmdStr) {
		Command cmd = Command.fromString(cmdStr);

		switch (cmd.type()) {
			case READ: {
				if (!baseStorage.contains(cmd.args().get(0)))
					return "Value with key \"" + cmd.args().get(0) + "\" hasn't been stored.";
				return cmd.args().get(0) + ": " + baseStorage.get(cmd.args().get(0));
			}

			case WRITE: {
				baseStorage.set(cmd.args().get(0), cmd.args().get(1));
				return "Data have been stored to base.";
			}

			case REMOVE: {
				if (!baseStorage.contains(cmd.args().get(0)))
					return "Value with key \"" + cmd.args().get(0) + "\" hasn't been stored.";

				baseStorage.remove(cmd.args().get(0));
				return "Value with key \"" + cmd.args().get(0) + "\" has been removed.";
			}

			case STORE_TO_DISK: {
				baseStorage.storeToDisk();
				return "Base has been copied to disk.";
			}

			case RESTORE_FROM_DISK: {
				try {
					baseStorage.restoreFromDisk();
					return "Base has been restored from disk.";
				} catch (FromFileBaseRestoringException e) {
					return "Problem with base restoring: " + e.getMessage();
				}
			}

			case UNKNOWN_CMD: {
				return "Sorry, unknown command!";
			}

			case QUIT: {
				return "Bye!";
			}
		}

		return "ok";
	}

	private Storage baseStorage;
}
