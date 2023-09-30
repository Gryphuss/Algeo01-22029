package Aplikasi;
import java.util.*;
import Matriks.*;

public class SPL {
    public static void displayArray(String[] arr){
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }
    public static boolean cekAllArray(int[] arr, int val){
        for(int i=0;i<arr.length;i++){
            if (arr[i]!=val) return false;
        }
        return true;
    }

    public static boolean cekAllArrayIdx(double[] arr, int val,int awal,int akhir){
        for(int i=awal;i<=akhir;i++){
            if (arr[i]!=val) return false;
        }
        return true;
    }

    public static String[] SPLGauss(Matrix m){
        int row = m.getRow(),col = m.getCol();
        
        double[] konstanta = new double[col-1];
        Arrays.fill(konstanta,0);

        double[][] jmlhParam = new double[col-1][col-1];
        for(int i=0;i<col-1;i++){
            Arrays.fill(jmlhParam[i],0);
        }
        String[] solusi = new String[col-1];
        Arrays.fill(solusi, "");

        OBE.Gauss(m);
        if(cekAllArrayIdx(m.mem[row-1], 0, 0, col-2) && m.mem[row-1][col-1]!=0) return solusi;

        char varParam = 'a';
        for(int i=row-1;i>=0;i--){
            int colLead = m.findColLead1(i);
            if (colLead==-1) continue;
            konstanta[colLead] = m.mem[i][col-1];

            for(int j=colLead+1;j<col-1;j++){
                if(m.mem[i][j]!=0 ){
                    if(solusi[j]==""){
                        jmlhParam[j][j] = 1;
                        jmlhParam[colLead][j] -= m.mem[i][j];

                        solusi[j] += String.valueOf(varParam);
                        varParam += 1;
                    }else{
                        for(int k=0;k<col-1;k++){
                            jmlhParam[colLead][k] -= m.mem[i][j]*jmlhParam[j][k];
                        }
                    }
                    konstanta[colLead] -= konstanta[j]*m.mem[i][j];
                }
            }

            for(int j=colLead+1;j<col-1;j++){
                if(jmlhParam[colLead][j]!=0){
                    if(solusi[colLead]!=""){
                        if(jmlhParam[colLead][j]>0){
                            solusi[colLead] += "+";
                        }
                    }
                    if(jmlhParam[colLead][j]==-1){
                        solusi[colLead] += "-";
                    }else if(jmlhParam[colLead][j]!=1){
                        solusi[colLead] += String.valueOf(jmlhParam[colLead][j]); 
                    }
                    solusi[colLead] += String.valueOf(solusi[j]);
                }
            }
            if(konstanta[colLead]>0 && solusi[colLead]!="") solusi[colLead] += "+";
            if(konstanta[colLead]!=0 || solusi[colLead]==""){
                solusi[colLead] += String.valueOf(konstanta[colLead]);
            }
        }
        return solusi;    
    }    

    public static String[] SPLGaussJordan(Matrix m){
        int row = m.getRow(),col = m.getCol();

        String[] solusi = new String[col-1];
        Arrays.fill(solusi, "");

        OBE.GaussJordan(m);
        if(cekAllArrayIdx(m.mem[row-1], 0, 0, col-2) && m.mem[row-1][col-1]!=0) return solusi;
        
        char varParam = 'a';
        for(int i=row-1;i>=0;i--){
            int colLead = m.findColLead1(i);
            if (colLead==-1) continue;
            double konstanta = m.mem[i][col-1];

            for(int j=colLead+1;j<col-1;j++){
                if(m.mem[i][j]!=0 ){
                    if(solusi[j]==""){
                        solusi[j] += String.valueOf(varParam);
                        varParam += 1;
                    }

                    if(m.mem[i][j]>0){
                        solusi[colLead] += "-";
                        if(m.mem[i][j]!=1) solusi[colLead] += String.valueOf(m.mem[i][j]);
                        solusi[colLead] += solusi[j];
                    }else{
                        double val = m.mem[i][j]*(-1);
                        if(solusi[colLead]!="") solusi[colLead] += "+";
                        if(val!=1) solusi[colLead] += String.valueOf(val); 
                        solusi[colLead] += solusi[j];
                    }
                }
            }
            if(konstanta>0 && solusi[colLead]!="") solusi[colLead] += "+";
            if(konstanta!=0 || solusi[colLead]==""){
                solusi[colLead] += String.valueOf(konstanta);
            }
        }
        return solusi;
    }

    public static String kaidahCrammer(Matrix m) {
        String out = new String();
        Matrix m1 = new Matrix(m.getRow(), m.getCol() - 1);
        Matrix m2 = new Matrix(m.getRow(), m.getCol() - m1.getCol());
        m1.mem = new double[m1.getRow()][m1.getCol()];
        m2.mem = new double[m2.getRow()][m2.getCol()];
        for (int i = 0; i < m.getRow(); i++) {
            for (int j = 0; j < m.getCol() - 1; j++) {
                m1.mem[i][j] = m.mem[i][j];
            }
        }
        for (int i = 0; i < m.getRow(); i++) {
            int k = 0;
            for (int j = m.getCol() - 1; j < m.getCol(); j++) {
                m2.mem[i][k] = m.mem[i][j];
                k++;
            }
        }
        
        if (m1.isSquare()) {
            if (m1.isSingular()) {
                out = "SPL memiliki banyak solusi atau tidak memiliki solusi sehingga tidak dapat menggunakan Kaidah Crammer.\n";
            } else {
                double det = OBE.determinanKofaktor(m1);
                double[] Xn = new double[m.getRow()];
                Matrix temp = new Matrix(m1.getRow(), m1.getCol());
                temp.mem = new double[temp.getRow()][temp.getCol()];
                for (int i = 0; i < m1.getCol(); i++) {
                    for (int a = 0; a < m1.getRow(); a++) {
                        for (int b = 0; b < m1.getCol(); b++) {
                            temp.mem[a][b] = m1.mem[a][b];
                        }
                    }
                    for (int j = 0; j < m1.getRow(); j++) {
                        temp.mem[j][i] = m2.mem[j][0];
                    }
                    Xn[i] = OBE.determinanKofaktor(temp) / det;
                }
                for (int i = 0; i < m.getRow(); i++) {
                    double ans = Xn[i];
                    out += ("x" + (i + 1) + " = " + String.format("%.2f", ans) + "\n");
                }
            }
        } else {
            out = "SPL tidak bisa diselesaikan menggunakan Kaidah Cramer.\n";
        }
        return out;
    }
}