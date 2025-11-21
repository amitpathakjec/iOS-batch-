package Graphs.DFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;


public class AdjMatrix_DFS {
    public static void main(String[] args) {
        System.out.println("Please enter the graph edges like this: '0 1, 1 2'");
        Scanner sc = new Scanner(System.in);
        ArrayList<ArrayList<Integer>> edgeList = new ArrayList<>();

        // taking edges input
        while(sc.hasNext()){
            int u = sc.nextInt();
            int v = sc.nextInt();
            edgeList.add(new ArrayList<>(Arrays.asList(u, v)));
        }

        // obtaining set of nodes
        HashSet<Integer> nodes = new HashSet<>();
        for(ArrayList<Integer> edge : edgeList){
            int u = edge.get(0);
            int v = edge.get(1);
            nodes.add(u);
            nodes.add(v);
        }

        // adjMatrix creation
        int n = nodes.size();
        int[][] adjMatrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                adjMatrix[i][j] = 0;
            }
        }

        // filling adjMatrix
        for(ArrayList<Integer> edge : edgeList){
            int u = edge.get(0);
            int v = edge.get(1);
            adjMatrix[u][v] = 1;
            adjMatrix[v][u] = 1;
        }

        // logging adjMatrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(adjMatrix[i][j] + " ");
            }
            System.out.println();
        }

        // dfs traversal
        boolean[] visited = new boolean[adjMatrix.length];
        System.out.println("DFS traversal of graph starting from 0 is: ");
        dfs(adjMatrix, 0, visited);

        // if disconnected componenets:
        /*for(int i = 0; i < n; i++){
            if(!visited[i]){
                dfs(adjMatrix, i, visited);
            }
        }*/

    }


    public static void dfs(int[][] adjMatrix, int node, boolean[] visited){

        if(visited[node]){
            return;
        }

        visited[node] = true;
        System.out.print(node + " ");
        for(int nei = 0; nei < adjMatrix.length; nei++){
            if(adjMatrix[node][nei] == 1){
                dfs(adjMatrix, nei, visited);
            }
        }
    }
}
