package anBase;

import java.lang.String;

public interface Storage {
	String get(String key);
	boolean contains(String key);

	void set(String key, String value);
	void remove(String key);

	void storeToDisk();
	void storeToDisk(String path);

	void restoreFromDisk();
	void restoreFromDisk(String path);
}
