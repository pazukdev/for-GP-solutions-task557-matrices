import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Pazuk on 28.01.2018.
 */
public class Main {

    static ArrayList<Integer> list;

    static boolean fileCheckOk;
    static int m;
    static int n;
    static int a;
    static int b;
    static int p;

    static int[][] matrixA;

    public static void main(String[] args) throws FileNotFoundException {

        readFile();
        //printGeneralInputValues();
        if(fileCheckOk){
            calculate();

            PrintWriter writer=new PrintWriter("output.txt");
            writer.print(matrixA[a-1][b-1]);
            writer.close();
        }

    }



    static void readFile() throws FileNotFoundException {
        list=new ArrayList<>();

        File file=new File("input.txt");
        Scanner scanner=new Scanner(file);
        while(scanner.hasNextInt()){
            list.add(scanner.nextInt());
        }
        scanner.close();
        //System.out.println(list);
        //System.out.println();

        m =list.get(0);
        n =list.get(1);
        a=list.get(2);
        b=list.get(3);
        p=list.get(4);
        //System.out.println(p);
        if(m>=1 && m<=130 && n>=1 && n<=130 && a>=1 && a<=n && b>=1 && b<=n && p<=10000){
            fileCheckOk=true;
            p=Math.abs(p);
        }
        //System.out.println(fileCheckOk);
        //System.out.println(p);
    }

    static void printGeneralInputValues(){
        System.out.print(m +" ");
        System.out.println(n +" ");
        System.out.print(a+" ");
        System.out.println(b+" ");
        System.out.println(p+" ");
        System.out.println();
    }

    static void calculate(){

        int i, j, k;
        int r=5;
        matrixA=new int[n][n];
        for(int n = 0; n< m; n++){
            int[][] matrixB=new int[Main.n][Main.n];
            for(i=0; i< Main.n; i++){
                for(j=0; j< Main.n; j++){
                    matrixB[i][j]=list.get(r);
                    r++;
                }
            }
            //printMatrix("B", matrixB);

            if(n>0){
                int[][] matrixC=new int[Main.n][Main.n];
                for(i=0; i< Main.n; i++){
                    for(j=0; j< Main.n; j++){
                        for(k=0; k< Main.n; k++){
                            int temp=matrixA[i][k]*matrixB[k][j];
                            if (temp>=p){
                                temp=temp%p;
                            }
                            matrixC[i][j]=matrixC[i][j]+temp;
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

        //printMatrix("Result", matrixA);
        //System.out.println(matrixA[a-1][b-1]);
    }



    static void printMatrix(String text, int[][] matrix){
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
    }

}
