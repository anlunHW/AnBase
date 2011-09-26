package anBase;

import anBase.exceptions.CommandExecutionException;

public interface CommandExecutor {
	String execute(Command cmd) throws CommandExecutionException;
}
