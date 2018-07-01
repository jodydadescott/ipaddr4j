package com.thescottsweb.ipaddr;

import com.thescottsweb.ipaddr.internal.IpMaskImpl;

public interface IpMask extends IpAddr {

	public static final IpMask S32 = IpMaskImpl.S32;
	public static final IpMask S31 = IpMaskImpl.S31;
	public static final IpMask S30 = IpMaskImpl.S30;
	public static final IpMask S29 = IpMaskImpl.S29;
	public static final IpMask S28 = IpMaskImpl.S28;
	public static final IpMask S27 = IpMaskImpl.S27;
	public static final IpMask S26 = IpMaskImpl.S26;
	public static final IpMask S25 = IpMaskImpl.S25;
	public static final IpMask S24 = IpMaskImpl.S24;
	public static final IpMask S23 = IpMaskImpl.S23;
	public static final IpMask S22 = IpMaskImpl.S22;
	public static final IpMask S21 = IpMaskImpl.S21;
	public static final IpMask S20 = IpMaskImpl.S20;
	public static final IpMask S19 = IpMaskImpl.S19;
	public static final IpMask S18 = IpMaskImpl.S18;
	public static final IpMask S17 = IpMaskImpl.S17;
	public static final IpMask S16 = IpMaskImpl.S16;
	public static final IpMask S15 = IpMaskImpl.S15;
	public static final IpMask S14 = IpMaskImpl.S14;
	public static final IpMask S13 = IpMaskImpl.S13;
	public static final IpMask S12 = IpMaskImpl.S12;
	public static final IpMask S11 = IpMaskImpl.S11;
	public static final IpMask S10 = IpMaskImpl.S10;
	public static final IpMask S9 = IpMaskImpl.S9;
	public static final IpMask S8 = IpMaskImpl.S8;
	public static final IpMask S7 = IpMaskImpl.S7;
	public static final IpMask S6 = IpMaskImpl.S6;
	public static final IpMask S5 = IpMaskImpl.S5;
	public static final IpMask S4 = IpMaskImpl.S4;
	public static final IpMask S3 = IpMaskImpl.S3;
	public static final IpMask S2 = IpMaskImpl.S2;
	public static final IpMask S1 = IpMaskImpl.S1;
	public static final IpMask S0 = IpMaskImpl.S0;
	public static final IpMask ZERO = S0;

	public static final IpMask LOOP = S32;
	public static final IpMask WIRE = S31;

	public static boolean validate(String ipMask) {
		assert ipMask != null;

		try {
			IpMask.valueOf(ipMask);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	public static IpMask valueOf(String ipMask) {

		// If the string arg has the character "." then we treat the string as
		// an IP. Else we treat it as short notation.

		if (ipMask.contains(".")) {

			String[] octets = ipMask.split("\\.");

			if (octets.length != 4)
				throw new NumberFormatException(
						String.format("Expecting four octets seperated by periods, found %s", octets.length));

			return valueOf(IpAddr.valueOf(ipMask).getIntValue());
		} else {
			return valueOf(Integer.valueOf(ipMask));
		}
	}

	public static IpMask valueOf(IpMask ipMask) {
		assert ipMask != null;
		return valueOf(ipMask.getIntValue());
	}

	public static IpMask valueOf(int ipMask) {
		return IpMaskImpl.valueOf(ipMask);
	}

	public default int toSlash() {

		if (getIntValue() == -1)
			return 32;

		if (getIntValue() == 0)
			return 0;

		for (int i = 0; i < 32; i++) {
			if (((0xffffffff << (32 - i))) == getIntValue())
				return i;
		}

		throw new AssertionError("Should not be here");
	}

}
