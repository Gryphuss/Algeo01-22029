package Matriks;
import IO.IOput;

public class Invers {

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
}
