package anBase;

import anBase.exceptions.FromFileBaseRestoringException;

import java.lang.String;

public interface Storage {
	String get(String key);
	boolean contains(String key);

	String set(String key, String value);
	String remove(String key);

	void storeToDisk();
	void storeToDisk(String path);

	void restoreFromDisk() throws FromFileBaseRestoringException;
	void restoreFromDisk(String path) throws FromFileBaseRestoringException;
}
