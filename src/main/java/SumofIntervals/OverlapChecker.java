package SumofIntervals;

public class OverlapChecker {
    public boolean isOverlap(Interval a, Interval b) {
        //[1, 3] -  4, 5
        //
        //1 >= 5 && 4 <= 3
        //4 < 3 && 5 < 1
        return a.getStart() >= b.getStart() && a.getStart() <= b.getEnd()
                || a.getEnd() >= b.getStart() && a.getEnd() <= b.getEnd()
                || b.getStart() >= a.getStart() && b.getStart() <= a.getEnd()
                || b.getEnd() >= a.getStart() && b.getEnd() <= a.getEnd();
    }
}
