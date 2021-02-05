import java.util.ArrayList;
import java.util.List;

/*
function that takes main string and potential string of the main string. function should return a version of the main string
with every instance of the substring in it wrapped between underscores.
if two or more instances of the substring in main string overlap each other or sit side by side , underscores relevant to these substrings
should only appear on the far left of the leftmost substring and on the far right of the rightmost substring
*/
class UnderscorifySubstring(){

public static String underscorifySubstring(String str,String substring){
        List<Integer[]>locations=collapse(getLocations(str,substring));
        return underscorify(str,locations);
        }

//get the locations of all instances of the substring in the main string
public static List<Integer[]>getLocations(String str,String substring){
        List<Integer[]>locations=new ArrayList<Integer[]>();

        int startIdx=0;
        while(startIdx<str.length()){
        //finds out the next substring present in the string starting from the startIdx
        int nextIdx=str.indexOf(substring,startIdx);
        if(nextIdx!=-1){
        locations.add(new Integer[]{nextIdx,nextIdx+substring.length()});
        startIdx=nextIdx+1;
        }else{ //if u do not find any substring starting from startIdx; that means we have completed searching
        break;
        }
        }
        return locations;
        }

//collapse will the merge the locations of the substrings that overlap each other or sit next to each other
public static List<Integer[]>collapse(List<Integer[]>locations){
        if(locations.size()==0){
        return locations;
        }
        List<Integer[]>newLocations=new ArrayList<Integer[]>();
        newLocations.add(locations.get(0));
        Integer[]previous=locations.get(0);
        for(int i=1;i<locations.size();i++){
        Integer[]current=locations.get(i);
        //[    [  ]   ]
        //testtest => previous[1] = current[0] (sit next to each other)
        //testest => previous[1] > current[0]  (overlapping)
        if(current[0]<=previous[1]){
        previous[1]=current[1];
        }else{
        newLocations.add(current);
        previous=current;
        }
        }
        return newLocations;
        }

public static String underscorify(String str,List<Integer[]>locations){

        //iterates over the locations
        int locationsIdx=0;
        //iterates over the string
        int stringIdx=0;

        StringBuilder sb=new StringBuilder();

        boolean inBetweenUnderscores=false;
        int i=0;
        while(stringIdx<str.length()&&locationsIdx<locations.size()){
        if(stringIdx==locations.get(locationsIdx)[i]){
        sb.append("_");
        inBetweenUnderscores=!inBetweenUnderscores;
        if(!inBetweenUnderscores){
        locationsIdx++;
        }
        i=i==1?0:1;
        }
        sb.append(str.charAt(stringIdx));
        stringIdx++;
        }

        //this is the corner case where last substring ends with the last character of the string
        if(locationsIdx<locations.size()){
        sb.append("_");
        }// this is the case when we have completed placing all the underscores for the substrings present in the string
        else if(stringIdx<str.length()){
        sb.append(str.substring(stringIdx));
        }
        return sb.toString();
        }

public static void main(String[]args){
        String mainString="testthis is a testtest to see if testestest it works";
        String substring="test";
        String underscorify=underscorifySubstring(mainString);
        System.out.println(underscorify);
        }

        }

/*
_test_this is a _testtest_ to see if _testestest_ it works
*/

//my solution
import java.util.*;

class Interval {
    int start;
    int end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

class Program {
    public static String underscorifySubstring(String str, String substring) {
        List<Interval> locations = getLocations(str, substring);
        List<Interval> intervals = flattenIntervals(locations);
        return underscorify(str, intervals);
    }

    public static String underscorify(String s, List<Interval> intervals) {
        StringBuilder sb = new StringBuilder();
        int prev = 0;
        for (Interval interval : intervals) {

            sb.append(s.substring(prev, interval.start));

            sb.append("_");

            sb.append(s.substring(interval.start, interval.end));

            sb.append("_");

            prev = interval.end;
        }

        if (prev < s.length()) {
            sb.append(s.substring(prev));
        }
        return sb.toString();
    }

    public static List<Interval> flattenIntervals(List<Interval> intervals) {
        List<Interval> result = new ArrayList<>();

        if (intervals.isEmpty()) return result;
        Interval prev = intervals.get(0);

        for (int i = 1; i < intervals.size(); i++) {
            Interval current = intervals.get(i);
            if (current.start > prev.end) {
                result.add(prev);
                prev = current;
            } else {
                prev.end = Math.max(current.end, prev.end);
            }
        }
        result.add(prev);
        return result;
    }

    public static List<Interval> getLocations(String text, String pat) {
        List<Interval> intervals = new ArrayList<>();
        int index = text.indexOf(pat, 0);
        while (index != -1) {
            intervals.add(new Interval(index, index + pat.length()));
            index = text.indexOf(pat, index + 1);
        }
        return intervals;
    }
}
