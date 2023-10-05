package Aplikasi;

import java.util.Scanner;

import Matriks.Matrix;
import Matriks.OBE;
import IO.*;

public class Interpolasi {
    public static Scanner input = new Scanner(System.in);

    public static Matrix inToMatrix(int n) {
        Matrix fin = new Matrix(n, n + 1);
        Matrix firstIn = new Matrix(n, 2);
        firstIn.readMatrix();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n + 1; j++) {
                if (j == n) {
                    fin.mem[i][j] = firstIn.mem[i][1];
                } else {
                    fin.mem[i][j] = Math.pow(firstIn.mem[i][0], j);
                }
            }
        }

        return fin;
    }

    public static Matrix inToMatrixv2(Matrix m) {
        Matrix fin = new Matrix(m.getRow(), m.getRow() + 1);
        // Matrix firstIn = new Matrix(m., 2);
        // firstIn.readMatrix();

        for (int i = 0; i < m.getRow(); i++) {
            for (int j = 0; j < m.getRow() + 1; j++) {
                if (j == m.getRow()) {
                    fin.mem[i][j] = m.mem[i][1];
                } else {
                    fin.mem[i][j] = Math.pow(m.mem[i][0], j);
                }
            }
        }

        return fin;
    }

    public static Matrix MatrixInterpolasi(Matrix m) {
        OBE.GaussJordan(m);
        return m;
    }

    public static String InterpolasiPrint(Matrix m) {
        String out = "f(x) =";
        for (int i = 0; i <= m.getRow() - 1; i++) {
            if (m.mem[i][m.getCol() - 1] != 0) {
                String nega = String.format("%.10f", -m.mem[i][m.getCol() - 1]);
                String posi = String.format("%.10f", m.mem[i][m.getCol() - 1]);
                if (i == 0) {
                    out += (m.mem[i][m.getCol() - 1] <= 0 ? " -" + nega : " " + posi);
                } else if (i == 1) {
                    out += (m.mem[i][m.getCol() - 1] <= 0 ? " - " + nega : " + " + posi) + "x";
                } else if (i == m.getRow() - 1) {
                    out += (m.mem[i][m.getCol() - 1] <= 0 ? " - " + nega : " + " + posi) + "x^" + i;
                } else {
                    out += (m.mem[i][m.getCol() - 1] <= 0 ? " - " + nega : " + " + posi) + "x^" + i;
                }
            }
        }
        return out;
    }

    public static String HasilYPrint(Matrix m, double d) {
        double hasil = 0;
        String out = "f(" + d + ") = ";
        for (int i = 0; i < m.getRow(); i++) {
            hasil += m.mem[i][m.getCol() - 1] * Math.pow(d, i);
        }
        out += String.format("%.4f", hasil);
        return out;
    }

    public static void mainMenu() {
        System.out.println("INTERPOLASI POLINOM");
        System.out.println("Options: ");
        System.out.println("1. Keyboard Input");
        System.out.println("2. File Input");
        System.out.println("Insert input method (1/2): ");
        int selecmed = 0;
        boolean nonvalid = true;

        Matrix matr = new Matrix(100, 100);
        Matrix matrPoints = new Matrix(100, 100);
        Matrix rightside = new Matrix(1, 1);
        // double rightside = 0;

        // System.out.println();
        while (nonvalid) {
            selecmed = input.nextInt();
            switch (selecmed) {
                case 1:
                    System.out.println("Masukkan jumlah pasangan titik: ");
                    int n = 0;
                    n = input.nextInt();
                    System.out.println("Masukkan titik-titik yang diinginkan: ");
                    // Matrix matr = new Matrix(n, n + 1);
                    matr = inToMatrix(n);
                    // matr.displayMatrix();
                    System.out.println("Masukkan nilai x yang ingin dicari hasilnya: ");
                    rightside.mem[0][0] = input.nextDouble();
                    nonvalid = false;
                    break;
                case 2:
                    // System.out.println("Masukkan nama file yang diinginkan: ");
                    IO.IOput.readFileToMatrixInterpolasi(matrPoints, rightside);
                    // System.out.println(rightside.mem[0][0]);

                    // matrPoints.displayMatrix();
                    matr = inToMatrixv2(matrPoints);
                    // matr.displayMatrix();
                    nonvalid = false;
                    break;
                default:
                    System.out.println("Input tidak valid, masukkan hanya 1 atau 2!");
            }
        }
        if (!nonvalid) {
            Matrix finale = MatrixInterpolasi(matr);
            // finale.displayMatrix();
            String hasilpoli = InterpolasiPrint(finale);
            String hasily = HasilYPrint(finale, rightside.mem[0][0]);

            if (IOput.outputMode() == 2) {

                System.out.println("Polinom yang melalui titik-titik tersebut adalah: ");
                System.out.println(hasilpoli);

                // String finalres = hasilpoli;

                System.out.println("Nilai y dari nilai x yang diinginkan adalah: ");
                System.out.println(hasily);
                System.out.print("Returning to Main Menu.. ");
                // finalres += "\n" + hasily;
            } else{
                String out2 = ("Polinom yang melalui titik-titik tersebut adalah: " + hasilpoli
                        + " dan nilai y dari nilai x yang diinginkan adalah: " + hasily);

                IOput.writeStringToFile(out2);
                System.out.print("Returning to Main Menu.. ");
            }
        }

    }
}
