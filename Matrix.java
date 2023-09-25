import java.util.*;

public class Matrix {
    // private static final int ROW_CAP = 100;
    // private static final int COL_CAP = 100;
    // Final means unchangeable

    public double[][] matrx;
    public int rowEff;
    public int colEff;

    public Matrix(int nRows, int nCols) {
        this.rowEff = nRows;
        this.colEff = nCols;
        this.matrx = new double[nRows][nCols];
        for (int i = 0; i < rowEff; i++) {
            for (int j = 0; j < colEff; j++) {
                this.matrx[i][j] = 0;
            }
        }
    }

    // 60% not sure if this will work
    public void copyMatrix(Matrix mIn) {
        this.rowEff = mIn.rowEff;
        this.colEff = mIn.colEff;
        this.matrx = new double[mIn.rowEff][mIn.colEff];
        for (int i = 0; i < mIn.rowEff; i++) {
            for (int j = 0; j < mIn.colEff; j++) {
                this.matrx[i][j] = mIn.matrx[i][j];
            }
        }
    }

    public static Matrix addMatrix(Matrix m1, Matrix m2) {
        Matrix after = new Matrix(m1.rowEff, m1.colEff);
        for (int i = 0; i < m1.rowEff; i++) {
            for (int j = 0; j < m1.colEff; j++) {
                after.matrx[i][j] = m1.matrx[i][j] + m2.matrx[i][j];
            }
        }
        return after;
    }

    public static Matrix subtractMatrix(Matrix m1, Matrix m2) {
        Matrix after = new Matrix(m1.rowEff, m1.colEff);
        for (int i = 0; i < m1.rowEff; i++) {
            for (int j = 0; j < m1.colEff; j++) {
                after.matrx[i][j] = m1.matrx[i][j] - m2.matrx[i][j];
            }
        }
        return after;
    }

    public static Matrix multiplyMatrix(Matrix m1, Matrix m2) {
        Matrix after = new Matrix(m1.rowEff, m2.colEff);
        for (int i = 0; i < after.rowEff; i++) {
            for (int j = 0; j < after.colEff; j++) {
                for (int k = 0; k < m1.colEff; k++) {
                    after.matrx[i][j] += m1.matrx[i][k] * m2.matrx[k][j];
                }
            }
        }
        return after;
    }

    public static Matrix multiplyByConst(Matrix m, int x) {
        Matrix after = new Matrix(m.rowEff, m.colEff);
        for (int i = 0; i < after.rowEff; i++) {
            for (int j = 0; j < after.colEff; j++) {
                after.matrx[i][j] *= x;
            }
        }
        return after;
    }

    public boolean isSquare() {
        return (this.rowEff == this.colEff);
    }

    public boolean isSymmetric() {
        // Use one of the determinant checking method later
    }

    public boolean isIdentity() {
        // MALASSSSSSSSSSSSSSSS
    }

    public Matrix transpose() {
        Matrix after = new Matrix(this.colEff, this.rowEff);
        for (int i = 0; i < this.colEff; i++) {
            for (int j = 0; j < this.rowEff; j++) {
                after.matrx[i][j] = this.matrx[j][i];
            }
        }
        return after;
    } 
}
