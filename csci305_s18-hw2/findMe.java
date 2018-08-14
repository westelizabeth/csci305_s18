//Elizabeth West
//CSCI305
//HW3 Q3 Part1

//import packages
import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;
import java.util.*;
import java.lang.*;

public class findMe{
  public static void main(String[] args){
    try{
      int i=0;
      int[] A = new int[args.length];
      for(String x : args){
        A[i] = Integer.parseInt(x);
        i++;
      }
      findMe(A[0], A);
    }catch(NumberFormatException e){
      System.out.println(e.toString());
      System.exit(1);
    }
  }

  //Purpose: find index of key and count comparisons
  private static void findMe(int z, int[] A){
    int i = 1;
    int comparisons = 1;
    while(A[i] != z){
      i += check_index(A, i, z);
      comparisons++;
    }
    print_statements(i, comparisons, z);
  }

  //Purpose: calculates next index to check   Math: i+=z-A[i]
  private static int check_index(int[] A, int current_first_index, int z){
    return z-A[current_first_index];
  }

  //Purpose: Output to user: number of comparisons performed, the index of the key
  private static void print_statements(int index, int comparison, int z){
    System.out.println("Index of key " + z + ":       " + index);
    System.out.println("Number of comparisons: " + comparison);
  }
}
