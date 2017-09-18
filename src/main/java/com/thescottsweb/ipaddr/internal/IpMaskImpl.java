package com.thescottsweb.ipaddr.internal;

import com.thescottsweb.ipaddr.IpMask;

public class IpMaskImpl implements IpMask {

	// ============================================================================================

	public static final IpMask S32 = new IpMaskImpl(-1);
	public static final IpMask S31 = new IpMaskImpl(-2);
	public static final IpMask S30 = new IpMaskImpl(-4);
	public static final IpMask S29 = new IpMaskImpl(-8);
	public static final IpMask S28 = new IpMaskImpl(-16);
	public static final IpMask S27 = new IpMaskImpl(-32);
	public static final IpMask S26 = new IpMaskImpl(-64);
	public static final IpMask S25 = new IpMaskImpl(-128);
	public static final IpMask S24 = new IpMaskImpl(-256);
	public static final IpMask S23 = new IpMaskImpl(-512);
	public static final IpMask S22 = new IpMaskImpl(-1024);
	public static final IpMask S21 = new IpMaskImpl(-2048);
	public static final IpMask S20 = new IpMaskImpl(-4096);
	public static final IpMask S19 = new IpMaskImpl(-8192);
	public static final IpMask S18 = new IpMaskImpl(-16384);
	public static final IpMask S17 = new IpMaskImpl(-32768);
	public static final IpMask S16 = new IpMaskImpl(-65536);
	public static final IpMask S15 = new IpMaskImpl(-131072);
	public static final IpMask S14 = new IpMaskImpl(-262144);
	public static final IpMask S13 = new IpMaskImpl(-524288);
	public static final IpMask S12 = new IpMaskImpl(-1048576);
	public static final IpMask S11 = new IpMaskImpl(-2097152);
	public static final IpMask S10 = new IpMaskImpl(-4194304);
	public static final IpMask S9 = new IpMaskImpl(-8388608);
	public static final IpMask S8 = new IpMaskImpl(-16777216);
	public static final IpMask S7 = new IpMaskImpl(-33554432);
	public static final IpMask S6 = new IpMaskImpl(-67108864);
	public static final IpMask S5 = new IpMaskImpl(-134217728);
	public static final IpMask S4 = new IpMaskImpl(-268435456);
	public static final IpMask S3 = new IpMaskImpl(-536870912);
	public static final IpMask S2 = new IpMaskImpl(-1073741824);
	public static final IpMask S1 = new IpMaskImpl(-2147483648);
	public static final IpMask S0 = new IpMaskImpl(0);

	public static IpMask valueOf(int intValue) {

		// Input could be in slash format or int value. If it is an int value,
		// it will be negative.

		switch (intValue) {

		// Slash value

		case 32:
			return S32;
		case 31:
			return S31;
		case 30:
			return S30;
		case 29:
			return S29;
		case 28:
			return S28;
		case 27:
			return S27;
		case 26:
			return S26;
		case 25:
			return S25;
		case 24:
			return S24;
		case 23:
			return S23;
		case 22:
			return S22;
		case 21:
			return S21;
		case 20:
			return S20;
		case 19:
			return S19;
		case 18:
			return S18;
		case 17:
			return S17;
		case 16:
			return S16;
		case 15:
			return S15;
		case 14:
			return S14;
		case 13:
			return S13;
		case 12:
			return S12;
		case 11:
			return S11;
		case 10:
			return S10;
		case 9:
			return S9;
		case 8:
			return S8;
		case 7:
			return S7;
		case 6:
			return S6;
		case 5:
			return S5;
		case 4:
			return S4;
		case 3:
			return S3;
		case 2:
			return S2;
		case 1:
			return S1;

		// Int value

		case -1:
			return S32;
		case -2:
			return S31;
		case -4:
			return S30;
		case -8:
			return S29;
		case -16:
			return S28;
		case -32:
			return S27;
		case -64:
			return S26;
		case -128:
			return S25;
		case -256:
			return S24;
		case -512:
			return S23;
		case -1024:
			return S22;
		case -2048:
			return S21;
		case -4096:
			return S20;
		case -8192:
			return S19;
		case -16384:
			return S18;
		case -32768:
			return S17;
		case -65536:
			return S16;
		case -131072:
			return S15;
		case -262144:
			return S14;
		case -524288:
			return S13;
		case -1048576:
			return S12;
		case -2097152:
			return S11;
		case -4194304:
			return S10;
		case -8388608:
			return S9;
		case -16777216:
			return S8;
		case -33554432:
			return S7;
		case -67108864:
			return S6;
		case -134217728:
			return S5;
		case -268435456:
			return S4;
		case -536870912:
			return S3;
		case -1073741824:
			return S2;
		case -2147483648:
			return S1;

		// Slash or int value

		case 0:
			return S0;
		}

		throw new NumberFormatException(
				String.format("The integer value %s is not a valid %s", intValue, IpMask.class.getSimpleName()));

	}

	// ============================================================================================

	private int intValue;

	private IpMaskImpl(int intValue) {
		this.intValue = intValue;
	}

	// ============================================================================================

	@Override
	public int getIntValue() {
		return intValue;
	}

	@Override
	public String toString() {
		return defaultToString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + intValue;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IpMaskImpl other = (IpMaskImpl) obj;
		if (intValue != other.intValue)
			return false;
		return true;
	}

	// ============================================================================================

}
