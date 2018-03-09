 package com.nantian.atom.po;

import java.util.ArrayList;
import java.util.List;

public class PageDsnForTableList {
    private List<PageDsnForTable> pageDsnForTables=new ArrayList<PageDsnForTable>();
    
    private PageDsnForFunction pageDsnForFunction = new PageDsnForFunction();

	public List<PageDsnForTable> getPageDsnForTables() {
		return pageDsnForTables;
	}
	
	public void setPageDsnForTables(List<PageDsnForTable> tables) {
		this.pageDsnForTables = tables;
	}

	public PageDsnForFunction getPageDsnForFunction() {
		return pageDsnForFunction;
	}

	public void setPageDsnForFunction(PageDsnForFunction pageDsnForFunction) {
		this.pageDsnForFunction = pageDsnForFunction;
	}
   
	
}
