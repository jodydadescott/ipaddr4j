package com.thescottsweb.ipaddr;

import org.junit.Test;

import com.thescottsweb.ipaddr.IpAddr;

public class TestIpAddr {

	@Test
	public void testIpAddr() {

		String testIpString = "172.16.0.1";
		IpAddr testIp = IpAddr.valueOf(testIpString);
		assert testIp.toString().equals(testIpString);

		IpAddr testIpFromInt = IpAddr.valueOf(testIp.getIntValue());
		assert testIpFromInt == testIp;

	}

}