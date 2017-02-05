package org.rbsg.java.service;


import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

 
import java.util.LinkedList;
import java.util.List;


 
@Service
public class PrimeNumberService implements  PrimeService {

    
     
    @Override
   // @Cacheable(value = "primes", key = "#upperLimit")
    public List<Integer> getPrimeNumbers(Integer upperLimit) {
    	slowQuery(2000L);
    	System.out.println( "Getting data from SOR befor cachce.********....." );
        List<Integer> primeNumbers = new LinkedList<Integer>();

        if(Integer.valueOf(upperLimit) >= 2) {
            primeNumbers.add(2);
            for(int i = 3; i <= Integer.valueOf(upperLimit); i += 2) {
                if(isPrime(i)){
                    primeNumbers.add(i);
                }
            }
        }
        
        

        return primeNumbers;
    }
    
    private void slowQuery(long seconds){
	    try {
                Thread.sleep(seconds);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
	}

    private boolean isPrime (int n){
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }
    
    
    
    
   
}
