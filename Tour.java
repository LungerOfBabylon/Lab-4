import edu.princeton.cs.algs4.*;
import edu.princeton.cs.algs4.StdOut;

public class Tour {
    private Node first;
    private int n;

    private class Node { // creating the Node subClass
        private Point p;
        private Node next;
    }

    public Tour() {
    }

    public void show() { // print the tour to standard output
        Node current = first;
        for (int i = 1; i <= n; i++) {
            StdOut.println(current.p.toString());
            current = current.next;
        }
    }

    public void draw() { // draw the tour to standard draw
        Node current = first;
        for (int i = 1; i < n; i++) {
            current.p.draw();
            if (current.next != null) { // if it not the last node, draw a line to the next node
                current.p.drawTo(current.next.p);
            } else {
                current.p.drawTo(first.p); // if it is the last node, draw a line to the first node
            }
            current = current.next; // move to the next node
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
        distance += current.p.distanceTo(first.p); 
        return distance;
    }

    public void insertNearest(Point p) { // insert p using nearest neighbor heuristic
        Node newNode = new Node();
        newNode.p = p;
        if (first == null) { // if the list is empty, set the first to the new node and return
            newNode.next = null;
            first = newNode;
            n++;
            return;
        }
        Node closest = first; // initialize the closest node to first
        double Dist = first.p.distanceTo(p); // same thing but with distance
        Node current = first.next; // move current forward
        while (current != null) { // traverse until the end of the list
            double d = current.p.distanceTo(p); // grab the new distance
            if (d < Dist) { // if the new distance is less then make it the closest
                Dist = d;
                closest = current;
            }
            current = current.next; // move to the next node
        }
        newNode.next = closest.next; // once the closest is confirmed insert the new node after it
        closest.next = newNode;
        n++;
    }

    public void insertSmallest(Point p) { // insert p using smallest increase heuristic

        Node newNode = new Node(); // the new node to be inserted
        newNode.p = p; // set the point of the new node to p
        if (first == null) {
            first = newNode; // if the list is empty, set the first node to the new node and return
            newNode.next = null;
            n++;
            return;
        }
        if (first.next == null) {
            first.next = newNode;
            newNode.next = null;
            n++;
            return;
        }
        
        Node step = new Node(); // the node that steps through the list
        Node bestPos = new Node(); // the node that is the best position to insert the newNode infront of
        double minDist = Double.MAX_VALUE; // this sets the minDist to the largest possible value so that any distance
                                           // will be smaller than it, it's not ideal as it's a lot of memory but it works
        step = first; // step points at the first node
        while (step != null) {
        	Node next = step.next; // the node after step
            if (next == null) {
                next = first; // if step is the last node, wrap around to the first node
            }
            double dist = step.p.distanceTo(p) + next.p.distanceTo(p) - step.p.distanceTo(next.p); // see below for further explanation of this calculation

            if (dist < minDist) { // checks if this distance is better
                minDist = dist; // update the minimum distance
                bestPos = step; // saves the best position to insert the newNode infront of
            }
            step = step.next; // move to the next node
        }

        newNode.next = bestPos.next; // set the next of the new node to the next of the best position
        bestPos.next = newNode; // set the next of the best position to the new
        n++; // increment the size of the tour

        /*
         * Further explanation for the dist calculation, basically instead of calling
         * distance() which would have to run through the entire list this instead
         * calulates
         * calculates just the new distance bewtween the three nodes, the first node
         * being what points to the new node and the dsecond being the new node and the
         * third being where new node points
         * you subtract that original distance between the first and third so you will
         * get how much the distance increased by addding the new node, not just the new
         * distance
         * 
         * 
         * 
         * 
         * This old code which SHOULD work now, if not then it's just some small bugs,
         * however it is far less effieceint than the above code, but both are
         * interesting approches
         * this code actually inserts the new node at each point and then calculates the
         * entire lists distance, saving how far into the list that the best position
         * was and then
         * traversing to that point and inserting the new node there, but this is way
         * worse for obviouse reasons, but it's still cool and was my first attempt
         * 
         * Node step = new Node(); // the new node to be inserted
         * Node curr = new Node(); // points at the node that is pointing at step
         * Node oldNext = new Node(); // points at the node that step will be inserted
         * infront of
         * double minDist = 0.0;
         * int index = 0;
         * step.p = p;
         * if(first == null) { // if list is empty
         * step.next = null;
         * first = step;
         * n++;
         * return;
         * }
         * step = first.next; // step points at what first is pointing at
         * curr = first; // curr points at first
         * first = step; // first points at step
         * minDist = distance();
         * 
         * for(int i = 0; i < n; i++){ // traverse through the list, this should include
         * putting step at the very end
         * oldNext = step.next; // store the where step is pointing to
         * step = oldNext.next.next; // move step to the next node
         * oldNext.next = step; // link the node which was pointing at the same spot as
         * what step is, to step
         * curr.next = oldNext; // link the node curr is pointing to, to the node that's
         * point at step
         * curr = oldNext; // move curr to the next node
         * 
         * if(distance() < minDist){ // if the distance is smaller than the previous
         * distance
         * minDist = distance(); // update the minimum distance
         * index = i; // store the index of the node that was just inserted
         * }
         * }
         * for(int i = 0; i <= index; i++){ // traverse to the node that is pointing at
         * the node that step should be inserted infront of
         * if(i == index){ // if the next node is the node that should point at step
         * curr.next = step; // link the node that curr is pointing at to step
         * if(curr.next != null) // if the node that curr is pointing at is not the last
         * one
         * step = curr.next.next; // link step to the node that curr.next was pointing
         * at
         * } else {
         * curr = curr.next; // move curr to the next node
         * }
         * }
         * n++;
         */
    }

}
