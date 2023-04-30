package com.shiv.exception.research.dsa;

public class SortingAlgorithms {
    public static void main(String[] args) {
        int[] arr={7,8,3,1,2,5,1,9,4};
        for(int i=0;i<arr.length;i++)
            System.out.print(arr[i]+" ");
        System.out.println();
//        sortByBubbleSort(arr);
       // sortBySelectionSort(arr);
//        sortBySelectionSort(arr);
        reverseArray(arr);
        for(int i=0;i<arr.length;i++)
            System.out.print(arr[i]+" ");
    }

    /**
     * reverse the array
     * @param arr
     */
    public static void reverseArray(int[] arr){
        int start=0;
        int end=arr.length-1;
        while (start<=end){
            swap(arr,start,end);
            start++;
            end--;
        }
    }

    /**
     * there are two parts sorted and unsorted part
     * compare each unsorted element with sorted element
     * if
     * @param arr 7 8 3 1 2
     */
    public static void sortByInsertionSort(int arr[]){
        // consider 0th index as sorted part
        for(int i=1;i<arr.length;i++){
            int current=arr[i]; // current element of unsorted part
            int j=i-1; // last element of sorted part
            while(j>=0 && current<arr[j]){
                arr[j+1]=arr[j];
                j--;
            }
            // placed
            arr[j+1]=current;
        }
    }

    /**
     * Bubble sorting algorithm
     * in each satisfied condition we have to swap
     * last i index rages has already sorted
     * @param arr
     */
    public static void sortByBubbleSort(int arr[]){
        for(int i=0;i<arr.length-1;i++)
            for(int j=0;j<arr.length-i-1;j++)
                if(arr[j]>arr[j+1])
                    swap(arr,j,j+1);
    }

    public static void swap(int []arr,int firstIndex,int secondIndex){
        int temp=arr[firstIndex];
        arr[firstIndex]=arr[secondIndex];
        arr[secondIndex]=temp;
    }

    /**
     * selection sort
     * after finding the smallest element in the array
     * then swap with initial index, not swapping in each condition
     * @param arr
     */
    public static void sortBySelectionSort(int arr[]){
        for(int i=0;i<arr.length;i++){
            int smallest=i;
            for(int j=i;j<arr.length;j++)
                if(arr[smallest]>arr[j])
                    smallest=j;
            swap(arr,smallest,i);
        }
    }
}
