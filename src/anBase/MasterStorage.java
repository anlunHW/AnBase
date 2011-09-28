package anBase;

public interface MasterStorage {
	void addSlave(SlaveStorage slave);
	void removeSlave(SlaveStorage slave);
	void updateSlaves();
}
