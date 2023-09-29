package Aplikasi;

public class RBL {
    public static double[] solveRLB(Matrix m){
        // Matrix m, row adalah jmlh sampel, col adalah jmlh peubah
        Matrix eqMLR = new Matrix(m.getCol()+1, m.getCol()+2);
        // Tiap kolom i dikali x(i-1)

        for(int i=0;i<eqMLR.getCol();i++){
            for(int j=0;j<eqMLR.getRow();i++){
                //Ketika column = 0, dikali 1
                double multipI=1,multipJ=1;
                for(int k=0;k<m.getRow();k++){
                    if(i!=0) multipI = m.mem[k][i-1];
                    if(j!=0) multipJ = m.mem[k][j-1];
                    eqMLR.mem[j][i] += multipI*multipJ;
                }    
            }
        }
        String[] solusiString = SPL.SPLGauss(eqMLR);
        double[] solusiDouble = new double[solusiString.length];
        for(int i=0;i<solusiString.length;i++){
            solusiDouble[i] = Double.valueOf(solusiString[i]);
        }
        
        return solusiDouble;
    }


}
