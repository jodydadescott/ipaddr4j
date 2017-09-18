package com.thescottsweb.ipaddr.internal;

import java.util.Map;
import java.util.WeakHashMap;

import com.thescottsweb.ipaddr.IpAddr;

public class IpAddrImpl implements IpAddr {

	private static Map<Integer, IpAddr> cacheMap = new WeakHashMap<>();

	// ============================================================================================

	public static IpAddr valueOf(int value) {

		IpAddr entity = cacheMap.get(value);

		if (entity == null) {
			synchronized (cacheMap) {
				entity = cacheMap.get(value);
				if (entity == null) {
					entity = new IpAddrImpl(value);
					cacheMap.put(value, entity);
				}
			}
		}
		return entity;
	}

	// ============================================================================================

	private int intValue;

	private IpAddrImpl(int intValue) {
		this.intValue = intValue;
	}

	// ============================================================================================

	@Override
	public int getIntValue() {
		return intValue;
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
