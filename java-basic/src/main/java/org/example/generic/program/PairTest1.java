package org.example.generic.program;

public class PairTest1 {
    public static void main(String[] args) {
        String[] words = {"Mary","hao","little","lamb","a"};
        Pair<String> pair = ArraAlg.minmax(words);
        System.out.println(pair.getFirst());
        System.out.println(pair.getSecond());
    }
}

class ArraAlg{

    public static Pair<String> minmax(String[] a){
        if (a == null || a.length == 0){
            return null;
        }
        String min = a[0];
        String max = a[0];
        for (int i = 0; i< a.length-1; i++){
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
