import edu.princeton.cs.algs4.StdOut;

public class Tour {
    private Node first;
    private int n;

    private class Node {
        private Point p;
        private Node next;
    }
    public Tour() {

    }
    public void show() {                // print the tour to standard output
        Node current = first;
        for (int  i = 1; i <= n; i++) {
            StdOut.println(current.p.toString());
            current = current.next;
        }
    }
    public void draw() {            // draw the tour to standard draw
        Node current = first;
        for (int  i = 1; i <= n; i++) {
            current.p.draw();
            current.p.drawTo(current.next.p);
            current = current.next;
        }
    }
    public int size() {                 // number of points on tour
        return n;
    }
    public double distance() {                 // return the total distance of the tour
        Node current = first;
        double distance = 0;
        for (int i = 1; i < n; i++) {
            distance += current.p.distanceTo(current.next.p);
            current = current.next;
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
