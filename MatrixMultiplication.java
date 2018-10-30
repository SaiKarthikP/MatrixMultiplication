

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MatrixMultiplication {
	  
	public static void main(String[] args) {
		long startTime, endTime;
		int size;
		int[][] mat1,mat2,mat3,mat4,mat5;
		
		System.out.println("Comparing NxN Matrix multiplication using different methods");
		System.out.print("Enter a value for N:");
		Scanner sc = new Scanner(System.in);
		
		//generate the two matrices to multiply
		size = sc.nextInt();
		sc.nextLine();
		
		mat1 = randomMatGen(size,size); 
		mat2 = randomMatGen(size,size);
		
		//print matrix A
		System.out.println("Matrix 1:");
		printMat(mat1);
		//print matrix B
		System.out.println("Matrix 2:");
		printMat(mat2);
		
		
		System.out.println("\nResult of Mat1*Mat2 using Classical Method:");
		
		startTime = System.currentTimeMillis();
		
		Classical classicMat = new Classical();
		mat3 = classicMat.classicMatMult(mat1, mat2);
		endTime = System.currentTimeMillis();
		
		printMat(mat3);
		System.out.println("Time taken: " + (endTime - startTime) + "ms");
		
		System.out.println("\nResult of Mat1*Mat2 using Recursive (Divide and Conquer) Method:");
			
		DivideAndConquer dcMat = new DivideAndConquer();
		startTime = System.currentTimeMillis();
		mat4 = dcMat.dcMatMult(mat1, mat2);
		endTime = System.currentTimeMillis();
		printMat(mat4);
		
		System.out.println("Time taken: " + (endTime - startTime) + "ms");
		
		System.out.println("\nResult of Mat1*Mat2 using Strassen Method:");
			
		Strassen sMat = new Strassen();
		startTime = System.currentTimeMillis();
		mat5 = sMat.strassenMatMult(mat1, mat2);
		endTime = System.currentTimeMillis();
		printMat(mat5);
		System.out.println("Time taken: " + (endTime - startTime) + "ms");

	}
	
	//random matrix generator of size mxn
	public static int[][] randomMatGen(int m, int n) {
		Random rand = new Random(); 
        int[][] a = new int[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                a[i][j] = rand.nextInt(2); 
        return a;
    }
	
	public static void printMat(int[][] mat){
		for (int[] row:mat)
			System.out.println(Arrays.toString(row));
	}
	
}
