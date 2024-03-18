package SumofIntervals;

public class IntervalFusion {

    /*public Interval[] mergeIntervals(Interval[] a, Interval[] b) {
        if (start.length != end.length) return null;
        Interval[] result = new Interval[start.length];
        for (int i = 0; i < start.length; i++) {
            if (OverlapChecker.isOverlap(start[i], end[i])) {
                result[i] = new Interval(start[i].getStart(), end[i].getEnd());
            } else {
                result[i] = null;
            }
        }

        return result;
    }*/

    public Interval merge(Interval a, Interval b) {
        return new Interval(Math.min(a.getStart(), b.getStart()), Math.max(a.getEnd(), b.getEnd()));
    }

}
