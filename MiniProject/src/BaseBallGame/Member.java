package BaseBallGame;

public class Member {

	public static String id;
	private String pw;
	private int rank;
	private int pick;
	
	public void member(String id, String pw, int rank, int pick) {
		
		this.id=id;
		this.pw=pw;
		this.rank=rank;
		this.pick=pick;
		
	}

	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
	public int getPick() {
		return pick;
	}
	public void setPick(int pick) {
		this.pick = pick;
	}



	@Override
	public String toString() {
		return "Member [id=" + id + ", pw=" + pw + ", rank=" + rank + ", pick=" + pick + "]";
	}



	


}
