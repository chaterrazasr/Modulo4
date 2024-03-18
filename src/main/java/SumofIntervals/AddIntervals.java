package SumofIntervals;

import java.util.List;

public class AddIntervals {
    public int add(List<Interval> intervals) {
        int sum = 0;
        for (Interval interval : intervals) {
            sum += interval.length();
        }
        return sum;
    }




}
