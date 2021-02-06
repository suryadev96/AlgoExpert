/*
2D array containing 0s and 1s.
1 - black; 0 - white

Island is defined as 1's horizontally or vertically adjacent and none of those 1s are in firstrow, firstcolumn, lastcolumn of the matrix

Islands as patches of black that dont touch the border of the two-toned image

Write a function to return the matrix with all of the islands removed.

matrix = [
	[1,0,0,0,0,0],
	[0,1,0,1,1,1],
	[0,0,1,0,1,0],
	[1,1,0,0,1,0],
	[1,0,1,1,0,0],
	[1,0,0,0,0,1]
]


[
	[1,0,0,0,0,0],
	[0,0,0,1,1,1],
	[0,0,0,0,1,0],
	[1,1,0,0,1,0],
	[1,0,0,0,0,0],
	[1,0,0,0,0,1]
]
*/
