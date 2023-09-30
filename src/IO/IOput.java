package IO;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import Matriks.*;

public class IOput{
    public static int inputMode(){
        int n;
        Scanner obj = new Scanner(System.in);
        System.out.println("Tentukan metode input!");
        System.out.println("1. Pembacaan File");
        System.out.println("2. Pembacaan dari Keyboard");
        while(true){
            n = obj.nextInt();
            if(n!=1 && n!=2){
                System.out.println("Pilihan hanya antara 1 atau 2!");
            }else{
                break;
            }
        }
        return n;
    }

    public static void readFileToMatrix(Matrix mm){
        Scanner scanFile = new Scanner(System.in);
        String nameFile = scanFile.nextLine();
        try {
            File file = new File(nameFile);
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
            System.out.println("Error");
            e.printStackTrace();
        }
    }

    public static void readFileToMatrixInterpolasi(Matrix mm, double x){
        Scanner scanFile = new Scanner(System.in);
        String nameFile = scanFile.nextLine();
        try {
            File file = new File(nameFile);
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
            x = mm.mem[i-1][0];
            mm.setRow(i-1); 
            readFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }

    public static void readFileToMatrixBicubic(Matrix mm, double x, double y){
        Scanner scanFile = new Scanner(System.in);
        String nameFile = scanFile.nextLine();
        try {
            File file = new File(nameFile);
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
            x = mm.mem[i-1][0];
            y = mm.mem[i-1][1];
            mm.setRow(i-1); 
            readFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }

    public static void readFileToMatrixRegresi(Matrix mm, int[] x){
        Scanner scanFile = new Scanner(System.in);
        String nameFile = scanFile.nextLine();
        try {
            File file = new File(nameFile);
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
            for(int k = 0; k < mm.getCol(); k++){
                x[k] = mm.mem[i-1][k];
            }
            mm.setRow(i-1); 
            readFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }

    public static void readFileToMatrixBicubic(Matrix mm, int x, int y){
        Scanner scanFile = new Scanner(System.in);
        String nameFile = scanFile.nextLine();
        try {
            File file = new File(nameFile);
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
            x = mm.mem[i-1][0];
            y = mm.mem[i-1][1];
            mm.setRow(i-1); 
            readFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }

    public static void readKeyboardToMatrix(Matrix m){
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
        Scanner scanFile = new Scanner(System.in);
        String nameFile = scanFile.nextLine();

        try {
            File file = new File(nameFile);
            file.createNewFile();
            FileWriter cc = new FileWriter(nameFile);
            for(int i = 0; i<m.getRow(); i++){
                for(int j = 0; j<m.getCol(); j++){
                    String num = Double.toString(m.mem[i][j]);
                    cc.write(num);
                    cc.write(" ");
                }
                cc.write("\n");
            }
            cc.write(str);
            cc.write("\n");
            cc.close();
        } catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }

    public static void writeMatrixToFile(Matrix m){
        Scanner scanFile = new Scanner(System.in);
        String nameFile = scanFile.nextLine();

        try {
            File file = new File(nameFile);
            file.createNewFile();
            FileWriter cc = new FileWriter(nameFile);
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
            System.out.println("Error");
            e.printStackTrace();
        }
    }
}
