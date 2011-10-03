package anBase;

import anBase.exceptions.FromFileBaseRestoringException;

import java.io.*;
import java.lang.String;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HashStorage implements Storage {
	public HashStorage() {
		map = new HashMap<String, String>();
		diskSavePath = "storage";
		initLog("logfile");
	}

	public HashStorage(String diskSavePath) {
		map = new HashMap<String, String>();
		this.diskSavePath = diskSavePath;
		initLog("logfile");
	}

	public HashStorage(String diskSavePath, String logPath) {
		map = new HashMap<String, String>();
		this.diskSavePath = diskSavePath;
		initLog(logPath);
	}

	public String get(String key) {
		return map.get(key);
	}

	public boolean contains(String key) {
		return map.containsKey(key);
	}

	public String set(String key, String value) {
		log("set " + key + " " + value);
		return map.put(key, value);
	}

	public String remove(String key) {
		if (map.containsKey(key)) {
			log("remove " + key);
			return map.remove(key);
		}

		return "Value with key \"" + key + "\" hasn't been stored.";
	}

	public void storeToDisk() {
		storeToDisk(diskSavePath);
	}

	public void storeToDisk(String path) {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new FileWriter(path));
			for (Map.Entry<String, String> curEntry: map.entrySet()) {
				out.write("@@@@@\n");
				out.write(curEntry.getKey());
				out.newLine();
				out.write("#####\n");
				out.write(curEntry.getValue());
				out.newLine();
			}

			initLog();

		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			try {
				if (out != null)
					out.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void restoreFromDisk() throws FromFileBaseRestoringException {
		restoreFromDisk(diskSavePath);
	}

	public void restoreFromDisk(String path) throws FromFileBaseRestoringException {
		BufferedReader in = null;
		map.clear();

		try {
			in = new BufferedReader(new FileReader(path));
			String curLine;

			ReadingState state = ReadingState.AROUND_TEXT;
			StringBuilder curKey = new StringBuilder();
			StringBuilder curValue = new StringBuilder();
			while ((curLine = in.readLine()) != null) {
				if (curLine.equals("@@@@@")) {
					if (state == ReadingState.VALUE) {
						map.put(curKey.toString(), curValue.toString());
						curKey = new StringBuilder(); //clearing
						curValue = new StringBuilder(); //clearing
					}

					state = ReadingState.KEY;
					continue;
				}

				if (curLine.equals("#####"))
					if (state == ReadingState.KEY) {
						state = ReadingState.VALUE;
						continue;
					} else {
						throw new FromFileBaseRestoringException("Invalid file");
					}

				switch (state) {
					case AROUND_TEXT: {
						continue;
					}

					case KEY: {
						if (curKey.length() > 0)
							curKey.append("\n");
						curKey.append(curLine);
						break;
					}

					case VALUE: {
						if (curValue.length() > 0)
							curValue.append("\n");
						curValue.append(curLine);
						curValue.append("\n");
						break;
					}
				}
			}
			if (state == ReadingState.VALUE)
				map.put(curKey.toString(), curValue.toString());
			initLog();

		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			try {
				if (in != null)
					in.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private enum ReadingState {
		AROUND_TEXT, KEY, VALUE
	}

	private void initLog() {
		initLog(logPath);
	}

	private void initLog(String newLogPath) {
		logPath = newLogPath;

		BufferedWriter log = null;
		try {
			log = new BufferedWriter(new FileWriter(logPath));
			log.write("saved base path:\n");
			log.write("\"" + diskSavePath + "\"\n");
			log.write("@@ COMMAND PATH @@\n");

		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			try {
				if (log != null)
					log.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void log(String newLogLine) {
		BufferedWriter log = null;
		try {
			log = new BufferedWriter(new FileWriter(logPath, true));
			log.write(newLogLine);
			log.newLine();

		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			try {
				if (log != null)
					log.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private HashMap<String, String> map;
	private String diskSavePath;
	private String logPath;
}
