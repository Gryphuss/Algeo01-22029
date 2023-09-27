package Aplikasi;
import java.util.*;
import Matriks.*;

public class SPL {
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
        int varParam = 97,row = m.getRow(),col = m.getCol();
        
        double[] konstanta = new double[col-1];
        Arrays.fill(konstanta,-9999);

        int[][] jmlhParam = new int[col-1][col-1];
        for(int i=0;i<col-1;i++){
            Arrays.fill(jmlhParam[i],-9999);
        }
        String[] solusi = new String[col-1];
        Arrays.fill(solusi, "");

        OBE.Gauss(m);
        if(cekAllArrayIdx(m.mem[row-1], 0, 0, col-2) && m.mem[row-1][col-1]!=0) return solusi;

        for(int i=row-1;i>=0;i--){
            int colLead = m.findColLead1(i);
            if (colLead==-1) continue;
            konstanta[colLead] = m.mem[i][col-1];
            Arrays.fill(jmlhParam[colLead], 0);

            for(int j=colLead+1;j<col-1;j++){
                if(m.mem[i][j]!=0 ){
                    if(cekAllArray(jmlhParam[j],-9999) && konstanta[j]==-9999){
                        Arrays.fill(jmlhParam[j], 0);
                        jmlhParam[j][j] = 1;
                        konstanta[j] = 0;
                        jmlhParam[colLead][j] -= m.mem[i][j];

                        solusi[j] +=(char) varParam;
                        varParam += 1;
                    }else{
                        for(int k=0;k<col-1;k++){
                            jmlhParam[colLead][k] -= m.mem[i][j]*jmlhParam[j][k];
                        }
                    }
                    konstanta[colLead] -= konstanta[j]*m.mem[i][j];
                }
            }
            if (!cekAllArray(jmlhParam[colLead], -9999)){
                for(int j=0;j<col-1;j++){
                    if(jmlhParam[colLead][j]!=0){
                        solusi[colLead] += jmlhParam[colLead][j]+solusi[j]+"+";
                    }
                }
            }
            solusi[colLead] += konstanta[colLead];
        }
        return solusi;    
    }

    public static String[] SPLGaussJordan(Matrix m){
        int varParam = 97,row = m.getRow(),col = m.getCol();
        
        double[] konstanta = new double[col-1];
        Arrays.fill(konstanta,-9999);

        int[][] jmlhParam = new int[col-1][col-1];
        for(int i=0;i<col-1;i++){
            Arrays.fill(jmlhParam[i],-9999);
        }
        String[] solusi = new String[col-1];
        Arrays.fill(solusi, "");

        OBE.GaussJordan(m);
        if(cekAllArrayIdx(m.mem[row-1], 0, 0, col-2) && m.mem[row-1][col-1]!=0) return solusi;

        for(int i=row-1;i>=0;i--){
            int colLead = m.findColLead1(i);
            if (colLead==-1) continue;
            konstanta[colLead] = m.mem[i][col-1];
            Arrays.fill(jmlhParam[colLead], 0);

            for(int j=colLead+1;j<col-1;j++){
                if(m.mem[i][j]!=0 ){
                    if(cekAllArray(jmlhParam[j],-9999) && konstanta[j]==-9999){
                        Arrays.fill(jmlhParam[j], 0);
                        jmlhParam[j][j] = 1;
                        konstanta[j] = 0;

                        solusi[j] +=(char) varParam;
                        varParam += 1;
                    }
                    jmlhParam[colLead][j] -= m.mem[i][j];
                }
            }
            if (!cekAllArray(jmlhParam[colLead], -9999)){
                for(int j=0;j<col-1;j++){
                    if(jmlhParam[colLead][j]!=0){
                        solusi[colLead] += jmlhParam[colLead][j]+solusi[j]+"+";
                    }
                }
            }
            solusi[colLead] += konstanta[colLead];
        }
        return solusi;
    }
}