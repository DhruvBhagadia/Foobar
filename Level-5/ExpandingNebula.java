import java.util.Arrays;
import java.util.ArrayList;
import java.util.Map;
import java.util.Iterator;
import java.util.HashMap;
import java.util.HashSet;
import java.lang.Math;

public class ExpandingNebula{
	public static void main(String args[]){
		boolean[][] g = {{true, true, false, true, false, true, false, true, true, false}, {true, true, false, false, false, false, true, true, true, false}, {true, true, false, false, false, false, false, false, false, true}, {false, true, false, false, false, false, true, true, false, false}};
		// boolean[][] g = {{true, false, true}, {false, true, false}, {true, false, true}};
		// boolean[][] g = {{true, false, true, false, false, true, true, true}, {true, false, true, false, false, false, true, false}, {true, true, true, false, false, false, true, false}, {true, false, true, false, false, false, true, false}, {true, false, true, false, false, true, true, true}};
		System.out.println(solution(g));
	}

	public static boolean[][] transpose(boolean[][] g){
		boolean[][] trans = new boolean[g[0].length][g.length];
		for(int i=0; i<g[0].length; i++){
			for(int j=0; j<g.length; j++)
				trans[i][j] = g[j][i];
		}
		return trans;
	}

	public static int generate(int x, int y, int len){
		int a = x & ~(1<<len);
		int b = y & ~(1<<len);
		int c = x>>1;
		int d = y>>1;
		return ((a&~b&~c&~d) | (~a&b&~c&~d) | (~a&~b&c&~d) | (~a&~b&~c&d));
	}

	public static HashMap<String, ArrayList<Integer>> build_map(int n, ArrayList<Integer> counts){
		HashMap<String, ArrayList<Integer>> map = new HashMap<String, ArrayList<Integer>>();
		HashSet<Integer> num = new HashSet<Integer>();
		for(int i: counts)
			num.add(i);
		for(int i=0; i<Math.pow(2, n+1); i++){
			for(int j=0; j<Math.pow(2, n+1); j++){
				int gen = generate(i, j, n);
				if(num.contains(gen)){
					String str = gen + " " + i;
					ArrayList<Integer> a = new ArrayList<Integer>();
					if(map.containsKey(str))
						a = map.get(str);
					a.add(j);
					map.put(str, a);
				}
			}
		}
		return map;
	}

	public static int solution(boolean[][] g) {
        g = transpose(g);
        int r = g.length;
        int c = g[0].length;
        ArrayList<Integer> counts = new ArrayList<Integer>();
        for(int i=0; i<g.length; i++){
        	int sum = 0;
        	for(int j=0; j<g[i].length; j++){
        		if(g[i][j])
        			sum += Math.pow(2, j);
        	}
        	counts.add(sum);
        }
        HashMap<String, ArrayList<Integer>> map = build_map(c, counts);
	    HashMap<Integer, Integer> pre_image = new HashMap<Integer, Integer>();
	    for(int i=0; i<Math.pow(2, c+1); i++)
	    	pre_image.put(i, 1);
	    for(int i=0; i<counts.size(); i++){
	    	int row = counts.get(i);
	    	HashMap<Integer, Integer> next_row = new HashMap<Integer, Integer>();
	    	Iterator it = pre_image.entrySet().iterator();
		    while (it.hasNext()) {
		    	Map.Entry pair = (Map.Entry)it.next();
		    	int c1 = (int) pair.getKey();
		    	String str = row + " " + c1;
		    	if(map.containsKey(str)){
		    		ArrayList<Integer> a = map.get(str);
			    	for(int c2: a){
				        if(next_row.containsKey(c2))
				       		next_row.put(c2, next_row.get(c2)+pre_image.get(c1));
				       	else
				       		next_row.put(c2, pre_image.get(c1));
				    }
		    	}
		    }
		    pre_image = next_row;
	    }
	    Iterator it = pre_image.entrySet().iterator();
	    int ans = 0;
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        ans += (int) pair.getValue();
	    }
        return ans;
    }
}