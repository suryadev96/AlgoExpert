import java.util.*;
class Point{

	int x;
	int y;

	public Point(int x, int y){
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString(){
		return this.x + "-" + this.y;
	}

}

class Question{

	public static int rectangleMania(Point[] coordinates){
		Set<String> coordsTable = getCoordsTable(coordinates);
		return getRectangleCount(coordinates,coordsTable);
	}

	public static Set<String> getCoordsTable(Point[] coordinates){
		Set<String> coordsTable = new HashSet<>();
		for (Point coord : coordinates){
			coordsTable.add(coord.toString());
		}
		return coordsTable;
	}

	public static int getRectangleCount(Point[] coordinates, Set<String> coordsTable){
		int rectangleCount = 0;
		for (Point coord1 : coordinates){
			for (Point coord2 : coordinates){
				if (!isInUpperRight(coord1,coord2))continue;
				String upperCoordString = (new Point(coord1.x,coord2.y)).toString();
				String rightCoordString = (new Point(coord2.x,coord1.y)).toString();
				if (coordsTable.contains(upperCoordString) && coordsTable.contains(rightCoordString)){
					rectangleCount++;
				}
			}
		}
		return rectangleCount;
	}

	public static boolean isInUpperRight(Point coord1, Point coord2){
		return coord2.x > coord1.x && coord2.y > coord1.y;
	}

	public static void main(String[] args){
		Point[] coordinates = new Point[]{
			new Point(0,0),
			new Point(0,1),
			new Point(1,1),
			new Point(1,0),
			new Point(2,1),
			new Point(2,0),
			new Point(3,1),
			new Point(3,0)
		};
		int count = rectangleMania(coordinates);
		System.out.println(count);
	}

	public static void print(Point[] coordinates){
		for (Point p : coordinates){
			System.out.print(p);
		}
	}
}