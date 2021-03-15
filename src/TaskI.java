import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskI {
    private static class Node {
        private final Node[] children = new Node[26];
        private final Node[] go = new Node[26];
        private final Node parent;
        private Node suffLink;
        private Node up;
        private final int charToP; //char
        private boolean leaf;
        private List<Integer> strs;
 
        public Node(Node parent, int charToP) {
            this.parent = parent;
            this.charToP = charToP;
        }
 
        public void addString(String s, int str) {
            Node cur = this;
            for (int i = 0; i < s.length(); i++) {
                int ch = s.charAt(i) - 'a';
                if (cur.children[ch] == null) {
                    cur.children[ch] = new Node(cur, ch);
                }
                cur = cur.children[ch];
            }
            cur.leaf = true;
            if (cur.strs == null) {
                cur.strs = new ArrayList<>();
            }
            cur.strs.add(str);
        }
 
        private Node getSuffLink() {
            if (suffLink == null) {
                if (parent == null) {
                    suffLink = this;
                } else if (parent.parent == null) {
                    suffLink = parent;
                } else {
                    suffLink = parent.getSuffLink().getLink(charToP);
                }
            }
            return suffLink;
        }
 
        public Node getLink(int ch) {
            if (go[ch] == null) {
                if (children[ch] != null) {
                    go[ch] = children[ch];
                } else if (parent == null) {
                    go[ch] = this;
                } else {
                    go[ch] = getSuffLink().getLink(ch);
                }
            }
            return go[ch];
        }
 
        public Node getUp() {
            if (up == null) {
                Node sfLink = getSuffLink();
                if (sfLink.leaf || sfLink.parent == null) {
                    up = sfLink;
                } else {
                    up = sfLink.getUp();
                }
            }
 
            return up;
        }
    }
 
    public static void main(String[] args) {
        //val reader = BufferedReader(InputStreamReader(System.`in`))
        PrintWriter out = new PrintWriter(System.out);
        Scanner sc = new Scanner(System.in);
        String t = sc.next();
        int n = sc.nextInt();
        boolean[] match = new boolean[n];
        Node tree = new Node(null, -1);
 
        for (int i = 0; i < n; i++) {
            tree.addString(sc.next(), i);
        }
 
        Node cur = tree;
        for (int i = 0; i < t.length(); i++) {
            int ch = t.charAt(i) - 'a';
            cur = cur.getLink(ch);
            /*if (cur.leaf) {
                Node curTerm = cur;
                do {
                    for (int str : curTerm.strs) {
                        match[str] = true;
                    }
                    curTerm = curTerm.getUp();
                } while (curTerm != tree);
            }*/
            Node curTerm = cur;
            if (!curTerm.leaf) {
                curTerm = curTerm.getUp();
            }
 
            whLoop: while (curTerm.leaf) {
                for (int str : curTerm.strs) {
                    if (match[str]) {
                        break whLoop;
                    }
                    match[str] = true;
                }
                curTerm = curTerm.getUp();
            }
        }
 
        for (int i = 0; i < n; i++) {
            if (match[i]) {
                out.println("Yes");
            } else {
                out.println("No");
            }
        }
        out.close();
    }
}