import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class BSTChecker {

    boolean isPreOrderValidForBST(String[] pre, int n) {
        // init empty stack
        Stack<String> s = new Stack<String>();
        // init dummy min value as root
        String root = "";

        // iterate given nodes
        for (int i = 0; i < n; i++) {
            //if there's a node later in the traversal smaller than the root, then the preorder was invalid, return false
            if (pre[i].compareTo(root) < 0) {
                return false;
            }

            // construct BST by removing elements smaller than pre[i] and setting as the new root
            while (!s.empty() && s.peek().compareTo(pre[i]) < 0) {
                root = s.peek();
                s.pop();
            }

            s.push(pre[i]);
        }
        return true;
    }

    //to read input from console/stdin, run this method and provide a list of strings ("pairs", according to assignment docs)
    public static void main(String[] args) {
        BSTChecker bst = new BSTChecker();
        Scanner scan = new Scanner(System.in);
        String[] arr = Arrays.stream(scan.nextLine().split(",")).map(String::trim).toArray(String[]::new);
        if (bst.isPreOrderValidForBST(arr, arr.length)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

}
