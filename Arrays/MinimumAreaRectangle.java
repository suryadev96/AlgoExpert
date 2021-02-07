/*
return minimum area of any rectangle that can be formed using any of the 4 points such that rectangle's side are parallel to x and y axes.


points = [
	[1, 5],
    [5, 1],
    [4, 2],
    [2, 4],
    [2, 2],
    [1, 2],
    [4, 5],
    [2, 5],
    [-1, -2]
]

3 

//The rectangle with corners [1,5], [2,5], [1,2] and [2,2] has minimum area 3
*/
import java.util.*;

class Question{

	public int minimumAreaRectangle(int[][] points){

		Set<String> pointSet = createPointSet(points);

		int minArea = Integer.MAX_VALUE;

		for (int i=0;i<points.length;i++){

			int x2 = points[i][0];
			int y2 = points[i][1];

			for (int j=0;j<points.length;j++){
				int x1 = points[j][0];
				int y1 = points[j][1];

				boolean pointsShareValue = x1 == x2 || y1 == y2;

				if (pointsShareValue) continue;

				//if (x1,y2) and (x2,y1) exists then we've found rectangle

				boolean point1OppDiagonal = pointSet.contains(convertPointToString(x1,y2));
				boolean point2OppDiagonal = pointSet.contains(convertPointToString(x2,y1));

				boolean oppDiagonalExists = point1OppDiagonal && point2OppDiagonal;

				if (oppDiagonalExists) {
					int currentArea = Math.abs(x2 - x1) * Math.abs(y2 - y1);
					minArea = Math.min(minArea, currentArea);
				}
			}
		}
		return minArea != Integer.MAX_VALUE ? minArea : 0;
	}

	public String convertPointToString(int x, int y){
		return x + ":" + y;
	}

	public Set<String> createPointSet(int[][] points){
		Set<String> pointSet = new HashSet<>();
		for (int[] point : points){
			int x = point[0];
			int y = point[1];
			String pointString = convertPointToString(x,y);
			pointSet.add(pointString);
		}
		return pointSet;
	}

}