import java.util.*;

public class test3 {
    public static void main(String[] args){
        int[][] initial = {
            {1,2,3},
            {0,4,6},
            {7,5,8}
        };

        int[][] goal = {
            {1,2,3},
            {4,5,6},
            {7,8,0}
        };

        test3 solverTest3 = new test3();
        List<int[][]> solution = solverTest3.solvePuzzle(initial, goal);

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

    public List<int[][]> solvePuzzle(int[][] initial, int[][] goal){
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(Node::getCost));
        Set<String> visited = new HashSet<>();

        Node root = new Node(initial, null, 0, calculateHeuristic(initial,goal));
        queue.add(root);

        while(!queue.isEmpty()){
            Node current = queue.poll();
            visited.add(Arrays.deepToString(current.state));

            if(Arrays.deepEquals(current.state, goal)){
                return getPath(current);
            }

            List<Node> neighbor = getNeighbors(current, goal);
            for(Node newState : neighbor){
                if(!visited.contains(Arrays.deepToString(newState.state))){
                    queue.add(newState);
                }
            }
        }
        return null;
    }

    public List<Node> getNeighbors(Node current, int[][] goal){

        List<Node> list = new ArrayList<>();
        int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};
        int[] zeroPosition = findZeroPosition(current.state);

        for(int[] direction : directions){
            int x = zeroPosition[0] + direction[0];
            int y = zeroPosition[1] + direction[1];
            
            if(x >= 0 && x < current.state.length && y >= 0 && y < current.state[0].length){
                int[][] neighbor = copyState(current.state);
                swap(neighbor, zeroPosition[0], zeroPosition[1], x, y);

                int cost = current.cost + 1;
                int heuristicCost = cost + calculateHeuristic(neighbor, goal);
                Node newNode = new Node(neighbor, current, cost, heuristicCost);
                list.add(newNode);
            }
        }
        return list;
    }

    public void swap(int[][] matrix, int x1, int y1, int x2, int y2){
        int temp = matrix[x1][y1];
        matrix[x1][y1] = matrix[x2][y2];
        matrix[x2][y2] = temp;
    }

    public int calculateHeuristic(int[][] initial, int[][] goal){
        int heuristic = 0;

        for(int i = 0;i<initial.length;i++){
            for(int j = 0;j<initial[0].length;j++){
                if(initial[i][j] != goal[i][j]){
                    heuristic++;
                }
            }
        }
        return heuristic;
    }

    public int[] findZeroPosition(int[][] current){
        for(int i = 0;i<current.length;i++){
            for(int j = 0;j<current[i].length;j++){
                if(current[i][j] == 0){
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }

    public int[][] copyState(int[][] current){
        int[][] newMatrix = new int[current.length][];

        for(int i = 0; i<current.length;i++){
            newMatrix[i] = Arrays.copyOf(current[i], current[i].length);
        }
        return newMatrix;
    }

    public List<int[][]> getPath(Node current){
        List<int[][]> states = new ArrayList<>();

        while(current != null){
            states.add(current.state);
            current = current.parent;
        }   
        return states;
    }

    class Node{
        private int[][] state;
        private Node parent;
        private int cost;
        private int heuristic;

        public Node(int[][] state, Node parent, int cost, int heuristic){
            this.cost = cost;
            this.parent = parent;
            this.heuristic = heuristic;
            this.state = state;
        }

        public int getCost(){
            return this.cost + this.heuristic;
        }
    }
}
