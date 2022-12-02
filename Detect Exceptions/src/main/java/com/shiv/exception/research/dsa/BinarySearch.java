package com.shiv.exception.research.dsa;

public class BinarySearch {

    /**
     * search via binary tree search
     * @param values array of values
     * @param search value will search
     * @return search value index
     */
    public static int binarySearch(int[] values,int search){
        int low=0;
        int high=values.length-1;
        while (low<=high){
            int mid=(low+high)/2;
            if(values[mid]==search)
                return mid;
            if(values[mid]<search)
                low=mid+1;
            else
                high=mid-1;
        }
        return -1;
    }

    /**
     * search given value and if his previous has value then return it else return index of searched value
     * @param values
     * @param search
     * @return
     */
    public static int binarySearchBeforeValue(int[] values,int search){
        int low=0;
        int high=values.length-1;
        while (low<=high){
            int mid=(low+high)/2;
            if(values[mid]==search)
                return mid==0?mid:values[mid-1];
            if(values[mid]<search)
                low=mid+1;
            else
                high=mid-1;
        }
        return -1;
    }

    /**
     * search given value and if his next has value then return it else return index of searched value
     * @param values
     * @param search
     * @return
     */
    public static int binarySearchAfterValue(int[] values,int search){
        int low=0;
        int high=values.length-1;
        while (low<=high){
            int mid=(low+high)/2;
            if(values[mid]==search)
                return mid==values.length?mid:values[mid+1];
            if(values[mid]<search)
                low=mid+1;
            else
                high=mid-1;
        }
        return -1;
    }


    public static void main(String[] args) {
        int[] values={11,13,16,20,21,22,23,45,51};
        int index=binarySearchBeforeValue(values,11);
        System.out.println(index);
    }
}
