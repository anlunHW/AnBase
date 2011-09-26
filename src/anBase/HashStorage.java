package anBase;

import java.lang.String;
import java.util.HashMap;

public class HashStorage implements Storage {
	public HashStorage() {
		map = new HashMap<String, String>();
		diskSavePath = new String();
	}

	public  HashStorage(String diskSavePath) {
		this.diskSavePath = diskSavePath;
	}

	public String get(String key) {
		return map.get(key);
	}

	public boolean contains(String key) {
		return map.containsKey(key);
	}

	public void set(String key, String value) {
		map.put(key, value);
	}

	public void remove(String key) {
		map.remove(key);
	}

	public void storeToDisk() {
		storeToDisk(diskSavePath);
		return;
	}

	public void storeToDisk(String path) {
		return;
	}

	public void restoreFromDisk() {
		restoreFromDisk(diskSavePath);
		return;
	}

	public void restoreFromDisk(String path) {
		return;
	}

	private HashMap<String, String> map;
	private String diskSavePath;
}
