package vn.framgia.process;

import java.util.BitSet;

public class BitUltil {
	public static String covertBitSetToString(BitSet bitSet) {
		byte[] data = bitSet.toByteArray();
		return new String(data);
	}

	public static BitSet convertStringToBitSet(String text) {
		byte[] data = text.getBytes();
		BitSet result = BitSet.valueOf(data);
		return result;
	}
	
	public static BitSet[] cutBitSet(BitSet data, int length) {
		BitSet[] result = new BitSet[data.length() % length == 0 ? data.length() / length : data.length() / length + 1];
		for (int i = 0; i < result.length; i++) {
			result[i]=data.get(i * length, (i + 1) * length);
		}
		return result;
	}

	public static BitSet mergeBitSet(BitSet[] data) {
		BitSet result = new BitSet();
		int index = 0;
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length(); j++) {
				result.set(index, data[i].get(j));
				index++;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		String a = "abcd";
		BitSet data = convertStringToBitSet(a);
		BitSet[] arrayBit = cutBitSet(data, 10);
		print(data);
		System.out.println();
		print(mergeBitSet(arrayBit));

		System.out.println(covertBitSetToString(mergeBitSet(arrayBit)));
	}

	static void print(BitSet bitSet) {
		for (int i = 0; i < bitSet.length(); i++)
			System.out.print(bitSet.get(i) ? 1 : 0);
	}
}
