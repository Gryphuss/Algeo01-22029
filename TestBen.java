public class TestBen {
    public static void main (String[] args){
        int row,col;
        Matrix m = new Matrix();
        m.setMtx(3,2);
        row = m.getRow();
        col = m.getCol();
        System.out.println(row);
        System.out.println(col);
        m.printMtx();
    }
}