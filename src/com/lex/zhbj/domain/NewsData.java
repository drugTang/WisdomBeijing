package com.lex.zhbj.domain;

import java.util.List;

public class NewsData {
	
	public int retcode;
	public List<NewsMenuData> data;
	
	public class NewsMenuData {
		public String title;
		public String url;
		public List<NewsChildMenuData> children;
		@Override
		public String toString() {
			return "NewsMenuData [title=" + title + ", url=" + url + ", children=" + children + "]";
		}
		
	}
	
	public class NewsChildMenuData {
		public String title;
		public String url;
		@Override
		public String toString() {
			return "NewsChildMenuData [title=" + title + ", url=" + url + "]";
		}
		
	}

	@Override
	public String toString() {
		return "NewsData [retcode=" + retcode + ", data=" + data + "]";
	}
	
	

}
