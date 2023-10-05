package IO;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

import Matriks.*;

public class IOput{
    public static int inputMode(){
    // Fungsi: untuk menentukan output yang diinginkan
    // output melalui terminal atau melalui file .txt
        int n;
        Scanner obj = new Scanner(System.in);
        // Pertanyaan kepada user
        System.out.println("Tentukan metode input!");
        System.out.println("1. Pembacaan File");
        System.out.println("2. Pembacaan dari Keyboard");
        // memastikan input user benar
        while(true){
            System.out.print("->");
            n = obj.nextInt();
            if(n!=1 && n!=2){
                System.out.println("Pilihan hanya antara 1 atau 2!");
            }else{
                break;
            }
        }
        // hasil
        return n;
    }

    public static int outputMode(){
    // Fungsi: untuk menentukan output yang diinginkan
    // output melalui terminal atau melalui file .txt
        int n;
        Scanner obj = new Scanner(System.in);
        // Pertanyaan kepada user
        System.out.println("Tentukan metode output!");
        System.out.println("1. Penulisan ke File");
        System.out.println("2. Penulisan ke Terminal");
        // memastikan input user benar
        while(true){
            n = obj.nextInt();
            if(n!=1 && n!=2){
                System.out.println("Pilihan hanya antara 1 atau 2!");
            }else{
                break;
            }
        }
        // hasil
        return n;
    }

    public static void readFileToMatrix(Matrix mm){
    // Fungsi: membaca matrix dari file
        // membaca nama file
        System.out.println("Masukkan Nama File:");
        Scanner scanFile = new Scanner(System.in);
        String nameFile = scanFile.nextLine();
        try {
            // proses membaca file dan mengisi matrix 
            File file = new File("../test/"+nameFile);
            Scanner readFile = new Scanner(file);
            int i,j;
            i = 0;
            j = 0;
            mm.setRow(1);
            mm.setCol(1);
            while (readFile.hasNext()) {
                String cc = readFile.nextLine();
                String[] mString = cc.split("\n");
                for(String num : mString){
                    String [] mmString = num.split(" ");
                    for(String numnum : mmString){
                        double f = Double.parseDouble(numnum);
                        mm.mem[i][j] = f;
                        j++;
                    }
                    mm.setCol(j);
                    j = 0;
                    i++;
                }
                mm.setRow(i); 
            }
            readFile.close();
        } catch (FileNotFoundException e) {
            // kasus error
            System.out.println("Error");
            e.printStackTrace();
        }
    }

