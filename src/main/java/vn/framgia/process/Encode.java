package vn.framgia.process;

import java.util.ArrayList;
import java.util.List;

public class Encode {
	public static BinaryImage encode(BinaryImage binaryImage, String mess, byte[] w, byte[] k, int m, int n, int r) {
		int width = binaryImage.getWidth();
		int height = binaryImage.getHeight();
		byte[] imageResult = binaryImage.getImage().clone();
		int[] messEncode = BitUltil.encodeMess(mess+"$", r);
		for (int i = 0; i < messEncode.length; i++) {
			processEncode(imageResult, i * m * n, w, k, messEncode[i], r);
		}

		return new BinaryImage(width, height, imageResult);
	}

	public static void processEncode(byte[] image, int from, byte[] w, byte[] k, int mess, int r) {
		byte[] fi = readByteFrom(image, from, w.length);
		byte[] t = MatrixProcess.addMatrix(fi, k, w.length);
		byte[] p = MatrixProcess.xMatrix(t, w, w.length);
		byte[] za = MatrixProcess.makeZa(w, t, r, w.length);
		int d = mess - MatrixProcess.sumMatrix(p) % (int) Math.pow(2, r);
		if(d<0) d=(int) Math.pow(2, r)+d;
		
		boolean flag = true;
		if (d != 0) {
			for (int i = 0; i < w.length; i++) {
				if (za[i] == d% (int) Math.pow(2, r)) {
					fi[i] = (byte) (fi[i] == 0 ? 1 : 0);
					flag = false;
					break;
				}
			}
			
			if (flag) {
				out:
				for (int m = 0; m < w.length; m++) {
					for (int n = m + 1; n < w.length; n++) {
						if ((za[m] + za[n] - d + Math.pow(2, r)) % (int) Math.pow(2, r) == 0) {
							fi[n] = (byte) (fi[n] == 0 ? 1 : 0);
							fi[m] = (byte) (fi[m] == 0 ? 1 : 0);
							break out;
						}
					}
				}
			}
			flag = true;
		}
		writeByteFrom(image, from, w.length, fi);
	}

	public static String decode(BinaryImage binaryImage, byte[] w, byte[] k, int m, int n, int r) {
		List<Integer> result = new ArrayList<>();
		byte[] imageResult = binaryImage.getImage().clone();
		byte[] fi;

		for (int i = 0; i < imageResult.length - m * n; i = i + m * n) {
			fi = readByteFrom(imageResult, i, m * n);
			int j = processDecode(fi, w, k, r);
			result.add(j);
		}
		int[] intResult = result.stream().mapToInt(i -> i).toArray();
		String mess = BitUltil.decodeMess(intResult, r);
		return mess.split("\\$")[0];
	}

	public static int processDecode(byte[] data, byte[] w, byte[] k, int r) {
		byte[] t = MatrixProcess.addMatrix(data, k, w.length);
		byte[] p = MatrixProcess.xMatrix(t, w, w.length);
		int sum = MatrixProcess.sumMatrix(p);
		int i = (int) (sum % 8);
		return i;
	}

	private static byte[] readByteFrom(byte[] data, int from, int length) {
		byte[] result = new byte[length];
		for (int i = 0; i < length; i++) {
			result[i] = data[from + i];
		}
		return result;
	}

	private static void writeByteFrom(byte[] data, int from, int length, byte[] value) {
		for (int i = 0; i < length; i++) {
			data[from + i] = value[i];
		}
	}

	public static void main(String[] args) {
		String path = "/home/framgia/demo.png";
		BinaryImage bs = ImageUtils.loadImage(path);
		byte[] w = { 1, 2, 3, 4, 5, 6, 7, 1, 2, 3, 4, 5, 6, 7, 1, 2 };
		byte[] k = { 1, 0, 1, 1, 0, 1, 1, 1, 0, 0, 0, 1, 1, 0, 1, 0 };
		// 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
		// 0, 0, 0
		// 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
		// 0, 0,
		BinaryImage result = encode(bs, "abcdsdfsdfe", w, k, 5, 5, 4);
		ImageUtils.makeImage(result);

		String path1 = "/home/framgia/demo2.png";
		BinaryImage image = ImageUtils.loadImage(path1);
		String a = decode(image, w, k, 5, 5, 4);
		System.out.println(a);
	}
}
