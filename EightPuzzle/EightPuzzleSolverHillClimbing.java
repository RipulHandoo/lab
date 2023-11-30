package EightPuzzle;

import java.util.*;

public class EightPuzzleSolverHillClimbing {
    public static void main(String[] args) {
        int[][] initial = {
                {1, 2, 3},
                {4, 5, 8},
                {6, 7, 0}
        };

        int[][] goal = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}
        };

        EightPuzzleSolverHillClimbing solver = new EightPuzzleSolverHillClimbing();
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
        int maxIterations = 1000; // Set a maximum number of iterations
        int[][] current = initial;

        for (int i = 0; i < maxIterations; i++) {
            List<int[][]> neighbors = getNeighbors(current, goal);

            if (neighbors.isEmpty()) {
                break; // No better neighbors found
            }

            int[][] bestNeighbor = getBestNeighbor(neighbors, goal);
            int currentHeuristic = calculateHeuristic(current, goal);
            int bestNeighborHeuristic = calculateHeuristic(bestNeighbor, goal);

            if (bestNeighborHeuristic >= currentHeuristic) {
                return getPath(initial, current); // Local minimum reached
            }

            current = bestNeighbor;
        }

        return null; // No solution found within the maximum number of iterations
    }

    private List<int[][]> getPath(int[][] initial, int[][] current) {
        List<int[][]> path = new ArrayList<>();
        path.add(initial);
        path.add(current);
        return path;
    }

    private List<int[][]> getNeighbors(int[][] state, int[][] goal) {
        List<int[][]> neighbors = new ArrayList<>();
        int[] zeroPosition = findZeroPosition(state);

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int[] direction : directions) {
            int x = zeroPosition[0] + direction[0];
            int y = zeroPosition[1] + direction[1];

            if (x >= 0 && x < state.length && y >= 0 && y < state[0].length) {
                int[][] neighborState = copyState(state);
                swap(neighborState, zeroPosition[0], zeroPosition[1], x, y);
                neighbors.add(neighborState);
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

    private int[][] getBestNeighbor(List<int[][]> neighbors, int[][] goal) {
        int[][] bestNeighbor = neighbors.get(0);
        int bestHeuristic = calculateHeuristic(bestNeighbor, goal);

        for (int[][] neighbor : neighbors) {
            int heuristic = calculateHeuristic(neighbor, goal);
            if (heuristic < bestHeuristic) {
                bestNeighbor = neighbor;
                bestHeuristic = heuristic;
            }
        }

        return bestNeighbor;
    }
}
