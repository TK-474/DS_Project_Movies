public class LNode {
    LNode next;
    TNode movie;

    public LNode(TNode m) {
        movie = m;
        next = null;
    }

    public String toString() {
        return movie.toString();
    }
}
