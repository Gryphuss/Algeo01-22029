package Matriks;
import IO.IOput;
import java.util.Scanner;

public class Invers {

    public Matriks invers(Matrix m) {
    // Fungsi: menghasilkan matrix invers dari m
        // mensetup matrix
        m = m.setUpInvers(m);
        // menginvers matrix
        m = m.GaussJordan(m)
        // mengambil matrix invers
        m = m.takeInversInvers(m);

        if(IOput.inputMode() == 1){
            writeMatrix(m);
        } else {
            // menampilkan hasil invers
            System.out.println("Matriks balikan dari matrix awal:\n");
            for (int i = 0; i < m.getRow(); i++) {
                for (int j = 0; j < m.getCol(); j++) {
                    System.out.print(m[i][j]);
                    System.out.print(" ");
                }
                System.out.print("\n");

            }
        }
        // output
        return m;
    }

    public Matrix setUpInvers(Matrix m) {
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

    public Matrix takeInvers(Matrix m) {
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
