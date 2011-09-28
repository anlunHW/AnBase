package anBase;

public class DistributedBaseCommandExecutor implements CommandExecutor {
	public DistributedBaseCommandExecutor() {
		master = new HashStorage();
		slave = new HashStorage();
	}

	public String execute(String cmdStr) {
	}

	private MasterStorage master;
	private SlaveStorage slave;
}
