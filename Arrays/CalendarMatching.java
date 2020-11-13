/*
In order to find blocks of time during which you and your co-worker can have a meeting, you have to first find all of the 
unavailabilities between the two of you. An unavailability is any block of time during which at least one of you is busy

you have to start by taking into account the daily bounds; the daily bounds can be represented by two additional meetings in each of your calendars

you will want to merge the two calendars into a single calendar of meetings and then flatten that calendar in order to eliminate
overlappings and back to back meetings . This will give you a calendar of unavailabilities from which you can pretty easily find the list of 
matching availabilities
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class StringMeeting {

    public String start;
    public String end;

    StringMeeting(String start, String end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[" + start + "," + end + "]";
    }
}

class Meeting {
    public int start;
    public int end;

    Meeting(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[" + start + "," + end + "]";
    }
}

class CalendarMatching {

    public static List<StringMeeting> calendarMatching(List<StringMeeting> calendar1, StringMeeting dailyBounds1,
                                                       List<StringMeeting> calendar2, StringMeeting dailyBounds2, int meetingDuration) {


        //add the daily bounds as part of calendar
        List<Meeting> updatedCalendar1 = updateCalendar(calendar1, dailyBounds1);
        List<Meeting> updatedCalendar2 = updateCalendar(calendar2, dailyBounds2);

        List<Meeting> mergedCalendar = mergeCalendars(updatedCalendar1, updatedCalendar2);
        List<Meeting> flattenedCalendar = flattenCalendar(mergedCalendar);

        return getMatchingAvailabilities(flattenedCalendar, meetingDuration);
    }

    public static List<StringMeeting> getMatchingAvailabilities(List<Meeting> calendar, int meetingDuration) {
        List<Meeting> matchingAvailabilties = new ArrayList<Meeting>();

        for (int i = 1; i < calendar.size(); i++) {
            int start = calendar.get(i - 1).end;
            int end = calendar.get(i).start;

            int availabilityDuration = end - start;
            if (availabilityDuration >= meetingDuration) {
                matchingAvailabilties.add(new Meeting(start, end));
            }
        }

        List<StringMeeting> matchingAvailabiltiesInHours = new ArrayList<StringMeeting>();
        for (int i = 0; i < matchingAvailabilties.size(); i++) {
            matchingAvailabiltiesInHours.add(new StringMeeting(minutesToTime(matchingAvailabilties.get(i).start), minutesToTime(matchingAvailabilties.get(i).end)));
        }
        return matchingAvailabiltiesInHours;
    }


    public static List<Meeting> flattenCalendar(List<Meeting> calendar) {
        List<Meeting> flattened = new ArrayList<Meeting>();
        flattened.add(calendar.get(0));

        for (int i = 1; i < calendar.size(); i++) {
            Meeting currentMeeting = calendar.get(i);
            Meeting previousMeeting = flattened.get(flattened.size() - 1);
            //[      [  ]    ]
            if (previousMeeting.end >= currentMeeting.start) {
                Meeting newPreviousMeeting = new Meeting(previousMeeting.start, Math.max(previousMeeting.end, currentMeeting.end));
                flattened.set(flattened.size() - 1, newPreviousMeeting);
            }//[    ] [  ]
            else {
                flattened.add(currentMeeting);
            }
        }
        return flattened;
    }

    //merges two sorted arrays into one array based on starting time of the meetings
    public static List<Meeting> mergeCalendars(List<Meeting> calendar1, List<Meeting> calendar2) {
        List<Meeting> merged = new ArrayList<Meeting>();
        int i = 0;
        int j = 0;

        while (i < calendar1.size() && j < calendar2.size()) {
            Meeting meeting1 = calendar1.get(i);
            Meeting meeting2 = calendar2.get(j);

            if (meeting1.start < meeting2.start) {
                merged.add(meeting1);
                i++;
            } else {
                merged.add(meeting2);
                j++;
            }
        }
        while (i < calendar1.size()) merged.add(calendar1.get(i++));
        while (j < calendar1.size()) merged.add(calendar2.get(j++));
        return merged;
    }

    public static List<Meeting> updateCalendar(List<StringMeeting> calendar, StringMeeting dailyBounds) {
        List<StringMeeting> updatedCalendar = new ArrayList<StringMeeting>();
        updatedCalendar.add(new StringMeeting("0:00", dailyBounds.start));
        updatedCalendar.addAll(calendar);
        updatedCalendar.add(new StringMeeting(dailyBounds.end, "23:59"));

        List<Meeting> calendarInMinutes = new ArrayList<Meeting>();
        for (int i = 0; i < updatedCalendar.size(); i++) {
            calendarInMinutes.add(new Meeting(timeToMinutes(updatedCalendar.get(i).start), timeToMinutes(updatedCalendar.get(i).end)));
        }
        return calendarInMinutes;
    }

    public static String minutesToTime(int minutes) {
        int hours = minutes / 60;
        int mins = minutes % 60;
        String hoursString = Integer.toString(hours);
        String minutesString = mins < 10 ? "0" + Integer.toString(mins) : Integer.toString(mins);
        return hoursString + ":" + minutesString;
    }

    public static int timeToMinutes(String time) {
        int delimiterPos = time.indexOf(':');
        int hours = Integer.parseInt(time.substring(0, delimiterPos));
        int minutes = Integer.parseInt(time.substring(delimiterPos + 1, time.length()));
        return hours * 60 + minutes;
    }

    public static void main(String[] args) {
        List<StringMeeting> calendar1 = new ArrayList<>(Arrays.asList(
                new StringMeeting("9:00", "10:30"),
                new StringMeeting("12:00", "13:00"),
                new StringMeeting("16:00", "18:00")
        ));

        StringMeeting dailyBounds1 = new StringMeeting("9:00", "20:00");

        List<StringMeeting> calendar2 = new ArrayList<>(Arrays.asList(
                new StringMeeting("10:00", "11:30"),
                new StringMeeting("12:30", "14:30"),
                new StringMeeting("14:30", "15:00"),
                new StringMeeting("16:00", "17:00")
        ));

        StringMeeting dailyBounds2 = new StringMeeting("10:00", "18:30");
        int meetingDuration = 30;

        List<StringMeeting> possibleSlots = calendarMatching(calendar1, dailyBounds1, calendar2, dailyBounds2, meetingDuration);
        System.out.println(possibleSlots);
    }
}

//my implemented solution
import java.util.*;

class Meeting {
    public int start;
    public int end;

    public Meeting(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

class Program {
    public static List<StringMeeting> calendarMatching(
            List<StringMeeting> calendar1,
            StringMeeting dailyBounds1,
            List<StringMeeting> calendar2,
            StringMeeting dailyBounds2,
            int meetingDuration) {

        //add daily bounds as part of calendar
        List<Meeting> updatedCalendar1 = addDailyBounds(dailyBounds1, calendar1);
        List<Meeting> updatedCalendar2 = addDailyBounds(dailyBounds2, calendar2);

        List<Meeting> mergedCalendar = mergeCalendars(updatedCalendar1, updatedCalendar2);

        List<Meeting> flattenedCalendar = flattenCalendar(mergedCalendar);
        return findAvailabilities(flattenedCalendar, meetingDuration);
    }

    public static List<StringMeeting> findAvailabilities(List<Meeting> calendar, int duration) {
        List<StringMeeting> result = new ArrayList<>();
        for (int i = 1; i < calendar.size(); i++) {
            int start = calendar.get(i - 1).end;
            int end = calendar.get(i).start;
            if (end - start >= duration) {
                result.add(new StringMeeting(minutesToTime(start), minutesToTime(end)));
            }
        }
        return result;
    }

    public static List<Meeting> flattenCalendar(List<Meeting> calendar) {
        List<Meeting> result = new ArrayList<>();
        Meeting prev = calendar.get(0);

        for (int i = 1; i < calendar.size(); i++) {
            Meeting curr = calendar.get(i);
            if (curr.start > prev.end) {
                result.add(new Meeting(prev.start, prev.end));
                prev = curr;
            } else {
                prev.end = Math.max(prev.end, curr.end);
            }
        }
        result.add(prev);
        return result;
    }

    public static List<Meeting> mergeCalendars(List<Meeting> c1, List<Meeting> c2) {
        int i = 0;
        int j = 0;
        int m = c1.size();
        int n = c2.size();
        List<Meeting> mergedCalendar = new ArrayList<>();

        while (i < m && j < n) {
            Meeting m1 = c1.get(i);
            Meeting m2 = c2.get(j);
            if (m1.start < m2.start) {
                mergedCalendar.add(m1);
                i++;
            } else {
                mergedCalendar.add(m2);
                j++;
            }
        }
        while (i < m) mergedCalendar.add(c1.get(i++));
        while (j < n) mergedCalendar.add(c2.get(j++));
        return mergedCalendar;
    }

    public static List<Meeting> addDailyBounds(StringMeeting dailyBounds, List<StringMeeting> calendar) {
        List<StringMeeting> updatedCalendar = new ArrayList<>();
        updatedCalendar.add(new StringMeeting("0:00", dailyBounds.start));
        updatedCalendar.addAll(calendar);
        updatedCalendar.add(new StringMeeting(dailyBounds.end, "23:59"));

        List<Meeting> minutesCalendar = new ArrayList<>();
        for (StringMeeting meeting : updatedCalendar) {
            minutesCalendar.add(new Meeting(timeToMinutes(meeting.start), timeToMinutes(meeting.end)));
        }
        return minutesCalendar;
    }

    public static int timeToMinutes(String time) {
        int delimPos = time.indexOf(":");
        int hours = Integer.parseInt(time.substring(0, delimPos));
        int minutes = Integer.parseInt(time.substring(delimPos + 1));
        return hours * 60 + minutes;
    }

    public static String minutesToTime(int time) {
        int hours = time / 60;
        int minutes = time % 60;
        String hoursString = String.valueOf(hours);
        String minutesString = minutes < 10 ? "0" + String.valueOf(minutes) : String.valueOf(minutes);
        return hoursString + ":" + minutesString;
    }

    static class StringMeeting {
        public String start;
        public String end;

        public StringMeeting(String start, String end) {
            this.start = start;
            this.end = end;
        }
    }
}

