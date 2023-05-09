package com.shiv.exception.be;

import java.util.Arrays;

public class TestBirdEye {

    /**
     * getting most recent repeated element indices
     * at 0 index has beginIndex
     * at 1 index has endIndex
     * @param arr
     * @return
     */
    public static int[] getMostRepeatedElementIndices(int[] arr){
        int[] tempArray={1,-1,-1};
        int beginIndex=0;
        int counter=1;
        for(int i=0;i<arr.length-1;i++){
            if(arr[i]!=arr[i+1]){
                if(counter>tempArray[0]){
                    tempArray[0]=counter;
                    tempArray[1]=beginIndex;
                    tempArray[2]=i;
                }else {
                    counter=1;
                    beginIndex=i+1;
                }
            }else{
                counter++;
                if(i+1==arr.length-1 && counter>tempArray[0]){
                    tempArray[0]=counter;
                    tempArray[1]=beginIndex;
                    tempArray[2]=i+1;
                }
            }
        }
        return tempArray;
    }

    public static void main(String[] args) {
//        int[] array={1,2,4,5,3,2,3,3,3,4,5,4,4};
        int[] array={2,2,2,2,1,2,3,4,4,4,4,4};
        System.out.println(Arrays.toString(array));
        int[] result=getMostRepeatedElementIndices(array);
        System.out.println("BeginIndex-"+result[1]);
        System.out.println("EndIndex-"+result[2]);
    }
}
