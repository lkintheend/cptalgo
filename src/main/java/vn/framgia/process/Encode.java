package vn.framgia.process;

public class Encode {
	public Byte[] EncodeInABlock(Byte[] fi, Byte[] w, Byte[] k, Byte mess, Integer r, Integer m, Integer n) {
		Integer sumMatrix = MatrixProcess.sumMatrix(fi);
		if (sumMatrix == (int) mess)
			return fi;
		Integer d = mess - sumMatrix;
		Byte[] za = MatrixProcess.makeZa(w, MatrixProcess.addMatrix(fi, k, m, n), r, m, n);
		for (int i = 0; i < m * n; i++) {
			if ((int) za[i] == d) {
				fi[i] = fi[i] == 0 ? (byte) 1 : 0;
				return fi;
			}
		}
		return null;
	}

	public static void main(String[] args) {
	}
}
