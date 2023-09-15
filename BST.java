import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Stack;

public class BST {
    private TNode[] Root;
    private HashMap MyList;
    private LinkedList History;

    public BST() {
        System.out.println("\nInitialising Netflix");
        Root = new TNode[100];
        Arrays.fill(Root, null);
        MyList = new HashMap(100);
        History = new LinkedList();
        init();
        System.out.println("Netflix Initialised");
    }

    private void init() {
        String file = "movie_metadata.csv";
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            String[] data;
            String name;
            String[] genre;
            double score;
            while ((line = reader.readLine()) != null) {
                data = line.split(",");
                genre = data[1].split("/");
                score = Double.parseDouble(data[2]);
                //remove extra spaces from the movie's name
                int j = data[0].length() - 1;           //
                while (data[0].charAt(j) == ' ') {      //
                    j--;                                //
                }                                       //
                name = data[0].substring(0, j);         //
                //
                for (int i = 0; i < genre.length; i++) {
                    insert(name, data[1], score, genre[i]);
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insert(String Name, String Genre, double Score, String g) {
        TNode newNode = new TNode(Name, Genre, Score);
        int i = getIndex(g);
        if (Root[i] == null) Root[i] = newNode;
        else {
            TNode curr = Root[i];
            TNode pvs = null;
            while (curr != null) {
                if (newNode.name.compareToIgnoreCase(curr.name) < 0) {
                    pvs = curr;
                    curr = curr.left;
                } else {
                    pvs = curr;
                    curr = curr.right;
                }
            }
            if (newNode.name.compareToIgnoreCase(pvs.name) < 0) {
                pvs.left = newNode;
            } else {
                pvs.right = newNode;
            }
        }
    }

    public void search(String n) {
        TNode f = find(n);
        if (f != null) System.out.println(f.toString());
        else System.out.println(n + " does not exist");
    }

    public void traverseGenre(String g) {
        int i = getIndex(g);
        if (Root[i] == null) {
            System.out.println("No movies in this category");
            return;
        }
        Stack<TNode> treeStack = new Stack<>();
        TNode currNode = Root[i];
        while (currNode != null || treeStack.size() > 0) {
            if (currNode != null) {
                treeStack.push(currNode);
                currNode = currNode.left;
            } else {
                currNode = treeStack.pop();
                System.out.println(currNode);
                currNode = currNode.right;
            }
        }
    }

    public void topScore(double score) {
        boolean empty = true;
        for (int i = 0; i < Root.length; i++) {
            if (Root[i] != null) {
                Stack<TNode> treeStack = new Stack<>();
                TNode currNode = Root[i];
                while (currNode != null || treeStack.size() > 0) {
                    if (currNode != null) {
                        treeStack.push(currNode);
                        currNode = currNode.left;
                    } else {
                        currNode = treeStack.pop();
                        if (currNode.score >= score) {
                            System.out.println(currNode);
                            empty = false;
                        }
                        currNode = currNode.right;
                    }
                }
            }
        }
        if (empty) System.out.println("No movie has a score above or equal to " + score);
    }

    public void topScore(double score, String g) {
        int i = getIndex(g);
        if (Root[i] == null) {
            System.out.println("No movies in this Category");
            return;
        }
        Stack<TNode> treeStack = new Stack<>();
        TNode currNode = Root[i];
        while (currNode != null || treeStack.size() > 0) {
            if (currNode != null) {
                treeStack.push(currNode);
                currNode = currNode.left;
            } else {
                currNode = treeStack.pop();
                if (currNode.score >= score) System.out.println(currNode);
                currNode = currNode.right;
            }
        }
    }

    public void myListInsert(String n) {
        TNode f = find(n);
        if (f != null) {
            MyList.insert(f);
            System.out.println(f.name + " has been added to your watch list");
        } else System.out.println(n + " does not exist");
    }

    public void displayMyList() {
        System.out.println("Your watch list: ");
        System.out.print(MyList.toString());
    }

    public void clearMyList() {
        MyList.clear();
        System.out.println("Your watch list has been cleared");
    }

    public void watch(String n) {
        TNode f;
        if ((f = MyList.pop(n)) != null) {
            History.insert(f);
            System.out.println(f.name + " has now been watched");
        } else if ((f = find(n)) != null) {
            History.insert(f);
            System.out.println(f.name + " has now been watched");
        } else System.out.println(n + "does not exist");
    }

    public void displayHistory() {
        System.out.println("Your watch history: ");
        System.out.print(History.toString());
    }

    private TNode find(String n) {
        TNode curr;
        for (int i = 0; i < Root.length; i++) {
            curr = Root[i];
            while (curr != null) {
                if (n.equalsIgnoreCase(curr.name)) return curr;
                if (n.compareToIgnoreCase(curr.name) < 0) curr = curr.left;
                else curr = curr.right;
            }
        }
        return null;
    }

    private int getIndex(String g) {
        int i;
        i = g.toLowerCase().hashCode() % Root.length;
        if (i < 0) i = -i;   //solution for g.hashcode giving negative value
        if (Root[i] != null) {
            while (!Root[i].genre.equals(g) && Root[i] == null) {
                i = (i + 2);
                if (i > Root.length) i = i % Root.length;
            }
        }
        return i;
    }
}
