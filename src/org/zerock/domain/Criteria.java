package org.zerock.domain;

public class Criteria {

	private int page;
	private String stype;
	private String keyword;

	public Criteria(int page, String stype, String keyword) {
		super();
		this.page = page;
		this.stype = stype;
		this.keyword = keyword;
	}

	public int getPage() {
		return page;
	}

	public String getStype() {
		return stype;
	}

	public String getKeyword() {
		System.out.println("keyword");
		return "%"+keyword+"%";
	}

	public int getNum() {
		return (page - 1) * 10;
	}

	@Override
	public String toString() {
		return "Criteria [page=" + page + ", stype=" + stype + ", keyword=" + keyword + "]";
	}

}
