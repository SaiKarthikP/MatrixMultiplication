
public class DivideAndConquer {
	 public int[][] dcMatMult(int[][] a, int[][] b)
     {
		 
         int n = a.length;
         int [][] c = new int[n][n];
         //base case
         if (n == 1) {
             c[0][0] = a[0][0] * b[0][0];
         }
         else {
        	 //reduce to 8 subproblems
             int[][] a11 = new int[n/2][n/2];
             int[][] a12 = new int[n/2][n/2];
             int[][] a21 = new int[n/2][n/2];
             int[][] a22 = new int[n/2][n/2];
             int[][] b11 = new int[n/2][n/2];
             int[][] b12 = new int[n/2][n/2];
             int[][] b21 = new int[n/2][n/2];
             int[][] b22 = new int[n/2][n/2];
             
             initialize(a, a11, 0 , 0);
             initialize(a, a12, 0 , n/2);
             initialize(a, a21, n/2, 0);
             initialize(a, a22, n/2, n/2);
             initialize(b, b11, 0 , 0);
             initialize(b, b12, 0 , n/2);
             initialize(b, b21, n/2, 0);
             initialize(b, b22, n/2, n/2);

             //recursive calls
             int[][] c11 = add(dcMatMult(a11, b11), dcMatMult(a12, b21));
             int[][] c12 = add(dcMatMult(a11, b12), dcMatMult(a12, b22));
             int[][] c21 = add(dcMatMult(a21, b11), dcMatMult(a22, b21));
             int[][] c22 = add(dcMatMult(a21, b12), dcMatMult(a22, b22));

             //reconstruct matrix C
             combine(c11, c, 0 , 0);
             combine(c12, c, 0 , n/2);
             combine(c21, c, n/2, 0);
             combine(c22, c, n/2, n/2);
         }
         return c;
     }
	
	
	public void initialize(int[][] copyFrom, int[][] copyTo,
            int startRow, int startCol)
    {
        for(int a = 0, a2 = startRow; a<copyTo.length; a++, a2++) {  
            for(int b = 0, b2 = startCol; b<copyTo.length; b++, b2++) {  
                copyTo[a][b] = copyFrom[a2][b2];
            }
        }
    }
	  public int[][] add(int[][] m1, int[][] m2)
      {
          int result[][] = new int[m1.length][m1.length];
          for(int a = 0; a<m1.length; a++) {
              for(int b = 0; b<m1.length; b++) {
                  result[a][b] = m1[a][b] + m2[a][b];
              }
          }
          return result;
      }
	  
	  public void combine(int[][] child, int[][] parent,
              int startRow, int startCol)
      {
          for(int a = 0, a2 = startRow; a<child.length; a++, a2++) {
              for(int b = 0, b2 = startCol; b<child.length; b++, b2++) {
                  parent[a2][b2] = child[a][b];
              }
          }
      }
}
