package com.ezen.springmyworkspace.opendata.covid;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CovidDailyRepository extends JpaRepository<CovidDaily, CovidDailyId> {

}
