public class TNode {
    TNode right, left;
    String name, genre;
    double score;

    public TNode(String n, String g, double s) {
        name = n;
        score = s;
        genre = g;
        right = left = null;
    }

    public String toString() {
        return "Title: " + name + " || Genre: " + genre + " || Score: " + score;
    }
}
