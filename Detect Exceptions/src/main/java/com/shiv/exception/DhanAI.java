package com.shiv.exception;
// Java Program to find the smallest positive missing number
import java.util.*;
public class DhanAI {

    static int solution(int[] a) {
        int N = 1000010;
        boolean[] present = new boolean[N];
        int positiveLostInteger = Integer.MIN_VALUE;
        for (int i = 0; i < a.length; i++) {
            if (a[i] > 0 && a[i] <= a.length)
                present[a[i]] = true;
            positiveLostInteger = Math.max(positiveLostInteger, a[i]);
        }
        for (int i = 1; i < N; i++)
            if (!present[i])
                return i;
        return positiveLostInteger + 1;
    }

    // Driver Code


    //  ["dhan","ai"] ->> 4dhan=2ai=
    public static String specialize(String[] a){
        StringBuilder stringBuilder=new StringBuilder();
        for(String string:a){
            stringBuilder.append(string.length()).append(string).append("=");
        }
        return stringBuilder.toString();
    }

    /**
     * a : [1 3 5]
     * T : 3
     * ->> 5-1 !=3, 3-1 !=3, 5-3!=3 then return no else yes
     * @param a
     * @param T
     * @return
     */
    public static String findSpecialPair(int [] a, int T) {
        /*Given array a of integers and an integer T
          returns yes or no */
        for(int i=a.length-1;i>0;i--){
            for(int j=i-1;j>=0;j--)
                if(a[i]-a[j] == T)
                    return "yes";
        }
        return "no";
    }
    public static void main(String[] args) {
//        int arr[] = { -8,-7,-6};
//        System.out.println(solution(arr));
//        String[] a={"dhan","ai"};
//        System.out.println(specialize(a));
//        int[] a={1,3,5};
//        System.out.println(findSpecialPair(a,4));
//        long index=9;
//        List<Integer> list=new ArrayList<>();
//        list.get((int) index);
        System.out.println(findNumber(13));
    }

    public static long findNumber(long N) {
        if(N==0)
            return N;
        List<Integer> list=new ArrayList<>();
        for(int i=1;list.size()<=N;i++){
            if(i==20)
                i=30;
            if(i%2!=0)
                list.add(i);
        }
        System.out.println(list);
        return list.get((int)N-1);
    }
}

// This code is contributed by Aditya Kumar (adityakumar129)
