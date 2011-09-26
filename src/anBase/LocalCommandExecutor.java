package anBase;

import anBase.exceptions.CommandExecutionException;

public class LocalCommandExecutor implements CommandExecutor {
	public LocalCommandExecutor() {
		baseStorage = new HashStorage();
	}

	public String execute(Command cmd) throws CommandExecutionException {
		switch (cmd.type()) {
			case READ: {
				if (cmd.args().size() < 1)
					throw new CommandExecutionException();
				if (!baseStorage.contains(cmd.args().get(0)))
					return "Value with key \"" + cmd.args().get(0) + "\" hasn't been stored.";
				return cmd.args().get(0) + ": " + baseStorage.get(cmd.args().get(0));
			}

			case WRITE: {
				if (cmd.args().size() < 2)
					throw new CommandExecutionException();
				baseStorage.set(cmd.args().get(0), cmd.args().get(1));
				return "Data have been stored to base.";
			}

			case REMOVE: {
				if (cmd.args().size() < 1)
					throw new CommandExecutionException();
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
				baseStorage.restoreFromDisk();
				return "Base has been restored from disk.";
			}
		}

		return "ok";
	}

	private Storage baseStorage;
}
