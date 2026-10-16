import edu.princeton.cs.algs4.*;
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
    public void insertNearest(Point p) {  // insert p using nearest neighbor heuristic
    	Node newNode = new Node();
        newNode.p = p;
        if (first == null) {
            newNode.next = null;
            first = newNode;
            n++;
            return;
        }
        Node closest = first;
        double Dist = first.p.distanceTo(p);
        Node current = first.next;
        while (current != null) {
            double d = current.p.distanceTo(p);
            if (d < Dist) {
                Dist = d;
                closest = current;
            }
            current = current.next;
        }
        newNode.next = closest.next;
        closest.next = newNode;
        n++;
    }
    public void insertSmallest(Point p) { // insert p using smallest increase heuristic
         Node step = new Node();
        Node curr = new Node();
        Node oldNext = new Node();
        curr = first;
        step = first.next; 
        step.p = p;
        first.next = step;
        double minDistance = distance();
        int index = 0;

        for(int i = 0; i < n; i++) {
              oldNext = step.next;
             step = step.next;
             curr.next.next = oldNext;
             curr = curr.next;
             double newDistance = distance();
             if (newDistance < minDistance){
                minDistance = newDistance;
                index = i;
             }
        }
        curr = first;
        for(int j = 0; j < index; j++) {
            curr = curr.next;
        }
        oldNext = curr;
        curr.next = step;
        step = oldNext;
        n++;
    }

}
