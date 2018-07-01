package com.thescottsweb.ipaddr;

import com.thescottsweb.ipaddr.internal.IpAddrPortImpl;

public interface IpAddrPort {

	public static final String IP_PORT_DELIMITER = ":";

	public static IpAddrPort valueOf(IpAddr ipAddr, Port port) {

		if (ipAddr == null && port == null) {
			return null;

		}
		if (ipAddr == null) {
			throw new NullPointerException("ipAddr is null but port is not");
		}

		if (port == null) {
			throw new NullPointerException("port is null but ipAddr is not");
		}

		return IpAddrPortImpl.valueOf(ipAddr, port);
	}

	public static IpAddrPort valueOf(String ipAddrPort) {

		if (ipAddrPort == null) {
			return null;
		}

		IpAddrPort result = null;

		if (ipAddrPort.contains(IP_PORT_DELIMITER)) {
			String split[] = ipAddrPort.split(IP_PORT_DELIMITER);
			result = IpAddrPortImpl.valueOf(IpAddr.valueOf(split[0]), Port.valueOf(split[1]));
		} else {
			result = IpAddrPortImpl.valueOf(IpAddr.valueOf(ipAddrPort), Port.ZERO);
		}

		return result;
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
