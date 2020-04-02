import java.util.*;

public class QueueToDo {
	public static void  main(String args[]){
		Scanner sc = new Scanner(System.in);
		System.out.println(solution(sc.nextInt(), sc.nextInt()));
	}

    public static int solution(int start, int length) {
        int ans = 0;
        int dup = length;
        for(int i=start; length >= 1; i += dup){
        	int ctr=0;
        	int temp = i;
        	while(ctr != length){
        		ans = ans^temp;
        		ctr++;
        		temp++;
        	}
        	length--;
        }
        return ans;
    }
}