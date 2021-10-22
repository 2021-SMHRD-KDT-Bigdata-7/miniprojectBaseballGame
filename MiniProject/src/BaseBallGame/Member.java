package BaseBallGame;

public class Member {

	private String id;
	private String pw;
	private int rank;
	
	public void member(String id, String pw, int rank) {
		
		this.id=id;
		this.pw=pw;
		this.rank=rank;
		
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
	@Override
	public String toString() {
		return "Member [id=" + id + ", pw=" + pw + ", rank=" + rank + "]";
	}


}
