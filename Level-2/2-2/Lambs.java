import java.util.*;

public class Lambs {
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		System.out.println(solution(sc.nextInt()));
	}

	public static int solution(int total_lambs) {
        	int generous=0, i=0, sum=0, stingy=2;
        	//generous case is when you allocate in terms of power of 2
        	while(true){
        		sum += Math.pow(2, i);
        		if(sum > total_lambs)
        			break;
        		i++;
        	}
        	generous = i;
        	//stingy case is when you allocate in terms of fibonacci series
        	int a=1, b=1;
        	sum = a+b;
        	while(sum<=total_lambs){
        		stingy++;
        		int c = a+b;
        		a = b;
        		b = c;
        		sum += c;
        	}
        	stingy = stingy-1;
        	return (stingy-generous);
    }
}