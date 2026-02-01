package com.java_9_to_25.newSwitch;

import java.time.Month;
import java.time.Year;

public class NewSwitch {

  public static int getDays(Month month,int year){
    return switch(month){
      case SEPTEMBER,APRIL,JUNE,NOVEMBER -> 30;
      case JANUARY, MARCH , MAY , JULY , AUGUST , OCTOBER , DECEMBER -> 31;
      default -> {
        IO.println("Checking if year " + year + " is leap year or not");
        yield Year.isLeap(year) ? 29 : 28 ;
      }
    };
  }

}