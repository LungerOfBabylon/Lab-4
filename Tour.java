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
    public void show() { // print the tour to standard output
        Node current = first;
        for (int  i = 1; i <= n; i++) {
            StdOut.println(current.p.toString());
            current = current.next;
        }
    }
    public void draw() { // draw the tour to standard draw
        Node current = first;
        for (int  i = 1; i < n; i++) {
            current.p.draw();
            if (current.next != null) {
                current.p.drawTo(current.next.p);
            }
            else {
                current.p.drawTo(first.p);
            }
            current = current.next;
        }
    }
    public int size() { // number of points on tour
        return n;
    }
    public double distance() { // return the total distance of the tour
        Node current = first;
        double distance = 0;
        for (int i = 1; i < n; i++) {
            distance += current.p.distanceTo(current.next.p);
            current = current.next;
        }
        return distance;
    }
    public void insertNearest(Point p) { // insert p using nearest neighbor heuristic
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
         Node step = new Node(); // the new node to be inserted
        Node curr = new Node(); // points at the node that is pointing at step
        Node oldNext = new Node(); // points at the node that step will be inserted infront of
        double minDist = 0.0;
        int index = 0;
        step.p = p;
        if(first == null) { // if list is empty
            step.next = null;
            first = step;
            n++;
            return;
        }
        step = first.next; // step points at what first is pointing at
        curr = first; // curr points at first
        first = step; // first points at step
        minDist = distance();

        for(int i = 0; i < n; i++){ // traverse through the list, this should include putting step at the very end
            oldNext = step.next; // store the where step is pointing to
            step = oldNext.next.next; // move step to the next node
            oldNext.next = step; // link the node which was pointing at the same spot as what step is, to step
            curr.next = oldNext; // link the node curr is pointing to, to the node that's point at step
            curr = oldNext; // move curr to the next node

            if(distance() < minDist){ // if the distance is smaller than the previous distance
                minDist = distance(); // update the minimum distance
                index = i; // store the index of the node that was just inserted
            }
        }
        for(int i = 0; i < index; i++){ // traverse to the node that is pointing at the node that step should be inserted infront of
            if(i == index - 1){ // if the next node is the node that should point at step
            curr.next = step; // link the node that curr is pointing at to step
            if(curr.next != null) // if the node that curr is pointing at is not the last one
            step = curr.next.next; // link step to the node that curr.next was pointing at    
            } else {
            curr = curr.next; // move curr to the next node
            }
        }
        n++;
    }

}
