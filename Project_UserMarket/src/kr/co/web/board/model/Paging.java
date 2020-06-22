package kr.co.web.board.model;

public class Paging {
	
    private final static int countPage = 3;	//	한 화면에 출력될 페이지 수
    private final static int countList = 8;	//	한 페이지에 출력될 게시물 수
    private int totalCount;		// 총 게시물 수
    private int page;			// 현재 페이지
    
    private int totalPage;		// 총 페이지 개수
    private int pageGroup;		// 현재 페이지그룹(1 : 1~5, 2 : 6~10, ...)
    private int totalGroup;		// 총 페이지그룹 개수
    private int startPage;	// 현재 페이지로 계산될 그룹내에 시작페이지 번호
    private int endPage;	// 현재 페이지로 계산될 그룹내에 마지막페이지 번호

    public Paging() {
		// TODO Auto-generated constructor stub
	}

//	public Paging(int totalCount, int page, int totalPage, int pageGroup, int totalGroup, int startPage, int endPage) {
//		super();
//		this.totalCount = totalCount;
//		this.page = page;
//		this.totalPage = totalPage;
//		this.pageGroup = pageGroup;
//		this.totalGroup = totalGroup;
//		this.startPage = startPage;
//		this.endPage = endPage;
//	}

	public Paging(int totalCount, int page) {
		super();
		this.totalCount = totalCount;
		this.page = page;
		this.totalPage = totalPage(totalCount);
		this.pageGroup = pageGroup(page);
		this.totalGroup = totalGroup(totalPage);
		this.startPage = ((page - 1) / countPage) * countPage + 1;
		this.endPage = endPage();
	}

	
	////////계산 메서드///////
	
	@Override
	public String toString() {
		return "[Paging]\n"
				+ " - totalCount(총 게시물 수) : " + totalCount + "\n"
				+ " - page(현재 페이지) : " + page + "\n"
				+ " - totalPage(총 페이지 수) : " + totalPage + "\n"
				+ " - pageGroup(현재 페이지그룹) : "+ pageGroup + "\n"
				+ " - totalGroup(총 페이지그룹 수)" + totalGroup + "\n"
				+ " - startPage(그룹 시작페이지) : " + startPage + "\n"
				+ " - endPage(그룹 끝페이지) : " + endPage + "\n";
	}
	
	public void example() {
		if (startPage > 1) {
		    System.out.print("<a href=\"?page=1\">[처음]</a>");
		}
		
		if (pageGroup > 1) {
		    System.out.print("<a href=\"?page=" + (startPage - 1)  + "\">[이전]</a>");
		}

		for (int iCount = startPage; iCount <= endPage; iCount++) {
		    if (iCount == page) {
		        System.out.print("<b>" + iCount + "</b>");
		    } else {
		        System.out.print(" " + iCount + " ");
		    }
		}

		if (pageGroup < totalGroup) {
		    System.out.print("<a href=\"?page=" + (endPage + 1)  + "\">[다음]</a>");
		}

		if (endPage < totalPage) {
		    System.out.print("<a href=\"?page=" + totalPage + "\">[끝]</a>");
		}
	}

	// 총 페이지 개수
	private int totalPage(int totalCount) {
		
		int totalPage = totalCount / countList;

		if (totalCount % countList > 0) {

		    totalPage++;

		}
		System.out.println("총 페이지 개수 : " + totalPage);
		
		return totalPage;
	}
	
	// 현재 페이지그룹
	private int pageGroup(int page) {

		int pageGroup = page / countPage;
		
		if (page % countPage > 0) {

			pageGroup++;

		}		
		System.out.println("현재 페이지 그룹 : " + pageGroup);
		
		return pageGroup;
	}
	
	private int totalGroup(int totalPage) {
		
		int totalGroup = totalPage / countPage;
		if (totalPage % countPage > 0) {

			totalGroup++;

		}
		System.out.println("총 페이지그룹 개수 : " + totalGroup);

		return totalGroup;
	}
	
	private int endPage() {
		
		int endPage = startPage + countPage - 1;
		if (endPage > totalPage) {

		    endPage = totalPage;

		}
		return endPage;
		
	}
	
	
	
	// getter, setter
	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageGroup() {
		return pageGroup;
	}

	public void setPageGroup(int pageGroup) {
		this.pageGroup = pageGroup;
	}

	public int getTotalGroup() {
		return totalGroup;
	}

	public void setTotalGroup(int totalGroup) {
		this.totalGroup = totalGroup;
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

	public static int getCountpage() {
		return countPage;
	}

	public static int getCountlist() {
		return countList;
	}
    
	
    
}











