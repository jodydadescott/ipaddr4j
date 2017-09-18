package com.thescottsweb.ipaddr;

import com.thescottsweb.ipaddr.internal.IpAddrImpl;

public interface IpAddr extends Comparable<IpAddr> {

	// ============================================================================================

	public static final IpAddr ZERO = valueOf(0);

	public static IpAddr valueOf(int value) {
		return IpAddrImpl.valueOf(value);
	}

	public static boolean validate(String arg0) {

		try {
			IpAddr.valueOf(arg0);
		} catch (NumberFormatException e) {
			return false;
		}

		return true;
	}

	public static IpAddr valueOf(String arg0) {

		if (arg0 == null)
			return null;

		String[] octets = arg0.split("\\.");

		if (octets.length != 4)
			throw new NumberFormatException("Expecting four octets seperated by periods, found " + octets.length);

		int result = 0;
		for (int i = 3; i >= 0; i--) {
			long octet = Integer.parseInt(octets[3 - i]);

			if (octet > 255 || octet < 0) {
				throw new NumberFormatException(
						"IP octet " + octet + " in address " + arg0 + " is NOT valid (expected value is 0 to 255)");
			}

			result |= (octet) << (i * 8);
		}

		return valueOf(result);
	}

	public static IpAddr valueOf(IpAddr ipAddr) {
		assert ipAddr != null;
		return valueOf(ipAddr.getIntValue());
	}

	// ============================================================================================

	public int getIntValue();

	// ============================================================================================

	public default IpAddr masked(IpMask ipMask) {
		assert ipMask != null;
		return valueOf(getIntValue() & ipMask.getIntValue());
	}

	public default IpAddr inversedMasked(IpMask ipMask) {

		assert ipMask != null;
		IpAddr result = valueOf((getIntValue() & ipMask.getIntValue()) - (ipMask.getIntValue() + 1));
		return result;
	}

	@Override
	public default int compareTo(IpAddr ipAddr) {

		if (getIntValue() == ipAddr.getIntValue())
			return 0;

		if (getIntValue() < 0 && ipAddr.getIntValue() < 0) {
			if (getIntValue() > ipAddr.getIntValue())
				return 1;
			else
				return -1;
		}

		if (getIntValue() < 0 && ipAddr.getIntValue() > 0) {
			return 1;
		}

		return -1;
	}

	public default int defaultHashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + getIntValue();
		return result;
	}

	public default boolean defaultEquals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IpAddr other = (IpAddr) obj;
		if (getIntValue() != other.getIntValue())
			return false;
		return true;
	}

	public default IpAddr increment() {
		return IpAddr.valueOf(this.getIntValue() + 1);
	}

	public default IpAddr decrement() {
		return IpAddr.valueOf(this.getIntValue() - 1);
	}

	public default String defaultToString() {
		return ((getIntValue() >> 24) & 0xFF) + "." + ((getIntValue() >> 16) & 0xFF) + "."
				+ ((getIntValue() >> 8) & 0xFF) + "." + (getIntValue() & 0xFF);
	}

	// ============================================================================================

}
