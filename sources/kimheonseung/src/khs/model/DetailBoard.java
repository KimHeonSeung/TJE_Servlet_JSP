package khs.model;

import java.util.*;
import java.sql.*;
import java.text.*;

public class DetailBoard {
	private int article_num;
	private String article_title;
	private String writer_nick;
	private String article_content;
	private Timestamp write_date;
	private int read_count;
	private int like_count;
	
	public DetailBoard() {}

	public DetailBoard(int article_num, String article_title, String writer_nick, String article_content,
			Timestamp write_date, int read_count, int like_count) {
		super();
		this.article_num = article_num;
		this.article_title = article_title;
		this.writer_nick = writer_nick;
		this.article_content = article_content;
		this.write_date = write_date;
		this.read_count = read_count;
		this.like_count = like_count;
	}

	public int getArticle_num() {
		return article_num;
	}

	public void setArticle_num(int article_num) {
		this.article_num = article_num;
	}

	public String getArticle_title() {
		return article_title;
	}

	public void setArticle_title(String article_title) {
		this.article_title = article_title;
	}

	public String getWriter_nick() {
		return writer_nick;
	}

	public void setWriter_nick(String writer_nick) {
		this.writer_nick = writer_nick;
	}

	public String getArticle_content() {
		return article_content;
	}

	public void setArticle_content(String article_content) {
		this.article_content = article_content;
	}

	public Timestamp getWrite_date() {
		return write_date;
	}

	public void setWrite_date(Timestamp write_date) {
		this.write_date = write_date;
	}

	public int getRead_count() {
		return read_count;
	}

	public void setRead_count(int read_count) {
		this.read_count = read_count;
	}

	public int getLike_count() {
		return like_count;
	}

	public void setLike_count(int like_count) {
		this.like_count = like_count;
	}

	
	
}
	
