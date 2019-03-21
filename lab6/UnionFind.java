public class UnionFind {

     private int[] parent;
     private int[] size;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = -1;
            size[i] = 1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if (vertex < 0 || vertex >= parent.length) {
            throw new IllegalArgumentException("Index " + vertex + " is not between 0 and " + parent.length + "!" );
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        return size[find(v1)];
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        validate(v1);
        return parent[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        int rootv1 = find(v1);
        int rootv2 = find(v2);
        if (rootv1 == rootv2) return;
        if (size[rootv1] <= size[rootv2]) {
            parent[rootv1] = rootv2;
            size[rootv2] += size[rootv1];
            parent[rootv2] = -size[rootv2];
        } else {
            parent[rootv2] = rootv1;
            size[rootv1] += size[rootv2];
            parent[rootv1] = -size[rootv1];
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        validate(vertex);
        int root = vertex;
        while (parent[root] >= 0) {
            root = parent[root];
        }
        while (parent[vertex] >= 0) {
            int newvertex = parent[vertex];
            parent[vertex] = root;
            vertex = newvertex;
        }
        return root;
    }

    public static void main(String[] args) {
        UnionFind weightedQU = new UnionFind(9);
        weightedQU.union(3, 2);
        weightedQU.union(1, 2);
        weightedQU.union(7, 5);
        weightedQU.union(4, 8);
        weightedQU.union(2, 7);
        System.out.println("the root of 3 is " + weightedQU.find(3));
        weightedQU.union(6, 0 );
        weightedQU.union(4, 6);
        weightedQU.union(3, 6);
        System.out.println("the root of 3 is " + weightedQU.find(4));
        System.out.println("the root of 3 is " + weightedQU.find(6));
    }
}
