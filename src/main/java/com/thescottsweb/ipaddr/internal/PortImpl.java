package com.thescottsweb.ipaddr.internal;

import java.util.Map;
import java.util.WeakHashMap;

import com.thescottsweb.ipaddr.Port;

public class PortImpl implements Port {

	private static Map<Integer, Port> cacheMap = new WeakHashMap<>();

	// ============================================================================================

	public static Port valueOf(int value) {

		Port entity = cacheMap.get(value);

		if (entity == null) {
			synchronized (cacheMap) {
				entity = cacheMap.get(value);
				if (entity == null) {
					entity = new PortImpl(value);
					cacheMap.put(value, entity);
				}
			}
		}
		return entity;
	}

	// ============================================================================================

	private int intValue;

	private PortImpl(int intValue) {
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
