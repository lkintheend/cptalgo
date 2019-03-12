package vn.framgia.process;

import java.util.BitSet;

public class Encode {
    public Byte[] EncodeInABlock(Byte[] fi,Byte[] w, Byte[] k, Byte mess, Integer r, Integer m, Integer n){
        Integer sumMatrix = MatrixProcess.sumMatrix(fi);
        if(sumMatrix==(int) mess) return fi;
        Integer d = mess -sumMatrix;
        Byte[] za = MatrixProcess.makeZa(w, MatrixProcess.addMatrix(fi,k , m, n),r, m, n);
        for(int i=0;i<m*n;i++){
            if((int)za[i]==d){
                fi[i] = fi[i]==0?(byte)1:0;
                return fi;
            } else {
                return fi;
            }
        }
        return null;
    }
    
    public static void main(String[] args) {
		String clearText= "aasdf";
		BitSet bitSet = new BitSet(3);
		bitSet.set(0);
//		bitSet.set(1);
//		bitSet.set(2);

		print(getBitSet(bits2Ints(bitSet)[0]));
//		System.out.println(bitSet.length());
//		print(bitSet);
	}
    
    static void print(BitSet bitSet) {
    	for(int i=0;i<bitSet.length();i++)
    		System.out.print(bitSet.get(i)?1:0);
    }
    
    public static BitSet getBitSet(int num){
        char[] bits = Integer.toBinaryString(num).toCharArray();  
        BitSet bitSet = new BitSet(bits.length);  
        for(int i = 0; i < bits.length; i++){  
            if(bits[i] == '1'){
                bitSet.set(i, true);
            }
            else{
                bitSet.set(i, false);
            }                
        }
        return bitSet;
    } 
    static int[] bits2Ints(BitSet bs) {
        int[] temp = new int[bs.size() / 3];

        for (int i = 0; i < temp.length; i++)
          for (int j = 0; j < 3; j++)
            if (bs.get(i * 3 + j))
              temp[i] |= 1 << j;

        return temp;
      }
}
