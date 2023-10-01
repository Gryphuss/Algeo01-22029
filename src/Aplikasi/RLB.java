package Aplikasi;
import java.util.*;
import IO.*;
import Matriks.*;

public class RLB {
    public static double[] solveRLB(Matrix m){
        // Matrix m, row adalah jmlh sampel, col adalah jmlh peubah
        Matrix eqMLR = new Matrix(m.getCol(), m.getCol()+1);
        // Tiap kolom i dikali x(i-1)
        m = m.transpose();
        for (int i = 0; i < eqMLR.getRow(); i++) {
            for (int j = 0; j < eqMLR.getCol(); j++) {
                if (i == 0) {
                    if (j == 0) {
                        eqMLR.mem[i][j] = m.getCol();
                    } else {
                        eqMLR.mem[i][j] = eqMLR.sigmaSampel(m, m.getCol(), i, j);
                    }
                } else {
                    if (j == 0) {
                        eqMLR.mem[i][j] = eqMLR.mem[j][i];
                    } else {
                        eqMLR.mem[i][j] = eqMLR.sigmaSampel(m, m.getCol(), i, j);
                    }
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

    public static void menuRLB(){
        Scanner obj = new Scanner(System.in);
        int roww,coll;

        System.out.println("Masukkan jumlah variabel peubah: ");
        coll = obj.nextInt();
        System.out.println("Masukkan jumlah sampel: ");
        roww = obj.nextInt();

        Matrix mat = new Matrix(roww, coll+1);
        Matrix testCase = new Matrix(1,coll);


        if(IOput.inputMode()==1){
            IOput.readFileToMatrixRegresi(mat, testCase);
        }else{
            System.out.println("Masukkan nilai variabel-variabel peubah dan nilai y tiap sampel, dengan tiap sampel dipisahkan oleh enter. Formatnya sebagai berikut.");
            System.out.println("x11 x21 x31 .... xk1 y1\nx12 x22 x32 .... xk2 y2\n....\nx1i x2i x3i .... xki yi");
            IOput.readKeyboardToMatrix(mat);
            System.out.println("Masukkan nilai variabel peubah yang ingin ditaksir nilai y, tiap nilai variabel dipisah dengan spasi");
            for(int i=0;i<coll;i++){
                testCase.mem[0][i] = obj.nextDouble();
            }
        }

        double[] solusi = solveRLB(mat);
        String fungsi = "f(x) = "+String.valueOf(solusi[0]);
        for(int i=1;i<=coll;i++){
            if(solusi[i]>0) fungsi += "+";
            fungsi += String.valueOf(IOput.df.format(solusi[i]))+"x"+String.valueOf(i);
        }
        
        System.out.println("Persamaan regresinya adalah "+fungsi);
        System.out.print("Hasil dari taksiran nya adalah\ny = ");
        double nilaiY = solusi[0];
        for(int i=1;i<=coll;i++){
            nilaiY += testCase.mem[0][i-1]*solusi[i];
        }
        System.out.println(IOput.df.format(nilaiY)+"\n");

    }

    public static void main(String[] args){
        menuRLB();
    }

}
