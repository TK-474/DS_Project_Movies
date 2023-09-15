/**
 * The HashMap class implements the necessary functionalities of a hash table to be used in the project to create the user's watch list
 */
public class HashMap {
    private LinkedList[] list;

    public HashMap(int s) {
        list = new LinkedList[s + (s / 3)];
    }

    /**
     * insert movie into the relevant index of the hash table
     */
    public void insert(TNode m) {
        int i = hash(m.name);
        if (list[i] == null)
            list[i] = new LinkedList();
        list[i].insert(m);
    }

    /**
     * remove the movie from the hashmap
     */
    public TNode pop(String n) {
        int a = hash(n);
        if (list[a] != null) return list[a].delete(n);
        else return null;
    }

    /**
     * clear the user's watch list
     */
    public void clear() {
        for (int i = 0; i < list.length; i++) {
            if (list[i] != null) list[i] = null;
        }
    }

    /**
     * Concatenate the list into a String
     */
    public String toString() {
        String rtrn = "";
        for (int i = 0; i < list.length; i++) {
            if (list[i] != null) rtrn += list[i].toString();
        }
        return rtrn;
    }

    private int hash(String n) {
        int j = n.toLowerCase().hashCode() % list.length;
        if (j < 0) j = -j;
        return j;
    }
}
