import java.util.Scanner;
public class StarTriangle {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        
        for(int i=0;i<n;i++) {
            for(int j=0;j<n-i-1;j++) {
                System.out.print(" ");
            }
            for(int k=0;k<2*i+1;k++) {
                System.out.print("*");
            }
            System.out.println();
        }
        input.close();
    }
}
