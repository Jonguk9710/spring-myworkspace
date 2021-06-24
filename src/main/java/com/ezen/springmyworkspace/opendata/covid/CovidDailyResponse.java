package com.ezen.springmyworkspace.opendata.covid;

import java.util.List;

import lombok.Data;

@Data
public class CovidDailyResponse {
	private Response response;

	@Data
	public class Response {
		private Body body;
	}

	@Data
	public class Body {
		private Items items;
	}

	@Data
	public class Items {
		private List<Item> item;
	}

	@Data
	public class Item {
		private String stdDay;
		private String gubun;
		private String deathCnt;
		private String incDec;
	}

}
