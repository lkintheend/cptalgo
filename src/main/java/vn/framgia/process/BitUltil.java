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
			BitSet bitSet = new BitSet(length);
			for (int j = 0; j < length; j++) {
				bitSet.set(j, data.get(i * length + j));
			}
			result[i] = bitSet;
		}
		return result;
	}

	public static BitSet mergeBitSet(BitSet[] data, int length) {
		BitSet result = new BitSet();
		int index = 0;
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < length; j++) {
				result.set(index, data[i].get(j));
				index++;
			}
		}
		return result;
	}

	public static BitSet getBitSet(int num, int length) {
		BitSet bitSet = new BitSet(length);
		for (int i = 0; i < length; i++) {
			if (num % 2 == 1) {
				bitSet.set(i, true);
			} else {
				bitSet.set(i, false);
			}
			num = num / 2;
		}
		return bitSet;
	}

	public static int getIntFromBit(BitSet bs, int length) {
		int result = 0;
		for (int i = length - 1; i >= 0; i--) {
			if (bs.get(i)) {
				result = result + (int) Math.pow(2, i);
			}
		}
		return result;
	}
	
	public static int[] encodeMess(String mess, int length) {
		BitSet[] bitSets = cutBitSet(convertStringToBitSet(mess), length);
		int[] result = new int[bitSets.length];
		for (int i = 0;i<bitSets.length;i++) {
			result[i]= getIntFromBit(bitSets[i], length);
		}
		return result;
	}
	
	public static String decodeMess(int[] data, int length) {
		BitSet[] bitSets = new BitSet[data.length];
		for(int i =0;i<data.length;i++) {
			bitSets[i] = getBitSet(data[i], length);
		}
		return covertBitSetToString(mergeBitSet(bitSets, length));
	}

	public static void main(String[] args) {
		int[] a = encodeMess("123123123sdfsdfsdfdsfds", 3);
		System.out.println(decodeMess(a, 3));
	}

	static void print(BitSet bitSet) {
		for (int i = 0; i < bitSet.length(); i++)
			System.out.print(bitSet.get(i) ? 1 : 0);
	}

}
