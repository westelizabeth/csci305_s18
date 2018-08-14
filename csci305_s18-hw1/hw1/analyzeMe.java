//import packages
import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;
import java.util.*;
import java.lang.*;

public class analyzeMe{
  public static void main(String[] args){
    try{
      //Checking parameters
      //Accepted Parameters: LS=integer integer integer or IS integer integer
      String search = args[0];
      if(!isInt(args[1]) || !isInt(args[2]) || !isFirstArg(args[0])){
        System.out.println("Usage Error!");
        System.exit(1);
      }
      else{
        int[] originalArray = createArray(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
        int p;
        //Insertion Sort
        if(parseFirstArgs(args[0]) == 0){
          if(Integer.parseInt(args[1]) <= 20){
            printSmallArray(originalArray, "A initial = ");
          }
          p = insertionSort(originalArray);
          System.out.println("Num Swaps : " + p);
        }
        //Linear Search
        else{
          if(Integer.parseInt(args[1]) <= 20){
            printSmallArray(originalArray, "A = ");
          }
          String[] parts = args[0].split("=");
          int target = Integer.parseInt(parts[1]);
          p = linearSearch(originalArray, target);
          if(p == -1){
            System.out.println("Entries Searched : not found");
          }
          else{
            System.out.println("Entries Searched : " + p);
          }
        }
      }
    }catch(ArrayIndexOutOfBoundsException e){
      System.out.println("Usage Error!");
      System.exit(1);
    }
  }

  //Purpose: Create original array, taking into account the degree of "sortedness" specified by the user
  //Pre-conditions: size, range both determined by user. size is array length, range is degree of sortedness
  //Post-conditions: Depending on the range, the array will be fully sorted, completely unsorted, or partially
  //sorted using a random number generator with specific bounds
  public static int[] createArray(int size, int range){
    int[] randomizedArray = new int[size];
    //backwards array
    if(range == 0){
      int tempSize= size;
      for(int i = 0; i < size; i++){
        randomizedArray[i]= tempSize;
        tempSize--;
      }
      return randomizedArray;
    }

    for(int i = 0; i < size; i++){
      randomizedArray[i]= i+1;
    }
    //return perfectly sorted
    if(range == 100){
      return randomizedArray;
    }
    //Randomize
    Double d = 100.0-range;
     d = Math.floor((d/100)*size);
    int randomize = d.intValue();
    Random randIndex = new Random();
    Random randNum = new Random();

    for(int i = 0; i < randomize; i++){
      int randomIndex = randIndex.nextInt((size-1) +1);
      int randomNum = randNum.nextInt((size*2) - (size+1) +1)+(size+1);
      if(randomizedArray[randomIndex] < size ){
        randomizedArray[randomIndex]= randomNum;
      }
    }
    return randomizedArray;
  }

  //Purpose: Determine if the first arg is insertion sort vs linear search
  public static int parseFirstArgs(String str){
    if(str.equals("IS")){
      return 0;
    }
    return 1;
  }

  //Purpose: Validate first parameter of main args
  public static boolean isFirstArg(String str) {
    try {
      if(str.equals("IS")){
        return true;
      }
      String[] parts = str.split("=");
      if((str.substring(0,3).equals("LS=")) && isInt(parts[1])){
        return true;
      }
      return false;
    }catch(ArrayIndexOutOfBoundsException e){
      return false;
    }
  }

  //Purpose: Validate if string is an int
  public static boolean isInt(String str) {
    try {
      Integer.parseInt(str);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
}

  //Purpose: this method is a sorting algorithm that sorts one item at a time to create a sorted array
  //Pre-conditions: It must have an array as it's input, the unsorted array to be sorted
  //Post-conditions: It sorts the array from smallest item to larges item so in the end you are left with a sorted array
  public static int insertionSort(int[] array){
    int swap = 0;
     for(int i = 0; i < array.length; i++){
        int j = i;
        while(j>0 && array[j-1]>array[j]){
           int temp =array[j-1];
           array[j-1] = array[j];//swap
           swap ++;
           array[j] = temp;
           j--;
        }
     }
     if(array.length <= 20){
       printSmallArray(array, "A final = ");
     }
     return swap;
  }

  //Purpose: Check if target is in an array
  public static int linearSearch(int[] array, int search){
    for(int i = 0; i < array.length; i++){
      if(array[i] == search){
        return i + 1;
      }
    }
    return -1;
  }

  //Purpose: Print out an array
  public static void printSmallArray(int[] smallArray, String statement){
    System.out.print(statement);
    for(int k = 0; k < smallArray.length; k++){
      System.out.print(smallArray[k] + " ");
    }
    System.out.println();
  }
}
