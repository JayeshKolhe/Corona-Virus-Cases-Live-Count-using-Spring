package io.javabrains.coronavirustracker;

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
@Service
public class coronavirusDataservices {

	
	private static String VirusDataUrl="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	private List<Models_locationStats> allstats =new ArrayList<>();
	
	

	




	public List<Models_locationStats> getAllstats() {
		return allstats;
	}








	public void setAllstats(List<Models_locationStats> allstats) {
		this.allstats = allstats;
	}








	@PostConstruct
	@Scheduled(cron="* * 1 * * *")
	public void fetchData() throws IOException, InterruptedException
	{
		 List<Models_locationStats> newstats =new ArrayList<>();
		HttpClient client= HttpClient.newHttpClient();  
		HttpRequest request= HttpRequest.newBuilder()
		.uri(URI.create(VirusDataUrl))
	 	.build();
		HttpResponse<String> httpResponse = client.send(request,HttpResponse.BodyHandlers.ofString());
		//System.out.println(httpResponse.body());
		
		
		StringReader csvBodyReader=new StringReader(httpResponse.body());
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
		
		for (CSVRecord record : records) {
			Models_locationStats locationstats=new Models_locationStats();
		    locationstats. setState(record.get("Province/State"));
		    locationstats. setCountry(record.get("Country/Region"));
		   int latestCases= Integer.parseInt(record.get(record.size()-1));
		   int previousDayCases= Integer.parseInt(record.get(record.size()-2));

		    locationstats. setLatestTotalcases(latestCases);
		    locationstats. setDiffFromPrevDay(latestCases-previousDayCases);
		    //System.out.println(locationstats);
		    newstats.add(locationstats);
		    this.allstats=newstats;


		    
		    
		    
		}
		
	}
}
