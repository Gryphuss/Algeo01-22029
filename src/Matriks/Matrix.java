package Matriks;
import java.util.*;

public class Matrix {

    public double[][] mem;
    private int rowEff;
    private int colEff; 

    public Matrix(int row,int col){
        this.rowEff = row;
        this.colEff = col;
        this.mem = new double[row][col];
    }

    public void setRow(int n){
        this.rowEff = n;
    }

    public void setCol(int n){
        this.colEff = n;
    }

    public void readMatrix(){
        Scanner obj = new Scanner(System.in);
        for(int i=0;i<this.rowEff;i++){
            for(int j=0;j<this.colEff;j++){
                double elmt = obj.nextDouble();
                this.mem[i][j] = elmt;
            }
        }
    }

    public void matrixIdentity(){ //I.S Matrix pasti persegi
        for(int i=0;i<this.rowEff;i++){
            for(int j=0;j<this.colEff;j++){
                if(i == j){
                    this.mem[i][j] = 1;
                }else{
                    this.mem[i][j] = 0;
                }
            }
        }
    }

    public int getRow(){
        return this.rowEff;
    }

    public int getCol(){
        return this.colEff;
    }

    public void bagiRow(int row,double div){
        for(int j=0;j<this.getCol();j++){
            this.mem[row][j] /= div;
        }
    }

    public void swapRow(int row1,int row2){
        double temp;
        for(int i=0;i<this.getCol();i++){
            temp = this.mem[row1][i];
            this.mem[row1][i] = this.mem[row2][i];
            this.mem[row2][i] = temp;
        }
    }

    public void tambahRow(int rowDest,int rowX,double rasio){
        for(int i=0;i<this.colEff;i++){
            this.mem[rowDest][i] += this.mem[rowX][i]*rasio;
        }
    }

    public int findColLead1(int row){
        for(int i=0;i<this.getCol();i++){
            if(this.mem[row][i]==1){
                return i;
            }
        }
        return -1;
    }

    public Matrix kali(Matrix a, Matrix b){
        Matrix mhasil = new Matrix(a.getRow(),b.getCol());
        for(int i = 0; i<mhasil.getRow(); i++){
            for(int j = 0; j<mhasil.getCol(); j++){
                mhasil.mem[i][j] = 0;
                for(int k = 0; k<a.getCol(); k++){
                    mhasil.mem[i][j] += a.mem[i][k] * b.mem[k][j];
                }
            }
        }
        return mhasil;
    }
    public void displayMatrix(){
        for(int i=0;i<this.getRow();i++){
            for(int j=0;j<this.getCol();j++){
                System.out.print(this.mem[i][j]+" ");
            }
            System.out.println();
        }
    }

    public boolean isSingular() {
        if (OBE.determinanKofaktor(this) == 0) {
            return true;
        }
        return false;
    }

    public boolean isSquare() {
        return (this.getCol() == this.getRow());
    }
}
