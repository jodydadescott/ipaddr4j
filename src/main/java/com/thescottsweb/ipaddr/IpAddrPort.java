package com.thescottsweb.ipaddr;

import com.thescottsweb.ipaddr.internal.IpAddrPortImpl;

public interface IpAddrPort {

	public static final String IP_PORT_DELIMITER = ":";

	public static IpAddrPort valueOf(String arg0) {

		if (arg0 == null) {
			return null;
		}

		if (!arg0.contains(IP_PORT_DELIMITER)) {
			throw new AssertionError("Expecting delimiter " + IP_PORT_DELIMITER);
		}

		String split[] = arg0.split(IP_PORT_DELIMITER);

		IpAddr ipAddr = IpAddr.valueOf(split[0]);
		Port port = Port.valueOf(split[1]);

		return IpAddrPortImpl.valueOf(ipAddr, port);
	}

	IpAddr getIpAddr();

	Port getPort();

	default int defaultHashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getIpAddr() == null) ? 0 : getIpAddr().hashCode());
		result = prime * result + ((getPort() == null) ? 0 : getPort().hashCode());
		return result;
	}

	default boolean defaultEquals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IpAddrPortImpl other = (IpAddrPortImpl) obj;
		if (getIpAddr() == null) {
			if (other.getIpAddr() != null)
				return false;
		} else if (!getIpAddr().equals(other.getIpAddr()))
			return false;
		if (getPort() == null) {
			if (other.getPort() != null)
				return false;
		} else if (!getPort().equals(other.getPort()))
			return false;
		return true;
	}

	public default String defaultToString() {
		return getIpAddr() + IP_PORT_DELIMITER + getPort();
	}

}
