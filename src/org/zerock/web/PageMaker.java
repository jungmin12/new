package org.zerock.web;

public class PageMaker {

	private boolean prev, next;

	private int start, end, current, total;

	public PageMaker(int page, int total) {
		this.current = page; // 현재 페이지 번호
		this.total = total;
		calcPage();
	}

	private void calcPage() {

		int tempEnd = (int) (Math.ceil(current / 10.0) * 10);

		start = tempEnd - 9;

		prev = start == 1 ? false : true;

		if (tempEnd * 10 >= total) { // 한페이지당 10개씩 나온다는 가정하에
			end = (int) (Math.ceil(total / 10.0));
			next = false;
		}else{
			end = tempEnd;
			next = true;
		}
	}

	public boolean isPrev() {
		return prev;
	}

	public boolean isNext() {
		return next;
	}

	public int getStart() {
		return start;
	}

	public int getEnd() {
		return end;
	}

	public int getCurrent() {
		return current;
	}

	public int getTotal() {
		return total;
	}

	@Override
	public String toString() {
		return "PageMaker [prev=" + prev + ", next=" + next + ", start=" + start + ", end=" + end + ", current="
				+ current + ", total=" + total + "]";
	}
	
	
	

}
