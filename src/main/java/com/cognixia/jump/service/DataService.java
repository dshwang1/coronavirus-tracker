package com.cognixia.jump.service;


import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.cognixia.jump.model.Location;


@Service
public class DataService {
	
	private final String DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_US.csv"; 
	
	private List<Location> stats = new ArrayList<>();
	
	public List<Location> getAllStats() {
		return stats;
	}
	
	@PostConstruct
	@Scheduled(cron = "* 5 * * * *")
	public void fetchVirusData() throws IOException, InterruptedException {
		List<Location> newStats = new ArrayList<>();
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(DATA_URL))
				.build();
		
		HttpResponse<String> httpResponse = client.send(request,  HttpResponse.BodyHandlers.ofString());
//		System.out.println(httpResponse.body());
		
		StringReader csvBodyReader = new StringReader(httpResponse.body());
		
		@SuppressWarnings("deprecation")
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
		for (CSVRecord record : records) {
			Location location = new Location();
			location.setState(record.get("Province_State"));
			location.setRegion(record.get("Admin2"));
			int latestCases = Integer.parseInt(record.get(record.size() - 1));
			int previousCases = Integer.parseInt(record.get(record.size() - 2));
			location.setLatestTotalCases(latestCases);
			location.setNumIncreased(latestCases - previousCases);
//		    String state = record.get("Province_State");
			System.out.println(location);
			newStats.add(location);
			
		}
		this.stats = newStats;
		        
	}

}
