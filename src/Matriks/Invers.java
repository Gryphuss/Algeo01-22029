package Matriks;
import java.util.Scanner;


public class Invers{

    public Matriks invers(Matrix m){
        m = m.setUpInvers(m);
        m = m.takeInversInvers(m);

        if(inputMode == 1){
            writeMatrix(m);
        }else{
            System.out.println("Matriks balikan dari matrix awal:\n");
            for(int i=0; i<m.getRow(); i++){
                for(int j=0; j<m.getCol(); j++){
                    System.out.print(m[i][j]);
                    System.out.print(" ");
                }
                System.out.print("\n");

            }
        }
    }
    
    public Matrix setUpInvers(Matrix m){
        Matrix mI = new Matrix(m.getRow(),2*m.getCol());
        for(int i=0; i<m.getRow(); i++){
            for(int j=0; j<m.getCol(); j++){
                mI.mem[i][j] = m.mem[i][j];
                if(i == j){
                    mI.mem[i][j+m.getCol()] = 1;
                }else{
                    mI.mem[i][j+m.getCol()] = 0;
                }
            }
        }
        return mI;
    }

    public Matrix takeInvers(Matrix m){
        Matrix mI = new Matrix(m.getRow(),m.getRow());
        for(int i=0; i<m.getRow(); i++){
            for(int j=0; j<m.getRow(); j++){
                mI.mem[i][j] = m.mem[i][j+m.getRow()];
            }
        }
        return mI;
    }
}
