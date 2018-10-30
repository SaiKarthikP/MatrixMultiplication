
public class Strassen {
    public int[][] strassenMatMult(int [][] a, int [][] b)
    {
        int n = a.length;
        int [][] c = new int[n][n];

        if (n == 1) {
            c[0][0] = a[0][0] * b[0][0];
        }
    else {
    	
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
        int[][] m1 = strassenMatMult(add(a11, a22), add(b11, b22));
        int[][] m2 = strassenMatMult(add(a21, a22), b11);
        int[][] m3 = strassenMatMult(a11, subtract(b12, b22));
        int[][] m4 = strassenMatMult(a22, subtract(b21, b11));
        int[][] m5 = strassenMatMult(add(a11, a12), b22);
        int[][] m6 = strassenMatMult(subtract(a21, a11), add(b11, b12));
        int[][] m7 = strassenMatMult(subtract(a12, a22), add(b21, b22));

        int[][] c11 = add(subtract(add(m1, m4), m5), m7);
        int[][] c12 = add(m3, m5);
        int[][] c21 = add(m2, m4);
        int[][] c22 = add(subtract(add(m1, m3), m2), m6);

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
	  
	  public int[][] subtract(int[][] m1, int[][] m2)
      {
          int result[][] = new int[m1.length][m1.length];
          for(int a = 0; a<m1.length; a++) {
              for(int b = 0; b<m1.length; b++) {
                  result[a][b] = m1[a][b] - m2[a][b];
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
