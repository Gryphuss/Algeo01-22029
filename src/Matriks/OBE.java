package Matriks;

public class OBE {
    public static void Gauss(Matrix m) {

        int rowLead = 0;
        for (int i = 0; i < m.getCol(); i++) {
            boolean col0 = true;
            for (int j = rowLead; j < m.getRow(); j++) {
                if (m.mem[j][i] != 0) {
                    m.swapRow(rowLead, j);
                    col0 = false;
                    break;
                }
            }
            if (col0)
                continue;
            m.bagiRow(rowLead, m.mem[rowLead][i]);
            for (int j = rowLead + 1; j < m.getRow(); j++) {
                m.tambahRow(j, rowLead, -m.mem[j][i]);
            }
            rowLead++;
        }
    }

    public static void GaussJordan(Matrix m) {
        Gauss(m);
        for (int i = m.getRow() - 1; i >= 0; i--) {
            int colLead = m.findColLead1(i);
            if (colLead == -1)
                continue;
            for (int j = i - 1; j >= 0; j--) {
                m.tambahRow(j, i, -m.mem[j][colLead]);
            }
        }
    }

    public static double kofaktor(Matrix m, int p, int q) {
        Matrix sub = new Matrix(m.getCol() - 1, m.getRow() - 1);
        double det;

        int i = 0;
        int j = 0;

        for (int r = 0; r < m.getRow(); r++) {
            for (int c = 0; c < m.getCol(); c++) {
                if (r != p && c != q) {
                    sub.mem[i][j++] = m.mem[r][c];
                    if (j == m.getCol() - 1) {
                        j = 0;
                        i++;
                    }
                }
            }
        }

        if ((p + q) % 2 == 0) {
            det = determinanKofaktor(sub);
        } else {
            det = -determinanKofaktor(sub);
        }

        return det;
    }

    public static double determinanKofaktor(Matrix m) {
        double det = 0;

        if (m.getRow() == 1 && m.getCol() == 1) {
            det = m.mem[0][0];
        }

        else {
            int i = 0;
            for (int j = 0; j < m.getCol(); j++) {
                det += (m.mem[i][j] * kofaktor(m, i, j));

            }
        }
        return det;
    }

    public static void mainmenu() {
        System.out.println("DETERMINAN");
        System.out.println("Options: ");
        System.out.println("1. Metode Reduksi Baris");
        System.out.println("2. Metode Ekspansi Kofaktor");
        System.out.println("Insert input method (1/2): ");
        int selecmed = 0;
        boolean nonvalid = true;

        Matrix matrig = new Matrix(100, 100);

        while (nonvalid) {
            selecmed = input.nextInt();
            switch (selecmed) {
                case 1:
                    System.out.println("METODE REDUKSI BARIS");
                    System.out.println("Options: ");
                    System.out.println("3. Keyboard");
                    System.out.println("4. File");
                    int selecmed2 = 0;
                    boolean nonvalid2 = true;

                    while (nonvalid2) {
                        selecmed2 = input.nextInt();
                        switch (selecmed2) {
                            case 3:
                                System.out.println("Masukkan jumlah baris dan kolom: ");
                                int tempbaris = input.nextInt();
                                matrig.setRow(tempbaris);
                                matrig.setCol(tempbaris);
                                System.out.println("Masukkan elemen matriks: ");
                                matrig.readMatrix();
                                nonvalid2 = false;
                                break;
                            case 4:
                                System.out.print("Masukkan nama file yang diinginkan: ");
                                IO.IOput.readFileToMatrix(matrig);
                                while (matrig.getRow() != matrig.getCol()) {
                                    System.out.println(
                                            "Tidak dapat dioperasikan dengan Metode Ekspansi Kofaktor, jumlah baris dan kolom harus sama!");
                                    System.out.print("Masukkan nama file yang diinginkan: ");
                                    IO.IOput.readFileToMatrix(matrig);
                                }
                                nonvalid2 = false;
                                break;
                            default:
                                System.out.println("Input tidak valid, masukkan hanya 3 atau 4!");

                        }
                    }

                    if (!nonvalid2) {
                        System.out.println(
                                "Determinan dari matrix tersebut adalah: " + determinanKofaktor(matrig));
                        // ini determinannya pake kofaktor harusnya sama aja sih sama OBE
                        nonvalid = false;
                    }
                    break;

                case 2:
                    System.out.println("METODE EKSPANSI KOFAKTOR");
                    System.out.println("Options: ");
                    System.out.println("3. Keyboard");
                    System.out.println("4. File");
                    int selecmed3 = 0;
                    boolean nonvalid3 = true;

                    while (nonvalid3) {
                        selecmed3 = input.nextInt();
                        switch (selecmed3) {
                            case 3:
                                System.out.println("Masukkan jumlah baris dan kolom: ");
                                int tempbaris = input.nextInt();
                                matrig.setRow(tempbaris);
                                matrig.setCol(tempbaris);
                                System.out.println("Masukkan elemen matriks: ");
                                matrig.readMatrix();
                                nonvalid3 = false;
                                break;
                            case 4:
                                System.out.print("Masukkan nama file yang diinginkan: ");
                                IO.IOput.readFileToMatrix(matrig);
                                while (matrig.getRow() != matrig.getCol()) {
                                    System.out.println(
                                            "Tidak dapat dioperasikan dengan Metode Ekspansi Kofaktor, jumlah baris dan kolom harus sama!");
                                    System.out.print("Masukkan nama file yang diinginkan: ");
                                    IO.IOput.readFileToMatrix(matrig);
                                }
                                nonvalid3 = false;
                                break;
                            default:
                                System.out.println("Input tidak valid, masukkan hanya 3 atau 4!");

                        }
                    }

                    if (!nonvalid3) {
                        System.out.println(
                                "Determinan dari matrix tersebut adalah: " + determinanKofaktor(matrig));
                        nonvalid = false;
                    }
                    break;
            }
        }
        if (!nonvalid) {
            System.out.print("Returning to Main Menu.. ");
        }
    }
}
