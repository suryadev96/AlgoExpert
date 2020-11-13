/*
Minimise the farthest distance you would have to walk from your apartment to reach all of the required buildings

[{
	"gym": False,
	"school":True,
	"store":False
},
{

}
]
["gym","school","store"] -> requirements
*/

import java.util.Arrays;
import java.util.List;
import java.util.Map;

class ApartmentHunting {

    //Brute Force solution
    public static int apartmentHunting(List<Map<String, Boolean>> blocks, String[] requirements) {
        int[] maxDistanceAtBlocks = new int[blocks.size()];
        Arrays.fill(maxDistanceAtBlocks, Integer.MIN_VALUE);
        for (int i = 0; i < blocks.size(); i++) {
            for (String requirement : requirements) {
                int closestReqDistance = Integer.MAX_VALUE;
                for (int j = 0; j < blocks.size(); j++) {
                    if (blocks.get(j).get(requirement)) {
                        closestReqDistance = Math.min(closestReqDistance, distanceBetween(i, j));
                    }
                }
                maxDistanceAtBlocks[i] = Math.max(maxDistanceAtBlocks[i], closestReqDistance);
            }
        }
        return getIdxAtMinValue(maxDistanceAtBlocks);
    }

    public static int getIdxAtMinValue(int[] array) {
        int idxAtMinValue = 0;
        int minValue = Integer.MAX_VALUE;
        for (int i = 0; i < array.length; i++) {
            int currentValue = array[i];
            if (currentValue < minValue) {
                minValue = currentValue;
                idxAtMinValue = i;
            }
        }
        return idxAtMinValue;
    }

    public static int distanceBetween(int i, int j) {
        return Math.abs(i - j);
    }


    //Algo Expert solution
    public static int apartmentHuntingEff(List<Map<String, Boolean>> blocks, String[] requirements) {
        //		 block0  block1 block2
        //gym     2       0       2
        //store   1       2       0
        //school  2       1       0
        int[][] minReqsDistanceFromBlocks = new int[requirements.length][];
        for (int i = 0; i < requirements.length; i++) {
            minReqsDistanceFromBlocks[i] = getMinReqDistance(blocks, requirements[i]);
        }

        int[] maxDistanceAtBlocks = getMaxDistanceAtBlocks(blocks, minReqsDistanceFromBlocks);
        return getIdxAtMinValue(maxDistanceAtBlocks);
    }

    public static int[] getMaxDistanceAtBlocks(List<Map<String, Boolean>> blocks, int[][] minReqsDistanceFromBlocks) {
        int[] maxDistanceAtBlocks = new int[blocks.size()];

        for (int i = 0; i < blocks.size(); i++) {

            int[] minReqDistanceAtBlocks = new int[minReqsDistanceFromBlocks.length];

            for (int j = 0; j < minReqsDistanceFromBlocks.length; j++) {
                minReqDistanceAtBlocks[j] = minReqsDistanceFromBlocks[j][i];
            }

            maxDistanceAtBlocks[i] = arrayMax(minReqDistanceAtBlocks);
        }
        return maxDistanceAtBlocks;
    }

    public static int[] getMinReqDistance(List<Map<String, Boolean>> blocks, String requirement) {
        int[] minDistance = new int[blocks.size()];
        int closestReqIdx = Integer.MAX_VALUE;

        //left to right
        for (int i = 0; i < blocks.size(); i++) {
            if (blocks.get(i).get(requirement)) closestReqIdx = i;
            minDistance[i] = distanceBetween(i, closestReqIdx);
        }

        //right to left
        for (int i = blocks.size() - 1; i >= 0; i--) {
            if (blocks.get(i).get(requirement)) closestReqIdx = i;
            minDistance[i] = Math.min(minDistance[i], distanceBetween(i, closestReqIdx));
        }
        return minDistance;
    }

    public static int arrayMax(int[] array) {
        int max = array[0];
        for (int a : array) {
            if (a > max) {
                max = a;
            }
        }
        return max;
    }

    //My solution
    public static int apartmentHuntingMySol(List<Map<String, Boolean>> blocks, String[] reqs) {
        int[][] reqIndex = buildIndex(blocks, reqs);
        return huntApartment(reqIndex);
    }

    public static int huntApartment(int[][] reqIndex) {
        int m = reqIndex.length;
        int n = reqIndex[0].length;

        int[] tdist = new int[n];

        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                tdist[j] = Math.max(tdist[j], reqIndex[i][j]);
            }
        }
        return getIdxAtMinValue(tdist);
    }

    public static int[][] buildIndex(List<Map<String, Boolean>> blocks, String[] reqs) {
        int m = reqs.length;
        int n = blocks.size();
        int[][] index = new int[m][n];

        for (int i = 0; i < m; i++) {
            index[i] = getMinDistanceLineSweep(blocks, reqs[i]);
        }
        return index;
    }

    public static int[] getMinDistanceLineSweep(List<Map<String, Boolean>> blocks, String req) {

        int[] minDist = new int[blocks.size()];

        int closestReqIdx = Integer.MAX_VALUE;

        //left sweep
        for (int i = 0; i < blocks.size(); i++) {
            Map<String, Boolean> map = blocks.get(i);
            if (map.get(req)) closestReqIdx = i;
            minDist[i] = distanceBetween(i, closestReqIdx);
        }

        //right sweep
        for (int i = blocks.size() - 1; i >= 0; i--) {
            Map<String, Boolean> map = blocks.get(i);
            if (map.get(req)) closestReqIdx = i;
            minDist[i] = Math.min(minDist[i], distanceBetween(i, closestReqIdx));
        }

        return minDist;
    }

    /*
    blocks :
    [
      {"gym": false, "school": true, "store": false},
      {"gym": true, "school": false, "store": false},
      {"gym": true, "school": true, "store": false},
      {"gym": false, "school": true, "store": false},
      {"gym": false, "school": true, "store": true}
    ]

    Reqs : ["gym", "school", "store"]
     */

}









