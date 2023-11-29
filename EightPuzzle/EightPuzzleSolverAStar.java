package EightPuzzle;
import java.util.*;

public class EightPuzzleSolverAStar {
    public static void main(String[] args) {
        int[][] initial = {
            {1, 2, 3},
            {0, 4, 6},
            {7, 5, 8}
        };

        int[][] goal = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 0}
        };

        EightPuzzleSolverAStar solver = new EightPuzzleSolverAStar();
        List<int[][]> solution = solver.solvePuzzle(initial, goal);

        if (solution != null) {
            System.out.println("Solution found!");
            for (int[][] state : solution) {
                printPuzzle(state);
                System.out.println();
            }
        } else {
            System.out.println("No solution found.");
        }
    }

    private static void printPuzzle(int[][] puzzle) {
        for (int i = 0; i < puzzle.length; i++) {
            for (int j = 0; j < puzzle[i].length; j++) {
                System.out.print(puzzle[i][j] + " ");
            }
            System.out.println();
        }
    }

    public List<int[][]> solvePuzzle(int[][] initial, int[][] goal) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(Node::getCost));
        Set<String> visited = new HashSet<>();

        Node root = new Node(initial, null, 0, calculateHeuristic(initial, goal));
        priorityQueue.add(root);

        while (!priorityQueue.isEmpty()) {
            Node current = priorityQueue.poll();
            visited.add(Arrays.deepToString(current.state));

            if (Arrays.deepEquals(current.state, goal)) {
                return getPath(current);
            }

            List<Node> neighbors = getNeighbors(current, goal);
            for (Node neighbor : neighbors) {
                if (!visited.contains(Arrays.deepToString(neighbor.state))) {
                    priorityQueue.add(neighbor);
                }
            }
        }

        return null;
    }

    private List<Node> getNeighbors(Node node, int[][] goal) {
        List<Node> neighbors = new ArrayList<>();
        int[] zeroPosition = findZeroPosition(node.state);

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int[] direction : directions) {
            int x = zeroPosition[0] + direction[0];
            int y = zeroPosition[1] + direction[1];

            if (x >= 0 && x < node.state.length && y >= 0 && y < node.state[0].length) {
                int[][] neighborState = copyState(node.state);
                swap(neighborState, zeroPosition[0], zeroPosition[1], x, y);

                int cost = node.cost + 1;
                int heuristic = cost + calculateHeuristic(neighborState, goal);

                Node neighbor = new Node(neighborState, node, cost, heuristic);
                neighbors.add(neighbor);
            }
        }

        return neighbors;
    }

    private int[] findZeroPosition(int[][] state) {
        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state[i].length; j++) {
                if (state[i][j] == 0) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    private int[][] copyState(int[][] state) {
        int[][] copy = new int[state.length][];
        for (int i = 0; i < state.length; i++) {
            copy[i] = Arrays.copyOf(state[i], state[i].length);
        }
        return copy;
    }

    private void swap(int[][] state, int x1, int y1, int x2, int y2) {
        int temp = state[x1][y1];
        state[x1][y1] = state[x2][y2];
        state[x2][y2] = temp;
    }

    private int calculateHeuristic(int[][] state, int[][] goal) {
        int heuristic = 0;
        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state[i].length; j++) {
                if (state[i][j] != goal[i][j]) {
                    heuristic++;
                }
            }
        }
        return heuristic;
    }

    private List<int[][]> getPath(Node node) {
        List<int[][]> path = new ArrayList<>();
        while (node != null) {
            path.add(0, node.state);
            node = node.parent;
        }
        return path;
    }

    private static class Node {
        private int[][] state;
        private Node parent;
        private int cost;
        private int heuristic;

        public Node(int[][] state, Node parent, int cost, int heuristic) {
            this.state = state;
            this.parent = parent;
            this.cost = cost;
            this.heuristic = heuristic;
        }

        public int getCost() {
            return cost + heuristic;
        }
    }
}
