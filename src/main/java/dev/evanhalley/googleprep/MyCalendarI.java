package dev.evanhalley.googleprep;

import java.util.Map;
import java.util.TreeMap;

public class MyCalendarI {

    public static void main(String[] args) {
        MyCalendar cal = new MyCalendar();
        System.out.println(cal.book(10, 20));
        System.out.println(cal.book(15, 25));
        System.out.println(cal.book(20, 30));
        System.out.println(cal.book(30, 300));
        System.out.println(cal.book(0, 1));
    }

    public static class MyCalendar {

        final TreeMap<Integer, Integer> meetings;

        public MyCalendar() {
            meetings = new TreeMap<>();
        }

        public boolean book(int start, int end) {

            if (doesMeetingOverlapStart(start) || doesMeetingOverlapStartAndEnd(start, end) || doesMeetingOverlapEnd(end)) {
                return false;
            }
            meetings.put(start, end);
            return true;
        }

        private boolean doesMeetingOverlapStart(int start) {
            Meeting priorMeeting = getPriorMeeting(start);
            return priorMeeting != null && priorMeeting.end > start;
        }

        private boolean doesMeetingOverlapStartAndEnd(int start, int end) {
            Meeting meeting = getConcurrentMeeting(start);
            return meeting != null && meeting.start >= start && meeting.end <= end;
        }

        private boolean doesMeetingOverlapEnd(int end) {
            Meeting meeting = getPriorMeeting(end);
            return meeting != null && meeting.start < end && meeting.end > end;
        }

        private Meeting getPriorMeeting(int time) {
            Map.Entry<Integer, Integer> entry = meetings.floorEntry(time);
            Meeting priorMeeting = null;

            if (entry != null) {
                priorMeeting = new Meeting(entry);
            }
            return priorMeeting;
        }

        private Meeting getConcurrentMeeting(int time) {
            Map.Entry<Integer, Integer> entry = meetings.ceilingEntry(time);
            Meeting priorMeeting = null;

            if (entry != null) {
                priorMeeting = new Meeting(entry);
            }
            return priorMeeting;
        }

        public static class Meeting {
            final int start;
            final int end;

            public Meeting(Map.Entry<Integer, Integer> entry) {
                this.start = entry.getKey();
                this.end = entry.getValue();
            }

            public String toString() {
                return start + " - " + end;
            }
        }

    }

}
