import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Pazuk on 28.01.2018.
 */

// Main idea:
// Step 1. Get all values from input file and according with it build array with numbers for matrices
// Step 2.
// Loop iteration:
// - get numbers from array and create next matrix array
// - multiply actual matrix with next matrix, get result matrix
// - mark result matrix as actual matrix
// Next iteration...
// Step 3. After last iteration, get from result matrix requested number (positions specified in input file)


public class Main {


    static int[] list;

    static boolean fileCheckOk;
    static int m;
    static int n;
    static int a;
    static int b;
    static int p;

    static int[][] matrixA;

    public static void main(String[] args) throws FileNotFoundException {

        readFile();
        if(fileCheckOk){
            calculate();

            PrintWriter writer=new PrintWriter("output.txt");
            writer.print(matrixA[a-1][b-1]);
            writer.close();
        }
    }

    static void readFile() throws FileNotFoundException {

        File file=new File("input.txt");
        Scanner scanner=new Scanner(file);

        //general input values:
        m=scanner.nextInt(); // number of matricis
        n=scanner.nextInt(); // matrices size
        a=scanner.nextInt(); // position i of requested number
        b=scanner.nextInt(); // position j of requested number
        p=scanner.nextInt(); // number, by modulo of what all the mathematic operations are processing

        list=new int[m*n*n]; // array length according with quantity of matricis and their size
        int i=0;
        while(scanner.hasNextInt()){
            list[i]=scanner.nextInt();
            i++;
        }
        scanner.close();

        if(m>=1 && m<=130 && n>=1 && n<=130 && a>=1 && a<=n && b>=1 && b<=n && p<=10000 && p>=-10000){
            fileCheckOk=true; //check if input values are comply with task conditions
            p=Math.abs(p);
        }
    }

    /*static void printGeneralInputValues(){
        System.out.print(m +" ");
        System.out.println(n +" ");
        System.out.print(a+" ");
        System.out.println(b+" ");
        System.out.println(p+" ");
        System.out.println();
    }*/

    static void calculate(){ // calculate matrices

        int i, j, k;
        int r=0;
        int t;
        int temp;
        matrixA=new int[n][n]; // actual matrix
        for(int n = 0; n< m; n++){ // number of iterations==matrices quantity
            int[][] matrixB=new int[Main.n][Main.n]; // next matrix
            for(i=0; i< Main.n; i++){
                for(j=0; j< Main.n; j++){
                    matrixB[i][j]=list[r];
                    r++;
                }
            }

            if(n>0){
                int[][] matrixC=new int[Main.n][Main.n]; //result matrix
                for(i=0; i< Main.n; i++){
                    for(k=0; k< Main.n; k++){
                        t=matrixA[i][k];
                        for(j=0; j< Main.n; j++){
                            temp=t*matrixB[k][j]; // multiplying...
                            if (temp>=p){    // compare with number, by modulo of what all
                                             // the mathematic operations are processing

                                temp=temp%p; // and replace if need
                            }
                            matrixC[i][j]=matrixC[i][j]+temp; // ...matrces
                            if(matrixC[i][j]>=p){
                                matrixC[i][j]=matrixC[i][j]%p;
                            }
                        }
                    }
                }
                //printMatrix("C", matrixC);
                matrixA=matrixC;
                //printMatrix("A", matrixA);
            } else {
                matrixA=matrixB;
                //printMatrix("A", matrixA);
            }
        }
    }



    /*static void printMatrix(String text, int[][] matrix){
        if(text.equals("Result")){System.out.println("Matrix RESULT");}
        if(text.equals("A")){System.out.println("Matrix A");}
        if(text.equals("B")){System.out.println("Matrix B");}
        if(text.equals("C")){System.out.println("Matrix C");}

        for(int i = 0; i< n; i++) {
            for(int j = 0; j< n; j++) {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }*/

}
