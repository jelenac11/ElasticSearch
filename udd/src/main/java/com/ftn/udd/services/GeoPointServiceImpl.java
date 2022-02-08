package com.ftn.udd.services;

import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class GeoPointServiceImpl implements GeoPointService {

	@Override
	public GeoPoint getGeoPoint(String city, String country) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
        String uri = "https://nominatim.openstreetmap.org/search?q=" + removeWhitespace(city) + "," + removeWhitespace(country) + "&format=json";
        ResponseEntity<String> response;
        try {
            response = restTemplate.getForEntity(uri, String.class);
        } catch (RestClientException e) {
            response = null;
        }
        if (response != null && response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                JsonNode root = mapper.readTree(response.getBody());
                JsonNode oneNode = root.get(0);
                if(oneNode != null){
                    String lat = oneNode.path("lat").asText();
                    String lon = oneNode.path("lon").asText();
                    if(!lat.isEmpty() && !lon.isEmpty()){
                        return new GeoPoint(Double.parseDouble(lat),Double.parseDouble(lon));
                    }
                }
            } catch (Exception e) {
                throw new Exception("Non existing location. Please, try again.");
            }

        }
        return null;
	}
	
	private String removeWhitespace(String word) {
		return word.replaceAll(" ", "+");
	}

	

}
