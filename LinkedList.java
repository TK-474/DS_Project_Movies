public class LinkedList {
    private LNode head;

    public LinkedList() {
        head = null;
    }

    /**
     * insert movie at the head of the list
     */
    public void insert(TNode x) {
        if (x != null) {
            LNode temp = head;
            head = new LNode(x);
            head.next = temp;
        }
    }

    /**
     * deletes a given movie from the list
     */
    public TNode delete(String n) {
        LNode temp = head;
        LNode prev = null;
        while (temp != null && !temp.movie.name.equalsIgnoreCase(n)) {
            prev = temp;
            temp = temp.next;
        }
        if (temp == null) return null;
        else {
            if (prev != null) prev.next = prev.next.next;
            else head = head.next;
        }
        return temp.movie;
    }

    /**
     * Concatenate the list into a String,
     */
    public String toString() {
        String rtrn = "";
        LNode temp = head;
        while (temp != null) {
            rtrn = rtrn + temp + "\n";
            temp = temp.next;
        }
        return rtrn;
    }
}
