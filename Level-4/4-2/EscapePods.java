import java.lang.Integer;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;

public class EscapePods{
	public static void main(String args[]){
		int[] entrances = {0};
		int[] exits = {3};
		int[][] path = {{0, 7, 0, 0}, {0, 0, 6, 0}, {0, 0, 0, 8}, {9, 0, 0, 0}};
		System.out.println(solution(entrances, exits, path));
	}

	public static Boolean bfs(int[][] graph, int[] parent){
		int n = graph.length;
		int[] parents = new int[n];
		Boolean[] visited = new Boolean[n];
		for(int i=0; i<n; i++)
			visited[i] = false;
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(0);
		parent[0] = -1;
		visited[0] = true;
		while(!q.isEmpty()){
			int current = q.remove();
			for(int i=0; i<n; i++){
				if(!visited[i] && graph[current][i] != 0){
					q.add(i);
					visited[i] = true;
					parent[i] = current;
				}
			}
		}
		if(visited[n-1])
			return true;
		return false;
	}

	public static int solution(int[] entrances, int[] exits, int[][] path) {
        int n = path.length;
        int ans = 0;
        int[][] residual_graph = new int[n+2][n+2];
        for(int i=1; i<n+1; i++){
        	for(int j=1; j<n+1; j++)
        		residual_graph[i][j] = path[i-1][j-1];
        }
        for(int i=0; i<entrances.length; i++)
        	residual_graph[0][entrances[i]+1] = Integer.MAX_VALUE;
        for(int i=0; i<exits.length; i++)
        	residual_graph[exits[i]+1][n+1] = Integer.MAX_VALUE;
        int[] parent = new int[n+2];
        while(bfs(residual_graph, parent)){
        	int min_flow = Integer.MAX_VALUE;
        	int vertex = n+1;
        	while(vertex != 0){
        		int p = parent[vertex];
        		min_flow = Math.min(min_flow, residual_graph[p][vertex]);
        		vertex = p;
        	}
        	ans += min_flow;
        	vertex = n+1;
        	while(vertex != 0){
        		int p = parent[vertex];
        		residual_graph[p][vertex] = residual_graph[p][vertex]-min_flow;
        		vertex = p;
        	}
        }
        return ans;
    }
}