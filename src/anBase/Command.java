package anBase;

import anBase.exceptions.UnknownCommandException;

import java.util.ArrayList;
import java.util.HashMap;

public class Command {
	public enum Type {
		READ, WRITE, REMOVE, STORE_TO_DISK, RESTORE_FROM_DISK
	}

	public Command(Type type, ArrayList<String> args) {
		this.type = type;
		this.args = args;
	}

	public Type type() {
		return type;
	}

	public ArrayList<String> args() {
		return args;
	}

	public static Command fromString(String commandStr) throws UnknownCommandException {
		String[] splittedStr = commandStr.split(" ");
		ArrayList<String> clearSplittedStr = new ArrayList<String>();
		for (int i = 0; i < splittedStr.length; i++) {
			String curStr = splittedStr[i].trim();
			if (!curStr.equals(""))
				clearSplittedStr.add(curStr);
		}

		HashMap<String, Type> strTypeMap = new HashMap<String, Type>(5);
		strTypeMap.put("get", Type.READ);
		strTypeMap.put("set", Type.WRITE);
		strTypeMap.put("remove", Type.REMOVE);
		strTypeMap.put("store_to_disk", Type.STORE_TO_DISK);
		strTypeMap.put("restore_from_disk", Type.RESTORE_FROM_DISK);

		if ((clearSplittedStr.size() == 0)
				|| (!strTypeMap.containsKey(clearSplittedStr.get(0).toLowerCase())))
			throw new UnknownCommandException();

		Type commandType = strTypeMap.get(clearSplittedStr.get(0).toLowerCase());
		clearSplittedStr.remove(0);

		switch (commandType) {
			case READ: {
				if (clearSplittedStr.size() < 1)
					throw new UnknownCommandException();
				break;
			}

			case WRITE: {
				if (clearSplittedStr.size() < 2)
					throw new UnknownCommandException();
				break;
			}

			case REMOVE: {
				if (clearSplittedStr.size() < 1)
					throw new UnknownCommandException();
				break;
			}
		}

		return new Command(commandType, clearSplittedStr);
	}

	private Type type;
	private ArrayList<String> args;
}
