/* COP 3503C Assignment 1
This program is written by: Juan Velasquez */

import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int numOfTestCases = sc.nextInt();
        int sorted = -1;

        // Run this for loop for however many testcases we have
        for (int i = 0; i < numOfTestCases; i++) {
            // Determine whether we are operating on a sorted list or an unsorted list
            sorted = sc.nextInt();

            // Scan in the array of values we are going operate on to see if 2 in the list
            // add up to the target
            int[] arr = new int[sc.nextInt()];
            for (int j = 0; j < arr.length; j++) {
                arr[j] = sc.nextInt();
            }
            // Scan in the target
            int target = sc.nextInt();

            // Create the result array that we store the pair of values in
            int[] res = new int[2];

            // The case if the array is sorted
            if (sorted == 1) {
                res = getCandidatePair(arr, target);
            }
            // The case if the array is unsorted
            else if (sorted == 0) {
                res = findComplementsUnsorted(arr, target);
            }

            // The case where 2 games dont exist in the list that add up to target
            if (res[0] == 0 && res[1] == 0) {
                System.out.println("Test case#" + (i + 1) + ": No way you can spend exactly " + target + " points.");
            }
            // The case where we got a valid pair
            else {
                System.out.println("Test case#" + (i + 1) + ": Spend " + target + " points by playing the games with "
                        + res[0] + " points and " + res[1] + " points.");
            }
        }
    }

    // Sorted Case
    public static int[] getCandidatePair(int A[], int target) {
        // result's value is assigned to 0, 0 in the case we don't find a pair
        int[] result = { 0, 0 };
        // indices of highest and lowest values in the array
        int lowest = 0;
        int highest = A.length - 1;
        /*
         * Loop for the length of the array since the max value that lowest can reach is
         * A.length-2 and the min value that highest can reach is 0 + 1, or simply 1.
         */
        for (int i = 0; i < A.length; i++) {
            /*
             * If the highest value plus the lowest value in the list are lower than target,
             * then the lowest value is too low since the value the lowest one needs in the
             * list
             * to add up to target doesnt exist in the list. Therefore, increment the lowest
             * index by 1.
             */
            if (A[highest] + A[lowest] < target) {
                lowest++;
            }
            /*
             * If the highest value plus the lowest value in the list are higher than
             * target,
             * then the highest value is too large since the value the highest one needs in
             * the list
             * to add up to target doesnt exist in the list. Therefore, decrement the
             * highest index by 1.
             */
            else if (A[highest] + A[lowest] > target) {
                highest--;
            }
            // We found our valid pair
            else if (A[highest] + A[lowest] == target) {
                result[0] = A[lowest];
                result[1] = A[highest];
                return result;
            }
        }
        // If the entire for loop executes without finding a pair, return the base case
        // of 0,0.
        return result;
    }

    // Unsorted Case
    public static int[] findComplementsUnsorted(int A[], int target) {
        // result's value is assigned to 0, 0 in the case we don't find a pair
        int[] result = { 0, 0 };
        // Create a hashset of type integer to store what values we have already
        // encountered in the list.
        HashSet<Integer> pairsComplement = new HashSet<>();
        /*
         * Loop through the list and check whether the target - value at index has been
         * encountered
         * by using the hashset. If so, then return the valid pair. Else, add the value
         * at the index
         * to the hashset.
         */
        for (int i = 0; i < A.length; i++) {
            if (pairsComplement.contains(target - A[i]) == false) {
                pairsComplement.add(A[i]);
            } else {
                if (target - A[i] < A[i]) {
                    result[0] = target - A[i];
                    result[1] = A[i];
                } else {
                    result[1] = target - A[i];
                    result[0] = A[i];
                }
                return result;
            }
        }
        // If the entire for loop executes without finding a pair, return the base case
        // of 0,0.
        return result;
    }
}
