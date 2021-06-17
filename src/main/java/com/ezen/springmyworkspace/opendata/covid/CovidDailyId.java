package com.ezen.springmyworkspace.opendata.covid;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CovidDailyId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1743176488971107945L;
	private String stdDay;
	private String gubun;

}
