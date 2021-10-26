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
///vntnl
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getStat() {
		return stat;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setStat(int stat) {
		this.stat = stat;
	}

	

	@Override
	public String toString() {
		return "Allplayer [id=" + id + ", name=" + name + ", stat=" + stat + "]";
	}

}
