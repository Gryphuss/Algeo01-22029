package Matriks;

import java.util.*;

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
}
