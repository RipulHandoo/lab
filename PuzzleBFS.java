import java.util.*;
// submitted one on volp

class PuzzleBFS {
    public static int N1 = 3;

    public static class Node {
        Node parent1;
        int m[][] = new int[N1][N1];
        int x1, y1;
        int level1;
        int cost1; // Add the cost field
    
        public Node() {
            cost1 = 0; // Initialize the cost
        }
    }

    public static void printMatrix(int m[][]) {
        for (int i1 = 0; i1 < N1; i1++) {
            for (int j1 = 0; j1 < N1; j1++) {
                System.out.print(m[i1][j1] + " ");
            }
            System.out.println("");
        }
    }

    public static Node newNode(int m[][], int x1, int y1, int newX1, int newY1, int level1, Node parent1) {
        Node node1 = new Node();
        node1.parent1 = parent1;
        node1.m = new int[N1][N1];
        for (int i1 = 0; i1 < N1; i1++) {
            for (int j1 = 0; j1 < N1; j1++) {
                node1.m[i1][j1] = m[i1][j1];
            }
        }
        int temp1 = node1.m[x1][y1];
        node1.m[x1][y1] = node1.m[newX1][newY1];
        node1.m[newX1][newY1] = temp1;
        node1.level1 = level1;
        node1.x1 = newX1;
        node1.y1 = newY1;
        return node1;
    }

    public static int row1[] = {1, 0, -1, 0};
    public static int col1[] = {0, -1, 0, 1};

    public static int isSafe(int x1, int y1) {
        return (x1 >= 0 && x1 < N1 && y1 >= 0 && y1 < N1) ? 1 : 0;
    }

    public static void printPath(Node root1) {
        if (root1 == null) {
            return;
        }
        printPath(root1.parent1);
        printMatrix(root1.m);
        System.out.println("");
    }

    public static void solve(int initialM[][], int x1, int y1, int finalM[][]) {
        Queue<Node> queue = new LinkedList<>();
        Node root1 = newNode(initialM, x1, y1, x1, y1, 0, null);
        queue.add(root1);
    
        while (!queue.isEmpty()) {
            Node current = queue.poll();
    
            if (Arrays.deepEquals(current.m, finalM)) {
                System.out.println("Cost to reach the goal state: " + current.level1); // Display the cost
                printPath(current);
                return;
            }
    
            for (int i1 = 0; i1 < 4; i1++) {
                if (isSafe(current.x1 + row1[i1], current.y1 + col1[i1]) > 0) {
                    Node child1 = newNode(current.m, current.x1, current.y1,
                            current.x1 + row1[i1], current.y1 + col1[i1],
                            current.level1 + 1, current);
                    child1.cost1 = current.cost1 + 1; // Update the cost for the child node
                    queue.add(child1);
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

        int finalM[][] = {
            {0, 1, 2},
            {4, 7, 5},
            {8, 6, 3}
        };

        int x1 = 1, y1 = 2;
        solve(initialM, x1, y1, finalM);
    }
}
