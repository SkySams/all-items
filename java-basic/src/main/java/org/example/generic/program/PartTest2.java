package org.example.generic.program;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;

public class PartTest2 {

    public static void main(String[] args) {
        LocalDate[] birthdays = {
                LocalDate.of(1906,12,9),
                LocalDate.of(1815,12,10),
                LocalDate.of(1903,12,3),
                LocalDate.of(1910,6,23)
        };
        Pair<ChronoLocalDate> mm = ArraysAlg.minmax(birthdays);
        System.out.println(mm.getSecond());
        System.out.println(mm.getFirst());

    }

}

class ArraysAlg{

    public static <T extends Comparable<T>> Pair<T> minmax(T[] a){
        if (a == null || a.length == 0){
            return null;
        }
        T min = a[0];
        T max = a[0];
        for (int i = 1; i < a.length; i++){
            if (min.compareTo(a[i]) > 0){
                min = a[i];
            }
            if (max.compareTo(a[i]) < 0){
                max = a[i];
            }
        }
        return new Pair<>(min,max);
    }

}
