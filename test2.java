import java.util.*;

public class test2{
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

        test2 solve = new test2();



        List<int[][]> solution = solve.solvePuzzle(initial, goal);
        
        if(solution != null){
            int size = solution.size();
            System.out.println("Puzzle solved in "+(size-1)+" moves");
            for(int i = 0;i<size;i++){
                printPath(solution.get(i));
                System.out.println();
            }
        }else{
            System.out.println("No solution found");
        }
    }

    // print the path
    private static void printPath(int[][] matrix){
        for(int i = 0;i<matrix.length;i++){
            for(int j = 0;j<matrix[i].length;j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

// logic to find the solution for puzzle
    private List<int[][]> solvePuzzle(int[][] initial, int[][] goal){
        Queue<Node> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        Node root = new Node(initial, null, 0);
        queue.offer(root);

        while(!queue.isEmpty()){
            Node current = queue.poll();
            visited.add(Arrays.deepToString(current.state));

            if(Arrays.deepEquals(current.state, goal)){
                return getPath(current);
            }

            List<Node> neighbor = getNeighbor(current);
            for(Node newNeighbor : neighbor){
                if(!visited.contains(Arrays.deepToString(newNeighbor.state))){
                    queue.offer(newNeighbor);
                }
            }
        }
        return null;
    }

    // get the neighbor
    private List<Node> getNeighbor(Node node){
        List<Node> neighbors = new ArrayList<>();
        int[] zeroPosition = getZeroPosition(node.state);

        int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};
        for(int[] direction : directions){
            int x = zeroPosition[0] + direction[0];
            int y = zeroPosition[1] + direction[1];

            if(x >= 0 && x < node.state.length && y >= 0 && y < node.state[0].length){
                int[][] newNeighbor = copyState(node.state);
                swap(newNeighbor,zeroPosition[0],zeroPosition[1],x,y);

                Node neighbor = new Node(newNeighbor, node, node.cost+1);
                neighbors.add(neighbor);
            }
        }
        return neighbors;
    }

    // swap the number
    private void swap(int[][] matrix, int x1, int y1, int x2, int y2){
        int temp = matrix[x1][y1];
        matrix[x1][y1] = matrix[x2][y2];
        matrix[x2][y2] = temp;
    }

    // copy the state
    private int[][] copyState(int[][] matrix){
        int[][] newMatrix = new int[matrix.length][];
        for(int i = 0;i<matrix.length;i++){
            newMatrix[i] = Arrays.copyOf(matrix[i],matrix[i].length);
        }
        return newMatrix;
    }

    // get the zero position
    private int[] getZeroPosition(int[][] matrix){
        for(int i = 0;i<matrix.length;i++){
            for(int j = 0;j<matrix[i].length;j++){
                if(matrix[i][j] == 0){
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }

    // get the path
    private List<int[][]> getPath(Node node){
        List<int[][]> path = new ArrayList<>();
        while(node != null){
            path.add(node.state);
            node = node.parent;
        }
        return path;
    }


    // define the class that stores the values for the node
    class Node{
        private int[][] state;
        private Node parent;
        private int cost;

        public Node(int[][] state, Node parNode, int cost){
            this.cost = cost;
            this.parent = parNode;
            this.state = state;
        }
    }
}