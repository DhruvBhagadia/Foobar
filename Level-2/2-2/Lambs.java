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

// public static int solution(int total_lambs) {
//         if(total_lambs == 1)
//         	return 0;
//         else{
//         	int generous=0, stingy=2;
//         	int i=0;
//         	while(Math.pow(2, i)<=total_lambs)
//         		i++;
//         	generous = i-1;
//         	int a=1;
//         	int b=1;
//         	int sum = a+b;
//         	while(sum<=total_lambs){
//         		stingy++;
//         		int c = a+b;
//         		a = b;
//         		b = c;
//         		sum += c;
//         	}
//         	stingy = stingy-1;
//         	return (stingy-generous);
//         }
//     }