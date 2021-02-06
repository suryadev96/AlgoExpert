/*
you have to complete circular path starting from a valid city

distances = [5,25,15,10,15]
fuel = [1,2,1,0,3]
mpg = 10

miles per gallon
*/
import java.util.*;

class Program {

  public int validStartingCity(int[] distances, int[] fuel, int mpg) {
    int total = 0;
		int tank = 0;
		int index = 0;
		
		for (int i=0;i<fuel.length;i++){
			int consume = fuel[i]*mpg - distances[i];
			tank += consume;
			if (tank < 0){
				index = i+1;
				tank = 0;
			}
			total += consume;
		}
    return total < 0 ? -1 : index;
  }
}
