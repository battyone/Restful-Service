package org.rbsg.java.model;

import java.io.Serializable;
import java.util.List;


public class PrimesResponse implements Serializable {

    private static final long serialVersionUID = 1746127840105174833L;

    private final Integer initial;
    private final List<Integer> primes;

    public PrimesResponse(Integer initial, List<Integer> primes) {
        this.initial = initial;
        this.primes = primes;
    }


    public Integer getInitial() {
    	
    	System.out.println("## initial: " + initial);
        return initial;
    }

    public List<Integer> getPrimes() {
    	System.out.println("## primes: " + primes);
        return primes;
    }
}
