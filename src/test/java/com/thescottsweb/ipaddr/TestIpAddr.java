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

		Port port = Port.valueOf("9000");
		assert port.getIntValue() == 9000;

		IpAddrPort ipAddrPort = IpAddrPort.valueOf("172.16.0.1:9001");
		assert ipAddrPort.getPort().getIntValue() == 9001;

	}

}