    public static void readFileToMatrixInterpolasi(Matrix mm, Matrix x) {
        System.out.println("Masukkan Nama File:");
        Scanner scanFile = new Scanner(System.in);
        String nameFile = new String();
        // scanFile.nextLine();
        // System.out.print("Masukkan nama file yang diinginkan: ");
        nameFile = scanFile.nextLine();
        try {
            File file = new File("../test/"+nameFile);
            Scanner readFile = new Scanner(file);
            int i, j;
            i = 0;
            j = 0;
            mm.setRow(1);
            mm.setCol(2);
            while (readFile.hasNext()) {
                String cc = readFile.nextLine();
                String[] mString = cc.split("\n");
                for (String num : mString) {
                    String[] mmString = num.split(" ");
                    for (String numnum : mmString) {
                        double f = Double.parseDouble(numnum);
                        // System.out.println("pass");
                        // System.out.println(numnum);
                        mm.mem[i][j] = f;
                        // System.out.println(j);
                        j++;
                    }
                    // mm.setCol(j);
                    j = 0;
                    i++;
                }
                mm.setRow(i);
            }
            // mm.displayMatrix();
            x.mem[0][0] = mm.mem[mm.getRow() - 1][0];
            // System.out.println(x);
            mm.setRow(i - 1);
            // mm.displayMatrix();
            // System.out.println("----");
            readFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }

    public static void readFileToMatrixBicubic(Matrix mm, Matrix xy){
    // Fungsi: membaca matrix dari file
    // sekaligus membaca nilai x,y untuk mencari nilai f(x,y)
        // membaca namafile
        System.out.println("Masukkan Nama File:");
        Scanner scanFile = new Scanner(System.in);
        String nameFile = scanFile.nextLine();
        try {
            // proses membaca file dan mengisi matrix
            File file = new File("../test/"+nameFile);
            Scanner readFile = new Scanner(file);
            int i,j;
            i = 0;
            j = 0;
            mm.setRow(5);
            mm.setCol(4);
            while (readFile.hasNext()) {
                String cc = readFile.nextLine();
                String[] mString = cc.split("\n");
                for(String num : mString){
                    String [] mmString = num.split(" ");
                    for(String numnum : mmString){
                        double f = Double.parseDouble(numnum);
                        mm.mem[i][j] = f;
                        j++;
                    }
                    j = 0;
                    i++;
                }
            }
            // membaca nilai x,y
            xy.mem[0][0] = mm.mem[i-1][0];
            xy.mem[0][1] = mm.mem[i-1][1];
            mm.setRow(i-1); 
            readFile.close();
        } catch (FileNotFoundException e) {
            // kasus error
            System.out.println("Error");
            e.printStackTrace();
        }
    }

    public static void readFileToMatrixRegresi(Matrix mm, Matrix x){
    // Fungsi: membaca matrix dari file
    // sekaligus membaca nilai x0-xn untuk mencari nilai f(x0,...,xn)
        // membaca namafile
        System.out.println("Masukkan Nama File:");
        Scanner scanFile = new Scanner(System.in);
        String nameFile = scanFile.nextLine();
        try {
            // proses membaca file dan mengisi matrix
            File file = new File("../test/"+nameFile);
            Scanner readFile = new Scanner(file);
            int i,j;
            i = 0;
            j = 0;
            while (readFile.hasNext()) {
                String cc = readFile.nextLine();
                String[] mString = cc.split("\n");
                for(String num : mString){
                    String [] mmString = num.split(" ");
                    for(String numnum : mmString){
                        double f = Double.parseDouble(numnum);
                        if(i == mm.getRow()){
                            x.mem[0][j] = f;
                        }else{
                            mm.mem[i][j] = f;
                        }
                        j++;
                    }
                    j = 0;
                    i++;
                }
            }
            // membaca x0-xn
            //for(int k = 0; k < mm.getCol(); k++){
                //x.mem[0][k] = mm.mem[i-1][k];
            //}
            //mm.setRow(i-1); 
            readFile.close();
        } catch (FileNotFoundException e) {
            // kasus error
            System.out.println("Error");
            e.printStackTrace();
        }
    }

    public static void readKeyboardToMatrix(Matrix m){
    // Fungsi: membaca matrix dari input keyboard
        // proses membaca matrix
        for(int i = 0 ; i < m.getRow(); i ++){
            int j;
            j = 0;
            Scanner obj = new Scanner(System.in);
            String strRow = obj.nextLine();
            String[] Row = strRow.split(" ");
            for(String num : Row){
                double d = Double.parseDouble(num);
                m.mem[i][j] = d;
                j ++ ;
            }
        }
    }


    public static void writeMatrixAndStringToFile(Matrix m, String str){
    // Fungsi: menulis matrix ke suatu file
    // sekaligus menulis string tambahan untuk suatu keterangan
        // membaca nama file
        System.out.println("Masukkan Nama File:");
        Scanner scanFile = new Scanner(System.in);
        String nameFile = scanFile.nextLine();

        try {
            File file = new File("../test/"+nameFile);
            // membuat file jika tidak ada
            file.createNewFile();
            // proses menulis matrix dalam file
            FileWriter cc = new FileWriter("../test/"+nameFile);
            for(int i = 0; i<m.getRow(); i++){
                for(int j = 0; j<m.getCol(); j++){
                    String num = Double.toString(m.mem[i][j]);
                    cc.write(num);
                    cc.write(" ");
                }
                cc.write("\n");
            }
            // menuliskan string tambahan
            cc.write(str);
            cc.write("\n");
            cc.close();
        } catch (IOException e) {
            // kasus error
            System.out.println("Error");
            e.printStackTrace();
        }
    }

    public static void writeMatrixToFile(Matrix m){
    // Fungsi: menulis matrix ke suatu file
        // membaca nama file
        System.out.println("Masukkan Nama File:");
        Scanner scanFile = new Scanner(System.in);
        String nameFile = scanFile.nextLine();

        try {
            File file = new File("../test/"+nameFile);
            // membuat file jika tidak ada
            file.createNewFile();
            // proses menulis file
            FileWriter cc = new FileWriter("../test/"+nameFile);
            for(int i = 0; i<m.getRow(); i++){
                for(int j = 0; j<m.getCol(); j++){
                    String num = Double.toString(m.mem[i][j]);
                    cc.write(num);
                    cc.write(" ");
                }
                cc.write("\n");
            }
            cc.close();
        } catch (IOException e) {
            // kasus error
            System.out.println("Error");
            e.printStackTrace();
        }
    }

    public static void writeStringToFile(String str){
    // Fungsi: menulis matrix ke suatu file
        // membaca nama file
        System.out.println("Masukkan Nama File:");
        Scanner scanFile = new Scanner(System.in);
        String nameFile = scanFile.nextLine();

        try {
            File file = new File("../test/"+nameFile);
            // membuat file jika tidak ada
            file.createNewFile();
            // proses menulis file
            FileWriter cc = new FileWriter("../test/"+nameFile);
            cc.write(str);
            cc.write("\n");
            cc.close();
        } catch (IOException e) {
            // kasus error
            System.out.println("Error");
            e.printStackTrace();
        }
    }

    public static void writeArrayStringToFile(String[] listStr){
    // Fungsi: menulis matrix ke suatu file
        // membaca nama file
        System.out.println("Masukkan Nama File:");
        Scanner scanFile = new Scanner(System.in);
        String nameFile = scanFile.nextLine();

        try {
            File file = new File("../test/"+nameFile);
            // membuat file jika tidak ada
            file.createNewFile();
            // proses menulis file
            FileWriter cc = new FileWriter("../test/"+nameFile);
            for(String kata : listStr){
                cc.write(kata);
                cc.write("\n");  
            }
            cc.close();
        } catch (IOException e) {
            // kasus error
            System.out.println("Error");
            e.printStackTrace();
        }
    }
}
