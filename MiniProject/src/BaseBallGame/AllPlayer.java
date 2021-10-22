package BaseBallGame;

public class AllPlayer {

	private String id;
	private String name;
	private int stat;

	public AllPlayer() {
	}

	public AllPlayer(String id, String name, int stat) {
		super();
		this.id = id;
		this.name = name;
		this.stat = stat;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getStat() {
		return stat;
	}

	@Override
	public String toString() {
		return "Allplayer [id=" + id + ", name=" + name + ", stat=" + stat + "]";
	}

}
