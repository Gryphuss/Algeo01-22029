package Matriks;
import IO.IOput;
import java.util.Scanner;

public class Invers {

    public void invers(Matrix m) {
    // Fungsi: menghasilkan matrix invers dari m
        Matrix temp = new Matrix(m.getRow(),m.getCol());
        // mensetup matrix
        temp = setUpInvers(m);
        // menginvers matrix
        temp = GaussJordan(temp);
        // mengambil matrix invers
        temp = takeInversInvers(temp);

        if(IOput.inputMode() == 1){
            writeMatrixToFile(temp);
        } else {
            // menampilkan hasil invers
            System.out.println("Matriks balikan dari matrix awal:\n");
            for (int i = 0; i < temp.getRow(); i++) {
                for (int j = 0; j < temp.getCol(); j++) {
                    System.out.print(m[i][j]);
                    System.out.print(" ");
                }
                System.out.print("\n");

            }
        }
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
}
