import java.util.*;

public class DFS {

    public static class Edge{
        public int src;
        public int dest;

        public Edge(int src,int dest){
            this.dest = dest;
            this.src = src;
        }
    }

    public static void creatingGraph(ArrayList<Edge> graph[]){
        for(int i = 0;i<graph.length;i++){
            graph[i] = new ArrayList<Edge>();
        }

        graph[0].add(new Edge(0,2));
        graph[0].add(new Edge(0,1));

        graph[1].add(new Edge(1,0));
        graph[1].add(new Edge(1,3));

        graph[2].add(new Edge(2,4));
        graph[2].add(new Edge(2,0));

        graph[3].add(new Edge(3,4));
        graph[3].add(new Edge(3,5));
        graph[3].add(new Edge(3,1));

        graph[4].add(new Edge(4,2));
        graph[4].add(new Edge(4,3));
        graph[4].add(new Edge(4,5));
        

        graph[5].add(new Edge(5,3));
        graph[5].add(new Edge(5,4));
        graph[5].add(new Edge(5,6));

        graph[6].add(new Edge(6,5));
    }


    public static void DFS(ArrayList<Edge> graph[], boolean visited[], int curr){
        if(visited[curr] == false){
            System.out.print(curr+" ");
            visited[curr] = true;
            for(int i = 0;i<graph[curr].size();i++){
                DFS(graph,visited,graph[curr].get(i).dest);
            }
        }
    }
    
    public static void main(String[] args) {
        int V = 8;
        
        ArrayList<Edge> graph[]  = new ArrayList[V];
        creatingGraph(graph);

        boolean visited[] = new boolean[V];
        DFS(graph,visited,0);//3rd parameter is from where the graph starts DFS or in simple words the edge from where DFS starts
    }
}
