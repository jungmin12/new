package org.zerock.domain;

import java.util.Date;

public class ReplyVO {

	private int rno, bno;
	private String reply, replyer;
	private Date replydate;

	public int getRno() {
		return rno;
	}

	public void setRno(int rno) {
		this.rno = rno;
	}
	
	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getReply() {
		return reply;
	}
	
	public void setReply(String reply) {
		this.reply = reply;
	}

	public String getReplyer() {
		return replyer;
	}

	public void setReplyer(String replyer) {
		this.replyer = replyer;
	}

	public Date getReplydate() {
		return replydate;
	}
	
	public void setReplydate(Date replydate) {
		this.replydate = replydate;
	}
	
	@Override
	public String toString() {
		return "ReplyVO [rno=" + rno + ", bno=" + bno + ", reply=" + reply + ", replyer=" + replyer + ", replydate="
				+ replydate + "]";
	}
	
	
	
}
