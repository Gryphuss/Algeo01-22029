package Aplikasi;
import Matriks.*;
import IOput.*;
import java.util.Scanner;

public class Bicubic{

    public int f(int x, int y, int i, int j){
        return ((int)Math.pow(x,i) * (int)Math.pow(y,j));
    }
    public int fx(int x, int y, int i, int j){
        return (i * (int)Math.pow(x,i-1) * (int)Math.pow(y,j));
    }
    public int fy(int x, int y, int i, int j){
        return (j * (int)Math.pow(x,i) * (int)Math.pow(y,j-1));
    }
    public int fxy(int x, int y, int i, int j){
        return (i * j * (int)Math.pow(x,i-1) * (int)Math.pow(y,j-1));
    }

    public static Matrix setBicubic(Matrix m){
        Matrix mBaru = new Matrix(16,1);
        int idx;
        idx = 0;
        for(int i = 0; i < m.getRow(); i++){
            for(int j = 0; j < m.getCol(); j++){
                mBaru.mem[idx] = m.mem[i][j];
                idx ++;
            }
        }
    }

    public static void bicubic(Matrix m){
        double x,y;
        if(inputMode() == 1){
            readFileToMatrixBicubic(m);
        }else{
            readKeyboardToMatrix(m);
            Scanner obj = new Scanner();
            x = obj.nextDouble();
            y = obj.nextDouble();
        }
        m = setBicubic(m);
        Matrix mx = new Matrix(0,0);
        mx = buatX;
        double f;
        f = bicubicInterpolation(x,y,mx,m); 
        System.out.println(f);

    }

    public static Matrix buatX(){
        Matrix mx = new Matrix(16,16);

        for(int i=0; i<16; i++){
            for(int j=0; j<16; j++){
                if(i<4){
                    if(i%4 == 0){
                        mx.mem[i][j] = f(0,0,j%4,j/4);
                    }else if(i%4 == 1){
                        mx.mem[i][j] = f(1,0,j%4,j/4);
                    }else if(i%4 == 2){
                        mx.mem[i][j] = f(0,1,j%4,j/4);
                    }else{
                        mx.mem[i][j] = f(1,1,j%4,j/4);
                    }
                }else if(i<8){
                    if(i%4 == 0){
                        mx.mem[i][j] = fx(0,0,j%4,j/4);
                    }else if(i%4 == 1){
                        mx.mem[i][j] = fx(1,0,j%4,j/4);
                    }else if(i%4 == 2){
                        mx.mem[i][j] = fx(0,1,j%4,j/4);
                    }else{
                        mx.mem[i][j] = fx(1,1,j%4,j/4);
                    }
                    
                }else if(i<12){
                    if(i%4 == 0){
                        mx.mem[i][j] = fy(0,0,j%4,j/4);
                    }else if(i%4 == 1){
                        mx.mem[i][j] = fy(1,0,j%4,j/4);
                    }else if(i%4 == 2){
                        mx.mem[i][j] = fy(0,1,j%4,j/4);
                    }else{
                        mx.mem[i][j] = fy(1,1,j%4,j/4);
                    }
                }else{
                    if(i%4 == 0){
                        mx.mem[i][j] = fxy(0,0,j%4,j/4);
                    }else if(i%4 == 1){
                        mx.mem[i][j] = fxy(1,0,j%4,j/4);
                    }else if(i%4 == 2){
                        mx.mem[i][j] = fxy(0,1,j%4,j/4);
                    }else{
                        mx.mem[i][j] = fxy(1,1,j%4,j/4);
                    }
                }
            }
        }
        return mx;
    }

    public static double bicubicInterpolation(double x, double y, Matrix mIn, Matrix mX){
        Matrix mhasil = new Matrix(1,16);
        mhasil = mhasil.kali(mX,mIn);
        double hasil = 0;
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                hasil += Math.pow(x,i) * Math.pow(y,j) * mhasil.mem[i+4*j][0];
            }
        }
        return hasil;
    }

}