import java.util.Arrays;
import java.util.Comparator;

public class ElevatorMaintenance {
	public static void main(String args[]){
		String[] l = {"1.1.2", "1.0", "1.3.3", "1.0.12", "1.0.2"};
		System.out.println(Arrays.toString(solution(l)));
	}

	public static String[] solution(String[] l) {
 		Arrays.sort(l, new Comparator<String>() {
 			public int compare(String a, String b){
 				//return -1 when a is smaller than b, 1 when a is greater than b and 0 
 				//if both are same

 				String[] content_a = a.split("\\.");
 				String[] content_b = b.split("\\.");
 				int i=0, j=0;
 				while(i<content_a.length && j<content_b.length){
 					if(Integer.valueOf(content_a[i]) < Integer.valueOf(content_b[j]))
 						return -1;
 					else if(Integer.valueOf(content_a[i]) > Integer.valueOf(content_b[j]))
 						return 1;
 					else{
 						i++; j++;
 					}
 				}
 				if(j == content_b.length)
 					return 1;
 				else
 					return -1;	
 			}
 		});     
 		return l;
    }
}