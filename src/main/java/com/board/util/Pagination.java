package com.board.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Pagination {
	private static final Logger logger = LoggerFactory.getLogger(Pagination.class);
	
	int ROWS_PER_PAGE = 5;
	int DISPLAY_PAGE_NUMBER = 5;
	double totalRows;
	int currentPage;
	Boolean isNext = true;
	Boolean isBefore = true;
	int[] pages;
	int startPage;
	int endPage;
	
	public Pagination(int totalRows, int currentPage) {
		this.totalRows = totalRows;
		this.currentPage = currentPage;
		
		getPagesArr();
	}

	public void getPagesArr() {
		int totalPageNum = (int) Math.ceil(totalRows/ROWS_PER_PAGE);
		double currGroup = Math.floor(currentPage/DISPLAY_PAGE_NUMBER);
		
		startPage = (int) currGroup*5;
		if(startPage<5) {
			setIsBefore(false);
		}
		
		endPage = (int) (currGroup+1)*5;
		if(endPage > totalPageNum) {
			endPage = totalPageNum;
			setIsNext(false);
		}
		
		pages = new int[endPage-startPage];
		for(int i=0; i+startPage < endPage ; i++) {
			pages[i] = i+startPage;
		}
	}
	
	public int getOffset(int currentPage) {
		int offset = ROWS_PER_PAGE*currentPage;
		return offset;
	}

	public Boolean getIsNext() {
		return isNext;
	}

	public void setIsNext(Boolean isNext) {
		this.isNext = isNext;
	}

	public Boolean getIsBefore() {
		return isBefore;
	}

	public void setIsBefore(Boolean isBefore) {
		this.isBefore = isBefore;
	}

	public int[] getPages() {
		return pages;
	}

	public void setPages(int[] pages) {
		this.pages = pages;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
}
