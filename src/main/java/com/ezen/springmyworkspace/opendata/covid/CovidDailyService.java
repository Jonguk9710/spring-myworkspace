package com.ezen.springmyworkspace.opendata.covid;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
public class CovidDailyService {
	CovidDailyRepository repo;

	@Autowired
	public CovidDailyService(CovidDailyRepository repo) {
		this.repo = repo;
	}

	@CacheEvict(cacheNames = "covid-daily", key = "0")
	@SuppressWarnings("deprecation")
	@Scheduled(cron = " 0 00 00 * * *")
//	@Scheduled(fixedRate = 1000 * 60 * 30)
	public void requestCovidDailyData() throws IOException {
		System.out.println(new Date().toLocaleString() + "--실행--");
		getCovidDailyData();
	}

	private void getCovidDailyData() throws IOException {
		String serviceKey = "msU2G9IrR9gcyV4QpLGh%2Bvyn1bYZcKG2J4%2BWTq4Pp4LAu4FHCXqBVm1ftfcUIAoe5JnlMYGBw74FLRD4LM2u2w%3D%3D";

		StringBuilder builder = new StringBuilder();
		builder.append("http://openapi.data.go.kr/openapi/service/rest/Covid19");
		builder.append("/getCovid19SidoInfStateJson");
		builder.append("?serviceKey=" + serviceKey);
		builder.append("&numOfRows=10");
		builder.append("&pageNo=1");

		System.out.println(builder.toString());

		URL url = new URL(builder.toString());
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		byte[] result = con.getInputStream().readAllBytes();

		String data = new String(result, "UTF-8");

		JSONObject jObject = XML.toJSONObject(data);
		System.out.println(jObject.toString());

		CovidDailyResponse response = new Gson().fromJson(jObject.toString(), CovidDailyResponse.class);
		System.out.println(response);

		for (CovidDailyResponse.Item item : response.getResponse().getBody().getItems().getItem()) {
			if (!item.getGubun().equals("합계") && !item.getGubun().equals("검역"))
				repo.save(new CovidDaily(item));
		}

	}

}
