package it.polito.tdp.borders.model;

import java.util.HashMap;
import java.util.Map;

public class CountryIdMap {
	
	private Map<Integer, Country> map;

	/**
	 * 
	 */
	public CountryIdMap() {
		map=new HashMap<Integer, Country>();
	} 
	
	public Country get(Country country) {
		Country old= map.get(country.getCode());
		if(old==null) {
			map.put(country.getCode(), country);
			return country;  
		}
		return old; 
	}
	
	public Country get(int code) {
		return map.get(code); 
	}

}
