import java.util.Arrays;
import java.util.ArrayList;
import java.lang.Math;

public class DoomsDay {
    static class Fraction{
        int numerator;
        int denominator;
     
        public Fraction(int numr, int denr) {
            if(numr == 0){
                numerator = 0;
                denominator = 0;
            }
            else{
                numerator = numr;
                denominator = denr;
                reduce();
            }
        }

        public int getNumerator() {
            return numerator;
        }
     
        public void setNumerator(int numerator) {
            this.numerator = numerator;
        }
     
        public int getDenominator() {
            return denominator;
        }
     
        public void setDenominator(int denominator) {
            this.denominator = denominator;
        }

        public int calculateGCD(int numerator, int denominator) {
            if (numerator % denominator == 0) {
                     return denominator;
                }
            return calculateGCD(denominator, numerator % denominator);
        }

        void reduce() {
            int gcd = calculateGCD(numerator, denominator);
            numerator /= gcd;
            denominator /= gcd;
        }

        public Fraction add(Fraction fractionTwo) {
            int numer = (numerator * fractionTwo.getDenominator()) + 
                                    (fractionTwo.getNumerator() * denominator);
            int denr = denominator * fractionTwo.getDenominator();
            if(numerator == 0 && denominator == 0){
                numer = fractionTwo.getNumerator();
                denr = fractionTwo.getDenominator();
            }
            else if(fractionTwo.getNumerator() == 0 && fractionTwo.getDenominator() == 0){
                numer = numerator;
                denr = denominator;
            }            
            return new Fraction(numer, denr);
        }

        public Fraction subtract(Fraction fractionTwo) {
            int newNumerator = (numerator * fractionTwo.denominator) - 
                                     (fractionTwo.numerator * denominator);
            int newDenominator = denominator * fractionTwo.denominator;
            if(numerator == 0 && denominator == 0){
                newNumerator = -fractionTwo.getNumerator();
                newDenominator = fractionTwo.getDenominator();
            }
            else if(fractionTwo.getNumerator() == 0 && fractionTwo.getDenominator() == 0){
                newNumerator = numerator;
                newDenominator = denominator;
            }            
            Fraction result = new Fraction(newNumerator, newDenominator);
            return result;
        }

        public Fraction multiply(Fraction fractionTwo) {
            int newNumerator = numerator * fractionTwo.numerator;
            int newDenominator = denominator * fractionTwo.denominator;
            Fraction result = new Fraction(newNumerator, newDenominator);
            return result;
        }

        public Fraction divide(Fraction fractionTwo) {
            int newNumerator = numerator * fractionTwo.getDenominator();
            int newDenominator = denominator * fractionTwo.numerator;
            Fraction result = new Fraction(newNumerator, newDenominator);
            return result;
        }

        @Override
        public String toString() {
            return this.numerator + "/" + this.denominator;
        }
    }

    static Fraction[][] subtraction(Fraction[][] A, Fraction[][] B){
        Fraction[][] ans = new Fraction[A.length][A.length];
        for(int i=0; i<A.length; i++)
            for(int j=0; j<A.length; j++)
                ans[i][j] = A[i][j].subtract(B[i][j]);
        return ans;
    }

    static Fraction determinant(Fraction arr[][], int n){ 
        Fraction D = new Fraction(0, 0);
        Fraction sign = new Fraction(1, 1);
        Fraction nsign = new Fraction(-1, 1);
        if (n == 1) 
            return arr[0][0];
        if (n== 2) {
            Fraction a = arr[0][0].multiply(arr[1][1]);
            Fraction b = arr[0][1].multiply(arr[1][0]);
            return a.subtract(b);
        }
        for (int i=0; i<n; i++) {
            Fraction temp[][] = new Fraction[n-1][n-1];
            for (int j=1; j<n; j++) {
                for (int k=0; k<n; k++) {
                    if (k < i) 
                        temp[j-1][k] = arr[j][k];
                    else if (k > i) 
                        temp[j-1][k-1] = arr[j][k];
                }
            }
            Fraction f = determinant(temp, n-1);
            if(Math.pow(-1,i) == -1)
                D = D.add(nsign.multiply(arr[0][i]).multiply(f));
            else
                D = D.add(sign.multiply(arr[0][i]).multiply(f));
        }
        return D;
    } 

    static Fraction[][] minor(Fraction arr[][], int row, int column){
        int n = arr.length;
        Fraction minor[][] = new Fraction[n-1][n-1];

        for (int i=0; i<n; i++)
            for (int j=0; i != row && j<n; j++)
                if (j != column)
                    minor[i<row ? i: i-1][j<column ? j: j-1] = arr[i][j];

        return minor;
    }

