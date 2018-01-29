import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Pazuk on 28.01.2018.
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException {



        ArrayList<Integer> list=new ArrayList<>();

        File file=new File("input.txt");
        Scanner scanner=new Scanner(file);
        while(scanner.hasNextInt()){
            list.add(scanner.nextInt());
        }
        scanner.close();

        System.out.println(list);
        System.out.println();

        int matrixQuantity=list.get(0);
        int matrixSize=list.get(1);
        int a=list.get(2);
        int b=list.get(3);
        int p=Math.abs(list.get(4));

        System.out.print(matrixQuantity+" ");
        System.out.println(matrixSize+" ");
        System.out.print(a+" ");
        System.out.println(b+" ");
        System.out.println(p+" ");
        System.out.println();

        int i, j, k;
        i=0;
        j=0;
        k=0;

        int r=5;
        int[][] matrixA=new int[matrixSize][matrixSize];
        for(int n=0; n<matrixQuantity; n++){
            int[][] matrixB=new int[matrixSize][matrixSize];
            for(i=0; i<matrixSize; i++){
                for(j=0; j<matrixSize; j++){
                    matrixB[i][j]=list.get(r);
                    System.out.print(matrixB[i][j]+" ");
                    r++;
                }
                System.out.println();
            }
            System.out.println();

            if(n>0){
                int[][] matrixC=new int[matrixSize][matrixSize];
                for(i=0; i<matrixSize; i++){
                    for(j=0; j<matrixSize; j++){
                        for(k=0; k<matrixSize; k++){
                            matrixC[i][j]=matrixC[i][j]+(matrixA[i][k]*matrixB[k][j]);
                            if(matrixC[i][j]>=p){
                                matrixC[i][j]=p;
                            }
                        }
                    }
                }
                matrixA=matrixC;
                for(i=0; i<matrixSize; i++) {
                    for(j=0; j<matrixSize; j++) {
                        System.out.print(matrixA[i][j]+" ");
                    }
                    System.out.println();
                }
                System.out.println();
            } else {
                matrixA=matrixB;
                for(i=0; i<matrixSize; i++) {
                    for(j=0; j<matrixSize; j++) {
                        System.out.print(matrixA[i][j]+" ");
                    }
                    System.out.println();
                }
                System.out.println();
            }


        }

        for(i=0; i<matrixSize; i++) {
            for(j=0; j<matrixSize; j++) {
                System.out.print(matrixA[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();

        System.out.println(matrixA[a-1][b-1]);

        PrintWriter writer=new PrintWriter("output.txt");
        writer.print(matrixA[a-1][b-1]);
        writer.close();


    }
}
