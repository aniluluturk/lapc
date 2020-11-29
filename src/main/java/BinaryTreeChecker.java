import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

class BinaryTreeChecker {
    private int V; // nr of nodes/vertices
    private LinkedList<Edge>[] adj; //adjacency list for nodes
    private Map<Character, Integer> charToNodeNumber;

    enum EdgeType {
        IN, OUT;
    }

    public static class Edge {
        int v;
        EdgeType type;

        public Edge(int v, EdgeType t) {
            this.v = v;
            this.type = t;
        }
    }

    public BinaryTreeChecker(String[] pairs) {
        Set<String> nodes = Arrays.stream(pairs).map(e -> Arrays.stream(e.toLowerCase().split("")).collect(Collectors.toList())).flatMap(Collection::stream).collect(Collectors.toSet());
        this.charToNodeNumber = new HashMap<>();
        List<String> nodesSorted = nodes.stream().sorted().collect(Collectors.toList());
        if(nodesSorted.isEmpty()) return;
        int itr = 0;
        //assign integer numbers to nodes, save them in the map
        for (String n : nodesSorted) {
            this.charToNodeNumber.put(n.charAt(0), itr++);
        }
        //init adjacency matrix and graph size
        this.V = nodesSorted.size();
        this.adj = new LinkedList[V];
        for (int i = 0; i < V; ++i)
            adj[i] = new LinkedList<>();

        //add edges
        for (String s : pairs) {
            char from = s.charAt(0);
            char to = s.charAt(1);
            this.addEdge(getNodeNumberFromChar(from), getNodeNumberFromChar(to));
        }
    }

    //add an edge to the graph from
    void addEdge(int from, int to) {
        adj[from].add(new Edge(to, EdgeType.OUT));
        adj[to].add(new Edge(from, EdgeType.IN));
    }

    //checks for cycle in the graph by recursively visiting all nodes accessible from v
    private boolean containsCycle(int v, boolean[] visitedNodes, int parent) {
        //mark v as visited
        visitedNodes[v] = true;
        int i;

        //check for all edges for v
        for (Edge edge : adj[v]) {
            i = edge.v;

            //if not visited, recursively check cycle for the target node
            if (!visitedNodes[i]) {
                if (containsCycle(i, visitedNodes, v))
                    return true;
            }

            //if we encounter a previously visited node that was not prior to current node, then there's a cycle
            else if (i != parent)
                return true;
        }
        return false;
    }

    // Returns true if the graph is a tree, else false.
    public boolean isBinaryTree() {
        if(this.V == 0 || this.adj.length == 0) {
            return false; //empty tree/graph is not considered a binary tree either
        }

        //mark all nodes initially as not visited
        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; i++) {
            visited[i] = false;
        }

        //check for cycles in graph, if there's any, it can't be a tree
        if (containsCycle(0, visited, -1)) {
            return false;
        }

        //if graph contains a disjointed node not reachable from the first node, then it's not a tree
        for (int u = 0; u < V; u++) {
            if (!visited[u])
                return false;
        }

        //if graph contains nodes with more than 3 edges (1 for parent, 2 for children), then it's not binary
        for (int u = 0; u < V; u++) {
            long inEdges = adj[u].stream().filter(e -> e.type == EdgeType.IN).count();
            long outEdges = adj[u].stream().filter(e -> e.type == EdgeType.OUT).count();
            if (adj[u].size() > 3 || inEdges > 1 || outEdges > 2) {
                return false;
            }
        }

        return true;
    }

    private int getNodeNumberFromChar(char c) {
        return charToNodeNumber.get(c);
    }

    //to read input from console/stdin, run this method and provide a list of edges ("pairs", according to assignment docs)
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        //get pairs from input, but disregard the ones if they contain more than 2 chars, we don't support those inputs
        String[] pairs = Arrays.stream(scan.nextLine().split(",")).map(String::trim).filter(s -> s.length() == 2).toArray(String[]::new);
        BinaryTreeChecker bt = new BinaryTreeChecker(pairs);

        if (bt.isBinaryTree())
            System.out.println("YES");
        else
            System.out.println("NO");
    }
}