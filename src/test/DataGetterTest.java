package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import driver.DataGetter;

public class DataGetterTest {
	DataGetter cmd;

	/**
	 * Make a new command
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		cmd = new DataGetter("hello");
	}

	/**
	 * Check the that url was set
	 */
	@Test
	public void testConstructor() {
		assertTrue((cmd.URL).equals("hello"));
	}

}
