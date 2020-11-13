/*

Engineering teams at big tech companies often have systems in place that continuosly test their latest code builds.
If all test passes , the latest build is usually said to be green; if some tests fail, the latest build is typically said to 
be broken. 

We call a buildRun a set of consecutive hours during which the latest build is green, then becomes broken.

One meaningful value that engineering teams sometimes care about is the green percentage of a build run; 

return the greatest number of consecutive build runs with strictly decreasing green percentages 

If there are no two consecutive build runs with strictly decreasing green percentages, your function should return -1

{
  "buildRuns": [
    [true, true, true, false, false],
    [true, true, true, true, false],
    [true, true, true, true, true, true, false, false, false],
    [true, false, false, false, false, false],
    [
      true,
      true,
      true,
      true,
      true,
      true,
      true,
      true,
      true,
      true,
      true,
      true,
      false
    ],
    [true, false],
    [true, true, true, true, false, false]
  ]
}
*/
class Program {
    public static int buildFailures(boolean[][] buildRuns) {
        int totalBuilds = buildRuns.length;

        double prev = Integer.MAX_VALUE;

        int currSeqLen = 0;

        int maxSeqLen = -1;

        for (int i = 0; i < totalBuilds; i++) {
            boolean[] currentBuild = buildRuns[i];
            int greens = binarySearch(currentBuild);
            int totalRuns = currentBuild.length;
            double curr = (double) greens / (double) totalRuns * 100;
            if (curr < prev) {
                currSeqLen++;
                maxSeqLen = Math.max(maxSeqLen, currSeqLen);
            } else {
                currSeqLen = 1;
            }
            prev = curr;
        }
        return maxSeqLen == 1 ? -1 : maxSeqLen;
    }

    public static int binarySearch(boolean[] build) {
        int l = 0;
        int r = build.length - 1;

        //before l, all values are true
        //after r, all values are false;

        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (build[mid]) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }
}
