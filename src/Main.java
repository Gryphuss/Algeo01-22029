public class Main {
    public static void main(String[] args){
        Matrix m = new Matrix(4, 5);
        m.readMatrix();
        OBE.GaussJordan(m);
        m.displayMatrix();
    }
}
