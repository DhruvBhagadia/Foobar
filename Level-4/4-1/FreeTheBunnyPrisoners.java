import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class FreeTheBunnyPrisoners{
	public static void main(String args[]){
		int[][] a = solution(5, 3);
		for(int i=0; i<a.length; i++){
			for(int j=0; j<a[i].length; j++)
				System.out.print(a[i][j] + " ");
			System.out.println();
		}
	}

	public static void generateCombinations(List<int[]> combinations, int data[], int start, int end, int index) {
	    if (index == data.length) {
	        int[] combination = data.clone();
	        combinations.add(combination);
	    } else if (start <= end) {
	        data[index] = start;
	        generateCombinations(combinations, data, start+1, end, index+1);
	        generateCombinations(combinations, data, start+1, end, index);
	    }
	}

	static int nCr(int n, int r){ 
	    return fact(n)/(fact(r)*fact(n-r)); 
	} 
	  
	static int fact(int n) { 
	    int res = 1; 
	    for (int i=2; i<=n; i++) 
	        res = res*i; 
	    return res; 
	}

	public static int[][] solution(int num_buns, int num_required) {
		int available = num_buns;
		int required = num_required;
		int[] bunnies = new int[available];
		for(int i=0; i<available; i++)
			bunnies[i] = i;
		int x = (available-required)+1;
		List<int[]> combinations = new ArrayList<>();
    	generateCombinations(combinations, new int[x], 0, available-1, 0);
    	int keys = nCr(available, x);
    	ArrayList<Integer>[] temp = new ArrayList[available];
    	for (int i=0; i<available; i++) 
    		temp[i] = new ArrayList<Integer>();
    	for(int i=0; i<keys; i++){
    		int[] combination = combinations.get(i);
    		for(int j=0; j<combination.length; j++)
    			temp[combination[j]].add(i);
    	}
    	int[][] ans = new int[available][temp[0].size()];
    	for(int i=0; i<temp.length; i++)
    		for(int j=0; j<temp[i].size(); j++)
    			ans[i][j] = temp[i].get(j).intValue();
        return ans;
    }
}