package com.thescottsweb.ipaddr.internal;

import java.util.Map;
import java.util.WeakHashMap;

import com.thescottsweb.ipaddr.IpAddr;
import com.thescottsweb.ipaddr.IpAddrMask;
import com.thescottsweb.ipaddr.IpMask;

public class IpAddrMaskImpl implements IpAddrMask {

	// ============================================================================================

	private static Map<Long, IpAddrMask> cacheMap = new WeakHashMap<>();

	public static IpAddrMask valueOf(long longValue) {

		IpAddrMask entity = cacheMap.get(longValue);
		if (entity == null) {
			synchronized (cacheMap) {
				entity = cacheMap.get(longValue);
				if (entity == null) {
					entity = new IpAddrMaskImpl(longValue);
					cacheMap.put(longValue, entity);
				}
			}
		}
		return entity;
	}

	// ============================================================================================

	private IpAddr ipAddr;
	private IpMask ipMask;

	private IpAddrMaskImpl(long longValue) {
		this.ipAddr = IpAddr.valueOf((int) (longValue >> 32));
		this.ipMask = IpMask.valueOf((int) longValue);
	}

	// ============================================================================================

	@Override
	public IpAddr getIpAddr() {
		return ipAddr;
	}

	@Override
	public IpAddr ipAddr() {
		return ipAddr;
	}

	@Override
	public IpMask getIpMask() {
		return ipMask;
	}

	@Override
	public IpMask ipMask() {
		return ipMask;
	}

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
