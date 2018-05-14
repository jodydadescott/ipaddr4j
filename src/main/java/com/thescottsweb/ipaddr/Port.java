package com.thescottsweb.ipaddr;

import com.thescottsweb.ipaddr.internal.PortImpl;

public interface Port extends Comparable<Port> {

	// ============================================================================================

	public static Port valueOf(int value) {
		return PortImpl.valueOf(value);
	}

	public static boolean validate(String arg0) {

		try {
			Port.valueOf(arg0);
		} catch (AssertionError e) {
			return false;
		}

		return true;
	}

	public static Port valueOf(String arg0) {

		if (arg0 == null)
			return null;

		int result = 0;

		try {
			result = Integer.valueOf(arg0);
		} catch (NumberFormatException e) {
			throw new AssertionError("The string " + arg0 + " is not a valid port");
		}

		if (result < 0 || result > 65535) {
			throw new AssertionError("The integer " + result + " is not valid (expected value is between 0 and 65535");
		}

		return valueOf(result);
	}

	public static Port valueOf(Port port) {
		assert port != null;
		return valueOf(port.getIntValue());
	}

	// ============================================================================================

	public int getIntValue();

	// ============================================================================================

	@Override
	public default int compareTo(Port port) {

		if (getIntValue() == port.getIntValue())
			return 0;

		if (getIntValue() < 0 && port.getIntValue() < 0) {
			if (getIntValue() > port.getIntValue())
				return 1;
			else
				return -1;
		}

		if (getIntValue() < 0 && port.getIntValue() > 0) {
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
		Port other = (Port) obj;
		if (getIntValue() != other.getIntValue())
			return false;
		return true;
	}

	public default String defaultToString() {
		return String.valueOf(getIntValue());
	}

	// ============================================================================================

}
