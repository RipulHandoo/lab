import java.util.*;

class PuzzleAStar {
    public static int N = 3;

    public static class Node implements Comparable<Node> {
        int[][] m = new int[N][N];
        int x, y;
        int level;
        int cost; // Total cost (g + h), where g is the actual cost, and h is the heuristic cost

        public Node() {
            cost = 0;
        }

        // Compare nodes based on their total cost
        public int compareTo(Node other) {
            return Integer.compare(this.cost, other.cost);
        }
    }

    public static void printMatrix(int[][] m) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(m[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public static Node newNode(int[][] m, int x, int y, int newX, int newY, int level, Node parent) {
        Node node = new Node();
        node.m = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                node.m[i][j] = m[i][j];
            }
        }
        int temp = node.m[x][y];
        node.m[x][y] = node.m[newX][newY];
        node.m[newX][newY] = temp;
        node.level = level;
        node.x = newX;
        node.y = newY;
        return node;
    }

    public static int[][] goalState = {
            {0, 1, 2},
            {4, 7, 5},
            {8, 6, 3}
    };

    public static int row[] = {1, 0, -1, 0};
    public static int col[] = {0, -1, 0, 1};

    public static int misplacedTiles(int[][] m) {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (m[i][j] != goalState[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }

    public static boolean isSafe(int x, int y) {
        return (x >= 0 && x < N && y >= 0 && y < N);
    }

    public static void printPath(Node root) {
        if (root == null) {
            return;
        }
        // printPath(root.parent);
        printMatrix(root.m);
        System.out.println("");
    }

    public static void solve(int initialM[][], int x, int y) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Node root = newNode(initialM, x, y, x, y, 0, null);
        root.cost = misplacedTiles(initialM); // Initialize the cost with the heuristic value
        pq.add(root);

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (Arrays.deepEquals(current.m, goalState)) {
                System.out.println("Cost to reach the goal state: " + current.level);
                printPath(current);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int newX = current.x + row[i];
                int newY = current.y + col[i];
                if (isSafe(newX, newY)) {
                    Node child = newNode(current.m, current.x, current.y, newX, newY, current.level + 1, current);
                    child.cost = child.level + misplacedTiles(child.m); // Update the cost with g + h
                    pq.add(child);
                }
            }
        }
    }

    public static void main(String args[]) {
        int initialM[][] = {
                {0, 1, 2},
                {4, 5, 8},
                {6, 7, 3}
        };

        int x = 1, y = 2;
        solve(initialM, x, y);
    }
}
