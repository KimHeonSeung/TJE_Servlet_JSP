package pro.model;

public class Likepage {
	private String member_id;
	private String title;
	private String likepage;

	public Likepage() {
	}


	public Likepage(String member_id, String title, String likepage) {
		super();
		this.member_id = member_id;
		this.title = title;
		this.likepage = likepage;
	}


	public String getLikepage() {
		return likepage;
	}


	public void setLikepage(String likepage) {
		this.likepage = likepage;
	}


	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


}
