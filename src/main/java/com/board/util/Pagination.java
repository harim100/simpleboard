package com.board.util;

public class Pagination {
	int DISPLAY_PER_PAGE = 5;
	int TOTAL_ROWS;
	int CURRENT_PAGE;
	int[] pages;
	
	
	public Pagination(int totalRows, int currentPage) {
		super();
		TOTAL_ROWS = totalRows;
		CURRENT_PAGE = currentPage;
	}

	public int[] getPagesArr() {
		int pageNumbers = (int) Math.floor(TOTAL_ROWS/DISPLAY_PER_PAGE);
		pages = new int[pageNumbers];
		for(int i=0; i<pageNumbers ; i++) {
			pages[i] = i;
		}
		return pages;
	}
	
	public int getOffset(int currentPage) {
		int offset = DISPLAY_PER_PAGE*currentPage;
		return offset;
	}
}
