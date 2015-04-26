package jtest;

import static org.junit.Assert.*;

import org.junit.Test;

import Test.Client;

public class ClientTest {

	@Test
	public void testClient() {
		Client c = new Client("4 3 +");
		assertEquals("4 3 +", c.getExpression());
	}

	@Test
	public void testStart() {
//		Client c = new Client("4 3 +");
//		assertEquals("4 3 +", c.getExpression());
	}

}
