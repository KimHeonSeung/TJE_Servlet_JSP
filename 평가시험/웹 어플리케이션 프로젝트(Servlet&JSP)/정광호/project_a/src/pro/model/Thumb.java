package pro.model;

public class Thumb {
	private String member_id;
	private int article_id;
	private int comment_id;
	private int article_thumb;
	private int comment_thumb;

	public Thumb() {
	}

	public Thumb(String member_id, int article_id, int comment_id, int article_thumb, int comment_thumb) {
		super();
		this.member_id = member_id;
		this.article_id = article_id;
		this.comment_id = comment_id;
		this.article_thumb = article_thumb;
		this.comment_thumb = comment_thumb;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public int getArticle_id() {
		return article_id;
	}

	public void setArticle_id(int article_id) {
		this.article_id = article_id;
	}

	public int getComment_id() {
		return comment_id;
	}

	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}

	public int getArticle_thumb() {
		return article_thumb;
	}

	public void setArticle_thumb(int article_thumb) {
		this.article_thumb = article_thumb;
	}

	public int getComment_thumb() {
		return comment_thumb;
	}

	public void setComment_thumb(int comment_thumb) {
		this.comment_thumb = comment_thumb;
	}

}
