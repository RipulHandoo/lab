import java.util.*;

public class AdjacentList{
   public static class Edge{
    public int src;
    public int dest;
    public int weight;
    public Edge(int src, int dest,int weight){
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
   }

   public static void creatingGraph(ArrayList<Edge> graph[]){
    for(int i = 0;i<graph.length;i++){
        graph[i] = new ArrayList<Edge>();
    }

    graph[0].add(new Edge(0,2,5));

    graph[2].add(new Edge(2,1,10));
    graph[2].add(new Edge(2,3,10));
    graph[2].add(new Edge(2,0,10));

    graph[3].add(new Edge(3,2,15));
    graph[3].add(new Edge(3,1,15));

    graph[1].add(new Edge(1,2,20));
    graph[1].add(new Edge(1,3,20));
   }


    public static void main(String[] args) {
        int V = 4;
        ArrayList<Edge> graph[] = new ArrayList[V];

        creatingGraph(graph);
        for(int i = 0;i<graph[2].size();i++){
            System.out.print(graph[2].get(i).weight+" ");
        }
    }
}