package com.thescottsweb.ipaddr;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import com.thescottsweb.ipaddr.internal.IpAddrMaskImpl;

public interface IpAddrMask extends Comparable<IpAddrMask> {

	// ============================================================================================

	public static final String IP_MASK_DELIMITER = "/";

	public static final IpAddrMask ZERO = valueOf("0.0.0.0/0");

	public static IpAddrMask valueOf(long longValue) {
		return IpAddrMaskImpl.valueOf(longValue);
	}

	public static IpAddrMask valueOf(IpAddr ipAddr, IpMask ipMask) {
		assert ipAddr != null;
		assert ipMask != null;
		return valueOf((((long) ipAddr.getIntValue()) << 32) | (ipMask.getIntValue() & 0xffffffffL));
	}

	public static IpAddrMask valueOf(IpAddrMask ipAddrMask) {
		assert ipAddrMask != null;
		return valueOf(ipAddrMask.ipAddr(), ipAddrMask.ipMask());
	}

	public static IpAddrMask valueOf(String arg) {

		if (arg == null)
			return null;

		if (!arg.contains(IP_MASK_DELIMITER))
			throw new NumberFormatException(String.format("Missing delimiter %s. Valid format is ip%smask",
					IP_MASK_DELIMITER, IP_MASK_DELIMITER));

		String[] argArray = arg.split(IP_MASK_DELIMITER);

		return valueOf(IpAddr.valueOf(argArray[0]), IpMask.valueOf(argArray[1]));
	}

	public static boolean validate(String arg0) {

		try {
			IpAddrMask.valueOf(arg0);
		} catch (NumberFormatException e) {
			return false;
		}

		return true;
	}

	// ============================================================================================

	IpAddr getIpAddr();

	IpAddr ipAddr();

	IpMask getIpMask();

	IpMask ipMask();

	// ============================================================================================

	// The IpAddr masked with the IpMask (may be the same)
	public default IpAddr netIpAddr() {
		return ipAddr().masked(ipMask());
	}

	public default IpAddr broadcastIpAddr() {
		return ipAddr().inversedMasked(ipMask());
	}

	public default boolean contains(IpAddr ipAddr) {
		IpAddr thisMaskedIpAddr = this.ipAddr().masked(ipMask());
		IpAddr argMaskedIpAddr = ipAddr.masked(ipMask());
		return (thisMaskedIpAddr.equals(argMaskedIpAddr));
	}

	public default boolean overlaps(IpAddrMask ipAddrMask) {
		// Mask with the smaller mask (larger network)
		IpMask mask = ipMask().compareTo(ipAddrMask.ipMask()) < 0 ? ipMask() : ipAddrMask.ipMask();
		return (ipAddr().masked(mask).equals(ipAddrMask.ipAddr().masked(mask)));
	}

	public default boolean isIpAddrNet() {

		if (ipAddr().equals(ipAddr().masked(ipMask())))
			return true;
		else
			return false;
	}

	public default boolean isIpAddrBroadcast() {

		if (ipAddr().equals(ipAddr().inversedMasked(ipMask())))
			return true;
		else
			return false;
	}

	public default boolean isIpAddrNetOrBroadcast() {

		if (isIpAddrNet())
			return true;
		else if (isIpAddrBroadcast())
			return true;
		else
			return false;
	}

	public default IpAddr upperIpAddr() {
		return ipAddr();
	}

	public default IpAddrMask upperIpAddrMask() {
		return IpAddrMask.valueOf(ipAddr().masked(ipMask()), ipMask());
	}

	public default IpAddr lowerIpAddr() {
		return ipAddr().inversedMasked(ipMask());
	}

	public default IpAddrMask lowerIpAddrMask() {
		return IpAddrMask.valueOf(ipAddr().inversedMasked(ipMask()), ipMask());
	}

	public default Collection<IpAddrMask> subnets(IpMask mask) {

		assert mask != null;

		Set<IpAddrMask> results = new LinkedHashSet<>();

		int start = ipAddr().getIntValue();
		int end = start + Math.abs(this.ipMask().getIntValue());
		int increment = Math.abs(mask.getIntValue()); // + 1;

		// System.out.println("start:" + start);
		// System.out.println("end:" + end);
		// System.out.println("increment:" + increment);

		for (int i = start; i < end; i = i + increment) {
			results.add(IpAddrMask.valueOf(IpAddr.valueOf(i), mask));
		}

		return results;
	}

	public default long longValue() {
		return (((long) ipAddr().getIntValue()) << 32) | (ipMask().getIntValue() & 0xffffffffL);
	}

	public default int defaultHashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ipAddr() == null) ? 0 : ipAddr().hashCode());
		result = prime * result + ((ipMask() == null) ? 0 : ipMask().hashCode());
		return result;
	}

	public default boolean defaultEquals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IpAddrMask other = (IpAddrMask) obj;
		if (ipAddr() == null) {
			if (other.ipAddr() != null)
				return false;
		} else if (!ipAddr().equals(other.ipAddr()))
			return false;
		if (ipMask() == null) {
			if (other.ipMask() != null)
				return false;
		} else if (!ipMask().equals(other.ipMask()))
			return false;
		return true;
	}

	public default String defaultToString() {
		return ipAddr().toString() + IP_MASK_DELIMITER + ipMask().toSlash();
	}

	@Override
	public default int compareTo(IpAddrMask arg0) {
		return ipAddr().getIntValue() - arg0.ipAddr().getIntValue();
	}

	// ============================================================================================

}
