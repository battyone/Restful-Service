package org.rbsg.java.controller;  
  
import java.util.ArrayList;
import java.util.List;

 

import org.rbsg.java.bean.Country;
import org.rbsg.java.model.PrimesResponse;
import org.rbsg.java.service.PrimeNumberService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
 
 

  


@RestController  
public class MyController {  
	
	
private PrimeNumberService primeService = new PrimeNumberService();


 
   
 @RequestMapping(value = "/countries", method = RequestMethod.GET,headers="Accept=application/json")  
 public List<Country> getCountries()  
 {  
  List<Country> listOfCountries = new ArrayList<Country>();  
  listOfCountries=createCountryList();  
  return listOfCountries;  
 }  
  
 @RequestMapping(value = "/country/{id}", method = RequestMethod.GET,headers="Accept=application/json")  
 public Country getCountryById(@PathVariable int id)  
 {  
  List<Country> listOfCountries = new ArrayList<Country>();  
  listOfCountries=createCountryList();  
  
  for (Country country: listOfCountries) {  
   if(country.getId()==id)  
    return country;  
  }  
    
  return null;  
 }  
 
 @RequestMapping(value = "/primes/{upperLimit}", method = RequestMethod.GET)
 @ResponseBody
 @Cacheable(value = "primes", key = "upperLimit")
 public PrimesResponse getPrimeNumbers(@PathVariable final Integer upperLimit) {
	
	 // Ehcche not woking so my custom cache
	 final PrimesResponse primesResponse; 
	 
	 
	// CacheManager.getInstance().addCache("xyz"); // creates a cache called xyz.
     
	 System.out.println( "Inside my Cache.********....." ); 
	  
	 
	 Cache xyz = CacheManager.getInstance().getCache("primes");
     //Check
	 if (xyz.get(upperLimit)==null) {
	       
	       primesResponse = new PrimesResponse(upperLimit, primeService.getPrimeNumbers(upperLimit));
	       xyz.put(new Element(upperLimit, primesResponse));
	 }else{
		 
		 Element e = xyz.get(upperLimit);
		 primesResponse =  (PrimesResponse)   e.getObjectValue();
	 }
	  
	
	 //final PrimesResponse primesResponse = new PrimesResponse(upperLimit, primeService.getPrimeNumbers(upperLimit));
     return primesResponse;
 }
  
// Utiliy method to create country list.  
 public List<Country> createCountryList()  
 {  
  Country indiaCountry=new Country(1, "India");  
  Country chinaCountry=new Country(4, "China");  
  Country nepalCountry=new Country(3, "Nepal");  
  Country bhutanCountry=new Country(2, "Bhutan");  
  
  List<Country> listOfCountries = new ArrayList<Country>();  
  listOfCountries.add(indiaCountry);  
  listOfCountries.add(chinaCountry);  
  listOfCountries.add(nepalCountry);  
  listOfCountries.add(bhutanCountry);  
  return listOfCountries;  
 }  
}  


