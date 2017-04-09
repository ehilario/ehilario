package com.challenge.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.java.swing.plaf.motif.MotifLabelUI;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.TreeMap;

/**
 * Created by ehilario on 4/7/2017.
 */
@Service
public class Multiples {


    public static final Logger LOGGER = Logger.getLogger(Multiples.class);

    public String findMultiples(int a, int b) throws JsonProcessingException {

        int k3 = 3;
        int k7 = 7;

        int nex3 = 0;
        int nex7 = 0;
        int a3, a7;
        a3 = a7 = a;
        TreeMap<Integer,String> hm = new TreeMap<>();
        //start
        long lStartTime = System.nanoTime();
        do {
            nex3 =  ((1 + (a3 / k3)) * k3);
            if(nex3 <= b && nex3 % 3 == 0) {
                //System.out.print(" 3 - " + nex3 + " : ");
                if (!hm.containsKey(nex3))
                    hm.put(nex3, "ME");
                else {
                    hm.put(nex3, "MS3 and ME");
                }
            }
            if(nex7 <= b && nex7 % 7 == 0) {
                nex7 = ((1 + (a7 / k7)) * k7);
               // System.out.print(" 7 - " + nex7);
                a7 = nex7;
                if(nex7 <= b) {
                    if (!hm.containsKey(nex7))
                        hm.put(nex7, "MS3");
                    else {
                        hm.put(nex7, "MS3 and ME");
                    }
                }
            }
            a3 = nex3;
            //System.out.println();
        }while(nex3 < b);
        //end
        long lEndTime = System.nanoTime();
        //time elapsed
        long output = lEndTime - lStartTime;
        //System.out.println("Elapsed time in milliseconds: " + output / 1000000);
        LOGGER.debug("Elapsed time in milliseconds: " + output / 1000000);

        String sl =  hm.toString();

        String jSonObject = new ObjectMapper().writeValueAsString(hm);
        return jSonObject;

    }
}
