package Aplikasi;
import Matriks.*;
import IO.*;
import java.util.*;

public class Bicubic{
    // Fungsi-fungsi untuk membuatt matrix X
    public static int f(int x, int y, int i, int j){
        return ((int)Math.pow(x,i) * (int)Math.pow(y,j));
    }
    public static int fx(int x, int y, int i, int j){
        return (i * (int)Math.pow(x,i-1) * (int)Math.pow(y,j));
    }
    public static int fy(int x, int y, int i, int j){
        return (j * (int)Math.pow(x,i) * (int)Math.pow(y,j-1));
    }
    public static int fxy(int x, int y, int i, int j){
        return (i * j * (int)Math.pow(x,i-1) * (int)Math.pow(y,j-1));
    }

    public static Matrix setBicubic(Matrix m){
    // Fungsi: mempersiapkan matrix y
        // mengubah bentuk matrix dari 4x4 menjadi 16x1
        Matrix mBaru = new Matrix(16,1);
        int idx;
        idx = 0;
        for(int i = 0; i < m.getRow(); i++){
            for(int j = 0; j < m.getCol(); j++){
                mBaru.mem[idx][0] = m.mem[i][j];
                idx ++;
            }
        }
        return mBaru;
    }

    public static void menubicubic(){
    // Fungsi: menampilkan nilai f(x,y) hasil bicubic
        Matrix m = new Matrix(5,4);
        Matrix xy = new Matrix(1,2);
        double x,y;
        // menentukan jenis input
        if(IOput.inputMode() == 1){
            IOput.readFileToMatrixBicubic(m,xy);

        }else{
            IOput.readKeyboardToMatrix(m);
            xy.mem[0][0] = m.mem[4][0];
            xy.mem[0][1] = m.mem[4][1];
            m.setRow(4);

        }
        x = xy.mem[0][0];
        y = xy.mem[0][1];
        // proses menghitung f(x,y)
        m = setBicubic(m);
        Matrix mx = new Matrix(0,0);

        mx = buatX();
        mx = Invers.invers(mx);
        double f;
        f = bicubicInterpolation(x,y,m,mx); 
        // output

        if(IOput.outputMode()==1){
            IOput.writeStringToFile("f(x,y) = ΣiΣj(a_ij * x^i * y^) = " + Double.toString(f));
        }else{
            System.out.println("f(x,y) = ΣiΣj(a_ij * x^i * y^j) = " + Double.toString(f));
        }
        System.out.println("Kembali ke Menu Utama.....\n");
    
    }



    public static Matrix buatX(){
    // Fungsi: membuat matrix X
        // inisialisasi matrix berukuran 16x16
        Matrix mx = new Matrix(16,16);
        //proses mengisi matrix
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
    // Fungsi: mencari nilai f(x,y) dengan bicubic
        // inisialisasi matrix mhasil
        Matrix mhasil = new Matrix(0,0);
        // proses mengisi matrix mhasil
        mhasil = mhasil.kali(mX,mIn);
        
        // menghitung f(x,y)
        double hasil = 0;
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                hasil += Math.pow(x,i) * Math.pow(y,j) * mhasil.mem[i+4*j][0];
            }
        }
        return hasil;
    }

}