package vn.framgia.process;

public class MatrixProcess {
    public static byte[] addMatrix(byte[] fi, byte[] k, Integer length) {
        byte[] result = new byte[length];
        for (int i = 0; i < (length); i++) {
            result[i] = fi[i] == k[i] ? (byte) 0 : 1;
        }
        return result;
    }

    public static byte[] xMatrix(byte[] t, byte[] w, Integer length){
        byte[] result = new byte[length];
        for (int i = 0; i < (length); i++) {
            result[i] = t[i] == 0 ? (byte) 0 : w[i];
        }
        return result;
    }

    public static Integer sumMatrix(byte[] matrix){
        Integer result =0;
        for (int i=0; i<matrix.length;i++){
            result = result + matrix[i];
        }
        return result;
    };

    public static byte[] makeZa(byte[] w , byte[] t, Integer r, Integer length){
        byte[] result = new byte[length];
        byte max =(byte)Math.pow(2, r);
        for(int i=0; i<length; i++){
            result[i]= t[i]==0? w[i]:(byte)(max-w[i]);
        }
        return result;
    }

    public static void print (byte[] w, Integer m, Integer n){
        for(int i = 1; i<=m*n;i++){
            System.out.print(w[i-1]);
            if(i%m ==0)System.out.println();
        }
    }
}
