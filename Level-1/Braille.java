import java.util.HashMap;

public class Braille {
		public static void main(String args[]){
			// System.out.println(solution("code"));
			// System.out.println(solution("Braille"));
			System.out.println(solution("The quick brown fox jumps over the lazy dog"));
		}
	
    public static String solution(String s) {
        HashMap<Character, String> map = new HashMap<Character, String>();
        map.put('a', "100000");
        map.put('b', "110000");
        map.put('c', "100100");
        map.put('d', "100110");
        map.put('e', "100010");
        map.put('f', "110100");
        map.put('g', "110110");
        map.put('h', "110010");
        map.put('i', "010100");
        map.put('j', "010110");
        map.put('k', "101000");
        map.put('l', "111000");
        map.put('m', "101100");
        map.put('n', "101110");
        map.put('o', "101010");
        map.put('p', "111100");
        map.put('q', "111110");
        map.put('r', "111010");
        map.put('s', "011100");
        map.put('t', "011110");
        map.put('u', "101001");
        map.put('v', "111001");
        map.put('w', "010111");
        map.put('x', "101101");
        map.put('y', "101111");
        map.put('z', "101011");
        StringBuilder ans = new StringBuilder("");
        for(int i=0; i<s.length(); i++){
            char current = s.charAt(i);
            System.out.println(ans);
            System.out.println(current);
            if(current == ' '){
                ans.append("000000");
                continue;
            }
            else if(Character.isUpperCase(current))
                ans.append("000001");
            current = Character.toLowerCase(current);
            ans.append(map.get(current));
        }
        return ans.toString();
    	// Your code here
    }
}
