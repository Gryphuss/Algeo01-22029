import java.util.Scanner;
public class Matrix{

    private float[][] m;
    private int nRow;
    private int nCol;
    //private int maxRow = 1000;
    //private int maxCol = 1000;
    
    //konstruktor
    public Matrix(){
        m = new float[1000][1000];
        for(int i=0; i<1000; i++){
            for(int j=0; j<1000; j++){
                m[i][j]=-9999;
            }
        }
    }
    //getter
    public float ELMT(Matrix m, int i, int j){
        return this.m[i][j];
    }
    public int getRow(){
        return nRow;
    }
    public int getCol(){
        return nCol;
    }

    //setter
    public void setRow(int row){
        this.nRow = row;
    }
    public void setCol(int col){
        this.nCol = col;
    }
    public void setMtx(int x, int y){
        this.nRow = x;
        this.nCol = y;
        for( int i = 0; i<nRow ; i++){
            for( int j = 0; j<nCol; j++){
                Scanner masukan = new Scanner (System.in);
                float f = masukan.nextFloat();
                this.m[i][j] = f;
            }
        }
    }

    //display
    public void printMtx(){
        for(int i = 0; i < nRow; i++){
            for(int j = 0; j < nCol-1; j++){
                System.out.print(this.m[i][j]);
                System.out.print(" ");
            }
            System.out.println(this.m[i][nCol-1]);
        }
    }
}