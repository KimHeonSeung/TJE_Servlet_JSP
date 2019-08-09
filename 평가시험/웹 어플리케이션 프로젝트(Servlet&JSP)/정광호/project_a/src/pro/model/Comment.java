package pro.model;

import java.util.*;
import java.text.*;

public class Comment {
	private int comment_id;
	private int article_id;
	private String member_id;
	private String content;
	private Date write_time;

	public Comment() {
	}

	public Comment(int comment_id, int article_id, String member_id, String content, Date write_time) {
		super();
		this.comment_id = comment_id;
		this.article_id = article_id;
		this.member_id = member_id;
		this.content = content;
		this.write_time = write_time;
	}

	public int getComment_id() {
		return comment_id;
	}

	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}

	public int getArticle_id() {
		return article_id;
	}

	public void setArticle_id(int article_id) {
		this.article_id = article_id;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWrite_timeString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
		return sdf.format(write_time);
	}

	public Date getWrite_time() {
		return write_time;
	}

	public void setWrite_time(Date write_time) {
		this.write_time = write_time;
	}

//	public String getComment_heartString() {
//		String heart = null;
//		if (comment_heart == 1) {
//			heart = "좋아요";
//		} else {
//			heart = "싫어요";
//		}
//		return heart;
//	}
//
//	public void setComment_heartString(String comment_heart) {
//		if (comment_heart.equals("남")) {
//			this.comment_heart = 1;
//		} else {
//			this.comment_heart = 2;
//		}
//
//	}
//
//	public int getComment_heart() {
//		return comment_heart;
//	}
//
//	public void setComment_heart(int comment_heart) {
//		this.comment_heart = comment_heart;
//	}

}
