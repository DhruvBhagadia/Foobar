import java.util.HashMap;
import java.util.Scanner;
import java.math.BigInteger;

public class Injection {
    static HashMap<BigInteger, Integer> map;
    static BigInteger two;
    static BigInteger one;
    static BigInteger zero;
	public static void  main(String args[]){
		Scanner sc = new Scanner(System.in);
		System.out.println(solution(sc.nextLine()));
	}

    public static int funct(BigInteger num, int depth){
        if(num.equals(one))
            return 0;
        else{
            if(map.containsKey(num))
                return map.get(num);
            else if(num.mod(two).equals(zero)){
                int ans = funct(num.divide(two), depth+1)+1;
                map.put(num, ans);
                return ans;
            }
            else{
                int ans1 = funct(num.add(one), depth+1);
                int ans2 = funct(num.subtract(one), depth+1);
                int ans = (ans1<ans2 ? ans1: ans2)+1;
                map.put(num, ans);
                return ans;
            }
        }
    }

    public static int solution(String x) {
        map = new HashMap<BigInteger, Integer>();
        two = new BigInteger("2");
        one = new BigInteger("1");
        zero = new BigInteger("0");
        return funct(new BigInteger(x), 0);
    }
}