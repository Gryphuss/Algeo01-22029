package Matriks;
import java.text.DecimalFormat;
import java.util.Scanner;
import IO.IOput;

public class Invers {
    public static void menuInvers() {
        // Fungsi: menghasilkan matrix invers dari m
        Scanner obj = new Scanner(System.in);
        System.out.println("Metode Invers");
        System.out.println("1. Metode Gauss-Jordan");
        System.out.println("2. Metode Adjoin");
        System.out.println("Pilih metode invers:");
        int met = obj.nextInt();
        System.out.println("Masukan Ukuran Matrix Persegi:");
        int n = obj.nextInt();
        Matrix m = new Matrix(n, n);
        if (IOput.inputMode() == 1) {
            IOput.readFileToMatrix(m);
        } else {
            IOput.readKeyboardToMatrix(m);
        }

        if(OBE.determinanKofaktor(m)==0){
            String pesanError = "Determinan Matriks adalah 0\nMatriks tidak memiliki invers";
            if (IOput.outputMode() == 1) {
                IOput.writeStringToFile(pesanError);
            }else{
                System.out.println(pesanError);
            }
        }
        else{
            if(met == 1){
                // mensetup matrix
                m = invers(m);
            }else{
                m = inversAdj(m);
            }
            
            if (IOput.outputMode() == 1) {
                IOput.writeMatrixToFile(m);
                System.out.print("Returning to Main Menu.. ");
            }else{
                // menampilkan hasil invers
                System.out.println("Matriks balikan dari matrix awal:\n");
                for (int i = 0; i < m.getRow(); i++) {
                    for (int j = 0; j < m.getCol(); j++) {
                        System.out.print(m.mem[i][j]);
                        System.out.print(" ");
                    }
                    System.out.print("\n");

                }
            }
        }
        System.out.println("Returning to Main Menu.. ");
    }

    public static Matrix invers(Matrix m) {
    // Fungsi: menghasilkan matrix invers dari m
        //Matrix temp = new Matrix(m.getRow(),m.getCol());
        // mensetup matrix
        m = setUpInvers(m);
        // menginvers matrix
        OBE.GaussJordan(m);
        // mengambil matrix invers
        m = takeInvers(m);

        /*if(IOput.inputMode() == 1){
            IOput.writeMatrixToFile(m);
        } else {
            // menampilkan hasil invers
            System.out.println("Matriks balikan dari matrix awal:\n");
            for (int i = 0; i < m.getRow(); i++) {
                for (int j = 0; j < m.getCol(); j++) {
                    System.out.print(m.mem[i][j]);
                    System.out.print(" ");
                }
                System.out.print("\n");

            }
        }*/
        return m;
    }

    public static Matrix setUpInvers(Matrix m) {
    // Fungsi: mensetup matrix invers dengan cara
    // menambahkan matrix identitas di sebelah matrix m
        // proses menambahkan matrix identitas
        Matrix mI = new Matrix(m.getRow(), 2 * m.getCol());
                for (int i = 0; i < m.getRow(); i++) {
            for (int j = 0; j < m.getCol(); j++) {
                    mI.mem[i][j] = m.mem[i][j];
                if (i == j) {
                    mI.mem[i][j + m.getCol()] = 1;
                } else {
                    mI.mem[i][j + m.getCol()] = 0;
                }
            }
        }
        return mI;
    }

    public static Matrix takeInvers(Matrix m) {
    // Fungsi: mengambil matrix invers dari matrix setup invers
    // yaitu mengambil matrix yang awalnya berisi identitas
        Matrix mI = new Matrix(m.getRow(), m.getRow());
        for (int i = 0; i < m.getRow(); i++) {
            for (int j = 0; j < m.getRow(); j++) {
                mI.mem[i][j] = m.mem[i][j + m.getRow()];
            }
        }
        return mI;
    }

    public static Matrix matrixKofak(Matrix m){
        Matrix kofak = new Matrix(m.getRow(), m.getCol());
        for(int i=0;i<m.getRow();i++){
            for(int j=0;j<m.getCol();j++){
                kofak.mem[i][j] = OBE.kofaktor(m, i, j);
            }
        }
        return kofak;
    }

    public static Matrix inversAdj(Matrix m){
        Matrix kofak = matrixKofak(m);
        kofak.transpose();
        double detM = OBE.determinanKofaktor(m);
        System.out.println(detM);
        for(int i=0;i<kofak.getRow();i++){
            kofak.bagiRow(i, detM);
        }
        return kofak;
    }
}
