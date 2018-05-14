package com.thescottsweb.ipaddr.internal;

import java.util.Map;
import java.util.WeakHashMap;

import com.thescottsweb.ipaddr.IpAddr;
import com.thescottsweb.ipaddr.IpAddrPort;
import com.thescottsweb.ipaddr.Port;

public class IpAddrPortImpl implements IpAddrPort {

	private static Map<String, IpAddrPort> cacheMap = new WeakHashMap<>();

	// ============================================================================================

	public static IpAddrPort valueOf(IpAddr ipAddr, Port port) {

		String value = ipAddr.toString() + IP_PORT_DELIMITER + port.toString();

		IpAddrPort entity = cacheMap.get(value);

		if (entity == null) {
			synchronized (cacheMap) {
				entity = cacheMap.get(value);
				if (entity == null) {
					entity = new IpAddrPortImpl(ipAddr, port);
					cacheMap.put(value, entity);
				}
			}
		}
		return entity;
	}

	// ============================================================================================

	private IpAddr ipAddr;
	private Port port;

	private IpAddrPortImpl(IpAddr ipAddr, Port port) {
		this.ipAddr = ipAddr;
		this.port = port;
	}

	@Override
	public IpAddr getIpAddr() {
		return ipAddr;
	}

	@Override
	public Port getPort() {
		return port;
	}

	// ============================================================================================

	@Override
	public int hashCode() {
		return defaultHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return defaultEquals(obj);
	}

	@Override
	public String toString() {
		return defaultToString();
	}

	// ============================================================================================

}
