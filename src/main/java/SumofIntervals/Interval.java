package SumofIntervals;

public class Interval {
    private int start;
    private int end;

    public Interval(int start, int end) {
        if (start > end) {
            throw new IllegalArgumentException("Start cannot be greater than end");
        }
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public int length() {
        return end - start;
    }
}
