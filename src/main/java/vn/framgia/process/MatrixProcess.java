package vn.framgia.process;

public class MatrixProcess {
    public static Byte[] addMatrix(Byte[] fi, Byte[] k, Integer m, Integer n) {
        Byte[] result = new Byte[m * n];
        for (int i = 0; i < (m * n); i++) {
            result[i] = fi[i] == k[i] ? (byte) 0 : 1;
        }
        return result;
    }

    public static Byte[] xMatrix(Byte[] t, Byte[] w, Integer m, Integer n){
        Byte[] result = new Byte[m * n];
        for (int i = 0; i < (m * n); i++) {
            result[i] = t[i] == 0 ? (byte) 0 : w[i];
        }
        return result;
    }

    public static Integer sumMatrix(Byte[] matrix){
        Integer result =0;
        for (int i=1; i<matrix.length;i++){
            result = result + matrix[i];
        }
        return result;
    };

    public static Byte[] makeZa(Byte[] w , Byte[] t, Integer r, Integer m, Integer n){
        Byte[] result = new Byte[m*n];
        Byte max =(byte)Math.pow(2, r);
        for(int i=0; i<m*n; i++){
            result[i]= t[i]==0? w[i]:(byte)(max-w[i]);
        }
        return result;
    }



    public static void main(String[] args) {
        Byte[] fi = new Byte[] {1,0,1,
                                1,1,1,
                                0,1,1};
        Byte[] k = new Byte[]  {1,0,1,
                                0,0,1,
                                1,1,3};
        Byte[] w = new Byte[]  {1,2,3,
                                3,2,1,
                                3,2,1};
        Byte[] result =addMatrix(fi,k , 3, 3);
        print(result, 3, 3);
        print(makeZa(w,addMatrix(fi,k , 3, 3), 2, 3 , 3), 3 ,3);

    }

    public static void print (Byte[] w, Integer m, Integer n){
        for(int i = 1; i<=m*n;i++){
            System.out.print(w[i-1]);
            if(i%m ==0)System.out.println();
        }
    }
}
