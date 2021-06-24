package com.ezen.springmyworkspace.opendata.covid;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@IdClass(CovidDailyId.class)
public class CovidDaily {
	@Id
	private String stdDay;
	@Id
	private String gubun;

	private String deathCnt;
	private String incDec;

	public CovidDaily(CovidDailyResponse.Item item) {
		this.stdDay = item.getStdDay();
		this.gubun = item.getGubun();
		this.deathCnt = item.getDeathCnt();
		this.incDec = item.getIncDec();
	}

}
