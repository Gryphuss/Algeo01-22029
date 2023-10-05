package Aplikasi;
import java.text.DecimalFormat;
import java.util.*;
import IO.IOput;
import Matriks.*;

public class SPL {
    public static final DecimalFormat df = new DecimalFormat("0.00");
    public static boolean cekAllArray(String[] arr, String val){
        for(int i=0;i<arr.length;i++){
            if (arr[i]!=val) return false;
        }
        return true;
    }

    public static boolean cekRangeArray(double[] arr, int val,int awal,int akhir){
        for(int i=awal;i<=akhir;i++){
            if (arr[i]!=val) return false;
        }
        return true;
    }

    public static boolean cekAdaSolusi(Matrix m){
        for(int i=m.getRow()-1;i>=0;i--){
            if(cekRangeArray(m.mem[i], 0, 0, m.getCol()-2)&&m.mem[i][m.getCol()-1]!=0){
                return false;
            }
            if(m.findColLead1(i)<m.getCol()-1 && m.findColLead1(i)!=-1) break;
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
        if(!cekAdaSolusi(m)) return solusi;

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
                        solusi[colLead] += String.valueOf(df.format(jmlhParam[colLead][j])); 
                    }
                    solusi[colLead] += String.valueOf(solusi[j]);
                }
            }
            if(konstanta[colLead]>0 && solusi[colLead]!="") solusi[colLead] += "+";
            if(konstanta[colLead]!=0 || solusi[colLead]==""){
                solusi[colLead] += String.valueOf(df.format(konstanta[colLead]));
            }
        }
        for(int i=0;i<col-1;i++){
            if(solusi[i]==""){
                solusi[i] = String.valueOf(varParam);
                varParam+=1;
            }
        }
        return solusi;    
    }    

    public static String[] SPLGaussJordan(Matrix m){
        int row = m.getRow(),col = m.getCol();

        String[] solusi = new String[col-1];
        Arrays.fill(solusi, "");

        OBE.GaussJordan(m);
        if(!cekAdaSolusi(m)) return solusi;
        
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
                        if(m.mem[i][j]!=1) solusi[colLead] += String.valueOf(df.format(m.mem[i][j]));
                        solusi[colLead] += solusi[j];
                    }else{
                        double val = m.mem[i][j]*(-1);
                        if(solusi[colLead]!="") solusi[colLead] += "+";
                        if(val!=1) solusi[colLead] += String.valueOf(df.format(val)); 
                        solusi[colLead] += solusi[j];
                    }
                }
            }
            if(konstanta>0 && solusi[colLead]!="") solusi[colLead] += "+";
            if(konstanta!=0 || solusi[colLead]==""){
                solusi[colLead] += String.valueOf(df.format(konstanta));
            }
        }
        for(int i=0;i<col-1;i++){
            if(solusi[i]==""){
                solusi[i] = String.valueOf(varParam);
                varParam+=1;
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

    public static String[] SPLInvers(Matrix M){

        if(M.getCol() != M.getRow()+1){
            String[] str = new String[1];
            str[0] = "";
            System.out.println("Matriks bukan persegi.");
            System.out.println("Matriks tidak memiliki balikan sehingga solusi SPL tidak bisa diselesaikan.");
            return str;
        }else{
            Matrix mKoef = new Matrix(M.getRow(), M.getRow());
            for(int i = 0; i < M.getRow(); i++){
                for(int j=0; j < M.getRow(); j++){
                    mKoef.mem[i][j] = M.mem[i][j];
                }
            }
            if(OBE.determinanKofaktor(mKoef) == 0){
                System.out.println("Determinan matriks nol");
                System.out.println("Matriks tidak memiliki balikan sehingga solusi SPL tidak bisa diselesaikan");
                String[] str = new String[1];
                str[0] = "";
                return str;
            }else{
                Matrix koef = new Matrix(M.getRow(),M.getRow());
                Matrix kons = new Matrix(M.getRow(),1);
                for(int i = 0; i < M.getRow(); i++){
                    for(int j = 0; j < M.getCol()-1 ; j++){
                        koef.mem[i][j] = M.mem[i][j];
                    }
                }
        
                for(int k = 0; k < M.getRow(); k++){
                    kons.mem[k][0] = M.mem[k][M.getCol()-1];
                }
        
                Matrix hasil = new Matrix(M.getRow(),1);
                hasil = hasil.kali(Invers.invers(koef),kons);
                
        
                String[] str = new String[hasil.getRow()];
                for(int a = 0; a < hasil.getRow(); a++){
                    str[a] = df.format(hasil.mem[a][0]);
                }
                return str;
            }
        }
    }
    
    public static void menuSPL(){
        Scanner obj = new Scanner(System.in);
        System.out.println("Tentukan metode penyelesaian SPL!");
        System.out.println("1. Metode Eliminasi Gauss\n2. Metode Eliminasi Gauss-Jordan\n3. Metode Matriks Balikan\n4. Kaidah Cramer");
        System.out.print("-> ");
        int menu = obj.nextInt();
        while(menu!=1&&menu!=2&&menu!=3&&menu!=4){
            System.out.print("Pilihan hanya antara 1,2,3 atau 4!\n-> ");
            menu = obj.nextInt();
        }
        int roww,byk_x;
        System.out.print("Masukkan banyak persamaan: ");
        roww = obj.nextInt();
        System.out.print("Masukkan jumlah variabel peubah: ");
        byk_x = obj.nextInt();

        Matrix mat = new Matrix(roww, byk_x+1);
        if(IOput.inputMode()==1){
            IOput.readFileToMatrix(mat);
        }else{
            for(int i=0;i<roww;i++){
                for(int j=0;j<byk_x;j++){
                    System.out.print("Masukkan koefisien a("+(i+1)+","+(j+1)+"): ");
                    mat.mem[i][j] = obj.nextDouble();               
                }
            }
            for(int i=0;i<roww;i++){
                System.out.print("Masukkan nilai b("+(i+1)+"): ");
                mat.mem[i][mat.getCol()-1] = obj.nextDouble();
            }
        }
        String[] solusi = new String[byk_x];
        String solusiString="";
        if(menu==1){
            solusi = SPLGauss(mat);
        }else if(menu==2){
            solusi = SPLGaussJordan(mat);
        }else if(menu==3){
            solusi = SPLInvers(mat);
        }else{
            solusiString = kaidahCrammer(mat);
        }

        if(menu!=4){
            if(!cekAllArray(solusi, "")){
                solusiString="Solusi dari SPL tersebut adalah: \n";
                for(int i=0;i<byk_x;i++){
                    solusiString+="x"+String.valueOf(i+1)+" = "+solusi[i]+"\n";
                }
            }else{
                solusiString="SPl tersebut tidak memiliki solusi.\n";
            }
        }

        if(IOput.outputMode()==1){
            IOput.writeStringToFile(solusiString);
        }else{
            System.out.print(solusiString);
        }
        System.out.println("Kembali ke Menu Utama.......\n");
    }
}