    static Fraction[][] inverse(Fraction arr[][], int n){
        Fraction[][] inv = new Fraction[n][n];
        Fraction sign = new Fraction(1, 1);
        Fraction nsign = new Fraction(-1, 1);

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                Fraction f;
                if(Math.pow(-1,(i+j)) == -1)
                    f = nsign.multiply(determinant(minor(arr, i, j), n-1));
                else
                    f = sign.multiply(determinant(minor(arr, i, j), n-1));
                inv[i][j] = f;
            }
        }
        Fraction one = new Fraction(1, 1);
        Fraction det = one.divide(determinant(arr, n));
        for(int i=0; i<n; i++){
            for(int j=0; j<=i; j++){
                Fraction temp = inv[i][j];
                inv[i][j] = inv[j][i].multiply(det);
                inv[j][i] = temp.multiply(det);
            }
        }
        return inv;
    }

    static Fraction[][] multiplyMatrices(Fraction[][] one, Fraction[][] two){
        int m = one.length;
        int p = two.length;
        int q = two[0].length;
        Fraction[][] mul = new Fraction[m][q];
        Fraction sum = new Fraction(0, 0);
        for (int i=0; i<m; i++) {
            for (int j=0; j<q; j++) {
                for (int k=0; k<p; k++)
                    sum = sum.add(one[i][k].multiply(two[k][j]));
                mul[i][j] = sum;
              sum = new Fraction(0, 0);
            }
        }
        return mul;
    }

    static int gcd(int a, int b) {
        if (a%b == 0)
            return b;
        return gcd(b, a%b);
    }

    static int lcm(int n1, int n2){
        return ((int)(n1*n2)/(int)gcd(n1, n2));
    }

    static int lcmOfAList(ArrayList<Integer> arrlist){
        int n1 = arrlist.get(0);
        int n2 = arrlist.get(1);
        int ans = lcm(n1, n2);
        for(int i=2; i<arrlist.size(); i++){
            ans = lcm(ans, arrlist.get(i));
        }
        return ans;
    }

	public static void  main(String args[]){
        int[][] arr ={{0, 1, 0, 0, 0, 1}, {4, 0, 0, 3, 2, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}};
        System.out.println(Arrays.toString(solution(arr)));
	}

    public static int[] solution(int[][] m) {
        ArrayList<Integer> terminating = new ArrayList<Integer>();
        ArrayList<Integer> non_terminating = new ArrayList<Integer>();
        Fraction[][] matrix = new Fraction[m.length][m[0].length];
        Boolean source_terminates = false;
        for(int i=0; i<m.length; i++){
            int sum=0;
            for(int j=0; j<m[i].length; j++)
                sum += m[i][j];
            if(sum == 0){
                if(i == 0)
                    source_terminates = true;
                terminating.add(i);
            }
            else
                non_terminating.add(i);
            for(int j=0; j<m[i].length; j++){
                Fraction f = new Fraction(m[i][j], sum); 
                matrix[i][j] = f;
            }
        }
        if(source_terminates){
            int[] ans = new int[terminating.size()+1];
            ans[0] = 1;
            int i;
            for(i=0; i<terminating.size(); i++)
                ans[i+1] = 0;
            ans[i] = 1;
            return ans;
        }
        int[][] cloned_m = (int[][]) m.clone();
        Fraction[][] cloned_matrix = new Fraction[matrix.length][matrix[0].length];
        for (int i=0; i<matrix.length; i++) {
            cloned_matrix[i] = (Fraction[]) matrix[i].clone();
        }
        int n = non_terminating.size();
        non_terminating.addAll(terminating);
        for(int i=0; i<non_terminating.size(); i++){
            for(int j=0; j<non_terminating.size(); j++){
                cloned_matrix[i][j] = matrix[non_terminating.get(i)][non_terminating.get(j)];
            }
        }
        matrix = cloned_matrix;
        Fraction[][] Q = new Fraction[n][n];
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++)
                Q[i][j] = matrix[i][j];
        }
        Fraction[][] R = new Fraction[n][non_terminating.size()-n];
        for(int i=0; i<n; i++){
            for(int j=0; j<(non_terminating.size()-n); j++)
                R[i][j] = matrix[i][j+n];
        }
        Fraction[][] identity = new Fraction[n][n];
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                Fraction f;
                if(i == j)
                    f = new Fraction(1, 1);
                else
                    f = new Fraction(0, 0);
                identity[i][j] = f;
            }
        }
        Fraction[][] tmp = subtraction(identity, Q);
        Fraction[][] F = inverse(tmp, tmp.length);
        Fraction[][] fin = multiplyMatrices(F, R);
        ArrayList<Integer> denominators = new ArrayList<Integer>();
        int[] numerators = new int[fin[0].length+1];
        for(int i=0; i<fin[0].length; i++){
            numerators[i] = fin[0][i].getNumerator();
            int den = fin[0][i].getDenominator();
            if(den != 0)
                denominators.add(den);
        }
        int lcm = lcmOfAList(denominators);
        denominators.clear();
        for(int i=0; i<fin[0].length; i++)
            denominators.add(fin[0][i].getDenominator());
        for(int i=0; i<fin[0].length; i++){
            if(denominators.get(i) == 0)
                continue;
            int a = lcm/denominators.get(i);
            numerators[i] = numerators[i]*a;
        }
        numerators[fin[0].length] = lcm;
        return numerators;
    }
}