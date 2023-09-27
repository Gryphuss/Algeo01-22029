import java.util.*;

public class SPL {
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
