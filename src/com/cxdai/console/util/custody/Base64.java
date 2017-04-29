package com.cxdai.console.util.custody;

public final class Base64 {

	public Base64() {
	}

	public static boolean isBase64(String isValidString) {
		return isArrayByteBase64(isValidString.getBytes());
	}

	public static boolean isBase64(byte octect) {
		return octect == 61 || base64Alphabet[octect] != -1;
	}

	public static boolean isArrayByteBase64(byte arrayOctect[]) {
		int length = arrayOctect.length;
		if (length == 0)
			return true;
		for (int i = 0; i < length; i++)
			if (!isBase64(arrayOctect[i]))
				return false;

		return true;
	}

	public static byte[] encode(byte binaryData[]) {
		int lengthDataBits = binaryData.length * 8;
		int fewerThan24bits = lengthDataBits % 24;
		int numberTriplets = lengthDataBits / 24;
		byte encodedData[] = null;
		if (fewerThan24bits != 0)
			encodedData = new byte[(numberTriplets + 1) * 4];
		else
			encodedData = new byte[numberTriplets * 4];
		byte k = 0;
		byte l = 0;
		byte b1 = 0;
		byte b2 = 0;
		byte b3 = 0;
		int encodedIndex = 0;
		int dataIndex = 0;
		int i = 0;
		for (i = 0; i < numberTriplets; i++) {
			dataIndex = i * 3;
			b1 = binaryData[dataIndex];
			b2 = binaryData[dataIndex + 1];
			b3 = binaryData[dataIndex + 2];
			l = (byte) (b2 & 0xf);
			k = (byte) (b1 & 3);
			encodedIndex = i * 4;
			byte val1 = (b1 & 0xffffff80) != 0 ? (byte) (b1 >> 2 ^ 0xc0)
					: (byte) (b1 >> 2);
			byte val2 = (b2 & 0xffffff80) != 0 ? (byte) (b2 >> 4 ^ 0xf0)
					: (byte) (b2 >> 4);
			byte val3 = (b3 & 0xffffff80) != 0 ? (byte) (b3 >> 6 ^ 0xfc)
					: (byte) (b3 >> 6);
			encodedData[encodedIndex] = lookUpBase64Alphabet[val1];
			encodedData[encodedIndex + 1] = lookUpBase64Alphabet[val2 | k << 4];
			encodedData[encodedIndex + 2] = lookUpBase64Alphabet[l << 2 | val3];
			encodedData[encodedIndex + 3] = lookUpBase64Alphabet[b3 & 0x3f];
		}

		dataIndex = i * 3;
		encodedIndex = i * 4;
		if (fewerThan24bits == 8) {
			b1 = binaryData[dataIndex];
			k = (byte) (b1 & 3);
			byte val1 = (b1 & 0xffffff80) != 0 ? (byte) (b1 >> 2 ^ 0xc0)
					: (byte) (b1 >> 2);
			encodedData[encodedIndex] = lookUpBase64Alphabet[val1];
			encodedData[encodedIndex + 1] = lookUpBase64Alphabet[k << 4];
			encodedData[encodedIndex + 2] = 61;
			encodedData[encodedIndex + 3] = 61;
		} else if (fewerThan24bits == 16) {
			b1 = binaryData[dataIndex];
			b2 = binaryData[dataIndex + 1];
			l = (byte) (b2 & 0xf);
			k = (byte) (b1 & 3);
			byte val1 = (b1 & 0xffffff80) != 0 ? (byte) (b1 >> 2 ^ 0xc0)
					: (byte) (b1 >> 2);
			byte val2 = (b2 & 0xffffff80) != 0 ? (byte) (b2 >> 4 ^ 0xf0)
					: (byte) (b2 >> 4);
			encodedData[encodedIndex] = lookUpBase64Alphabet[val1];
			encodedData[encodedIndex + 1] = lookUpBase64Alphabet[val2 | k << 4];
			encodedData[encodedIndex + 2] = lookUpBase64Alphabet[l << 2];
			encodedData[encodedIndex + 3] = 61;
		}
		return encodedData;
	}

	public static byte[] decode(byte base64Data[]) {
		if (base64Data.length == 0)
			return new byte[0];
		int numberQuadruple = base64Data.length / 4;
		byte decodedData[] = null;
		byte b1 = 0;
		byte b2 = 0;
		byte b3 = 0;
		byte b4 = 0;
		byte marker0 = 0;
		byte marker1 = 0;
		int encodedIndex = 0;
		int dataIndex = 0;
		int lastData;
		for (lastData = base64Data.length; base64Data[lastData - 1] == 61;)
			if (--lastData == 0)
				return new byte[0];

		decodedData = new byte[lastData - numberQuadruple];
		for (int i = 0; i < numberQuadruple; i++) {
			dataIndex = i * 4;
			marker0 = base64Data[dataIndex + 2];
			marker1 = base64Data[dataIndex + 3];
			b1 = base64Alphabet[base64Data[dataIndex]];
			b2 = base64Alphabet[base64Data[dataIndex + 1]];
			if (marker0 != 61 && marker1 != 61) {
				b3 = base64Alphabet[marker0];
				b4 = base64Alphabet[marker1];
				decodedData[encodedIndex] = (byte) (b1 << 2 | b2 >> 4);
				decodedData[encodedIndex + 1] = (byte) ((b2 & 0xf) << 4 | b3 >> 2 & 0xf);
				decodedData[encodedIndex + 2] = (byte) (b3 << 6 | b4);
			} else if (marker0 == 61)
				decodedData[encodedIndex] = (byte) (b1 << 2 | b2 >> 4);
			else if (marker1 == 61) {
				b3 = base64Alphabet[marker0];
				decodedData[encodedIndex] = (byte) (b1 << 2 | b2 >> 4);
				decodedData[encodedIndex + 1] = (byte) ((b2 & 0xf) << 4 | b3 >> 2 & 0xf);
			}
			encodedIndex += 3;
		}

		return decodedData;
	}

	private static final int BASELENGTH = 255;

	private static final int LOOKUPLENGTH = 64;

	private static final int TWENTYFOURBITGROUP = 24;

	private static final int EIGHTBIT = 8;

	private static final int SIXTEENBIT = 16;

	private static final int SIXBIT = 6;

	private static final int FOURBYTE = 4;

	private static final int SIGN = -128;

	private static final byte PAD = 61;

	private static byte base64Alphabet[];

	private static byte lookUpBase64Alphabet[];

	static {
		base64Alphabet = new byte[255];
		lookUpBase64Alphabet = new byte[64];
		for (int i = 0; i < 255; i++)
			base64Alphabet[i] = -1;

		for (int i = 90; i >= 65; i--)
			base64Alphabet[i] = (byte) (i - 65);

		for (int i = 122; i >= 97; i--)
			base64Alphabet[i] = (byte) ((i - 97) + 26);

		for (int i = 57; i >= 48; i--)
			base64Alphabet[i] = (byte) ((i - 48) + 52);

		base64Alphabet[43] = 62;
		base64Alphabet[47] = 63;
		for (int i = 0; i <= 25; i++)
			lookUpBase64Alphabet[i] = (byte) (65 + i);

		int i = 26;
		for (int j = 0; i <= 51; j++) {
			lookUpBase64Alphabet[i] = (byte) (97 + j);
			i++;
		}

		i = 52;
		for (int j = 0; i <= 61; j++) {
			lookUpBase64Alphabet[i] = (byte) (48 + j);
			i++;
		}

		lookUpBase64Alphabet[62] = 43;
		lookUpBase64Alphabet[63] = 47;
	}
}