import edu.princeton.cs.algs4.*;
public class Tour {
    private Point first;
    private int n;

    private class Node {
        private Point p;
        private Node next;
    }
    public Tour() {

    }
    public void show() {                // print the tour to standard output

    }
    public void draw() {            	// draw the tour to standard draw

    }
    public int size() {                 // number of points on tour
        return n;
    }
    public double distance() {                 // return the total distance of the tour
        Point curr = first;
        double distance = 0;
        for (int i = 1; i < n - 1; i++) {
            
        }
        return distance;
    }
    public void insertNearest(Point p) {           // insert p using nearest neighbor heuristic
        n++;
    }
    public void insertSmallest(Point p) {           // insert p using smallest increase heuristic
        n++;
    }

}
