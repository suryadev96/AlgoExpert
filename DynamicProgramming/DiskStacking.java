/*
You are given disks. These integers denote width,depth and height respectively.
Your goal is to stack up the disks and to maximize the total height of the stack. 
A disk must have a strictly smaller width, depth and height than any other disk below it

Write a function that returns an array of disks in the final stack, starting with the top disk and ending with bottom stack
Note that you can't rotate the disks. You can assume that there will only be one stack with greatest total height
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Question {

    public static List<Integer[]> diskStacking(List<Integer[]> disks) {

        //sort the given disks according to height in ascending order
        disks.sort((disk1, disk2) -> disk1[2].compareTo(disk2[2]));

        //this array stores at each index i , the height of the tallest tower that can be created with the disk i located at bottom
        //this can be populated using dynamic programming
        int[] heights = new int[disks.size()];

        for (int i = 0; i < disks.size(); i++) {
            heights[i] = disks.get(i)[2];
        }

        //instead of storing entire sequences of disks that are formed by tallest tower for each index i , try storing the previous indexes
        //this is basically creating a pointer to the disk above it
        int[] sequences = new int[disks.size()];

        for (int i = 0; i < disks.size(); i++) {
            sequences[i] = Integer.MIN_VALUE;
        }


        //this variable is to store the index i from which tallest tower can be created by having i at the bottom
        int maxHeightIdx = 0;

        //indexing starts from 1 because u cannot create any tower from index 0 because you cannot place anything above it
        //logic is similar to longest increasing subsequence problem
        for (int i = 1; i < disks.size(); i++) {
            Integer[] currentDisk = disks.get(i);

            for (int j = 0; j < i; j++) {
                Integer[] otherDisk = disks.get(j);

                if (areValidDimensions(otherDisk, currentDisk)) {
                    if (heights[i] <= currentDisk[2] + heights[j]) {
                        heights[i] = currentDisk[2] + heights[j];
                        sequences[i] = j;
                    }
                }
            }

            if (heights[i] >= heights[maxHeightIdx]) {
                maxHeightIdx = i;
            }
        }
        return buildSequence(disks, sequences, maxHeightIdx);
    }

    //all dimensions must be strictly increasing
    public static boolean areValidDimensions(Integer[] top, Integer[] bottom) {
        return top[0] < bottom[0] && top[1] < bottom[1] && top[2] < bottom[2];
    }

    public static List<Integer[]> buildSequence(List<Integer[]> disks, int[] sequences, int currentIdx) {

        //disks from top to bottom which forms tallest tower
        List<Integer[]> sequence = new ArrayList<Integer[]>();

        while (currentIdx != Integer.MIN_VALUE) {
            sequence.add(0, disks.get(currentIdx));
            currentIdx = sequences[currentIdx]; //this holds a pointer to the disk above it
        }
        return sequence;
    }

    public static void main(String[] args) {

        List<Integer[]> disks = new ArrayList<Integer[]>();
        disks.add(new Integer[]{2, 1, 2});
        disks.add(new Integer[]{3, 2, 3});
        disks.add(new Integer[]{2, 2, 8});
        disks.add(new Integer[]{2, 3, 4});
        disks.add(new Integer[]{1, 3, 1});
        disks.add(new Integer[]{4, 4, 5});

        List<Integer[]> tallestTower = diskStacking(disks);

        for (Integer[] disk : tallestTower) {
            for (Integer dimension : disk) {
                System.out.print(dimension + " ");
            }
            System.out.println();
        }
    }
}
/*
2 1 2 
3 2 3 
4 4 5 
*/
import java.util.*;

class Program {
    public static List<Integer[]> diskStacking(List<Integer[]> disks) {
        //sort by height
        Collections.sort(disks, (a, b) -> a[2] - b[2]);
        int n = disks.size();
        int[] sequence = new int[n];
        Arrays.fill(sequence, Integer.MIN_VALUE);

        int[] heights = new int[n];
        for (int i = 0; i < n; i++) {
            heights[i] = disks.get(i)[2];
        }

        int maxHeightStackIdx = 0;

        for (int i = 1; i < n; i++) {
            Integer[] bottom = disks.get(i);
            for (int j = 0; j < i; j++) {
                Integer[] top = disks.get(j);
                if (areValidDimensions(bottom, top)) {
                    if (heights[j] + bottom[2] > heights[i]) {
                        heights[i] = heights[j] + bottom[2];
                        sequence[i] = j;
                    }
                }
            }
            if (heights[i] > heights[maxHeightStackIdx]) {
                maxHeightStackIdx = i;
            }
        }
        return getDiskStacking(maxHeightStackIdx, sequence, disks);
    }

    public static List<Integer[]> getDiskStacking(int maxIdx, int[] sequence, List<Integer[]> disks) {
        List<Integer[]> result = new ArrayList<>();
        int i = maxIdx;
        while (i != Integer.MIN_VALUE) {
            result.add(0, disks.get(i));
            i = sequence[i];
        }
        return result;
    }

    public static boolean areValidDimensions(Integer[] bottom, Integer[] top) {
        return top[0] < bottom[0] && top[1] < bottom[1] && top[2] < bottom[2];
    }
}