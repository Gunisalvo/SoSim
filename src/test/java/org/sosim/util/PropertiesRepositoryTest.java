package org.sosim.util;

import java.util.MissingResourceException;

import org.junit.Assert;
import org.junit.Test;

public class PropertiesRepositoryTest {

	private PropertiesRepository cobaia;
	
	@Test
	public void propertiesFileFoundTest() {
		
		cobaia = new PropertiesRepository( "test" );
		
		Assert.assertTrue( cobaia.getParameter("test1").equals("testing...") );
		Assert.assertTrue( cobaia.getParameter("test2").equals("testing 1,2,3") );
		
	}
	
	@Test(expected = MissingResourceException.class)
	public void propertiesFileNotFoundTest() {
		
		cobaia = new PropertiesRepository( "testing" );
	
	}
	
	@Test(expected = MissingResourceException.class)
	public void propertyNotFoundTest(){
		
		cobaia = new PropertiesRepository( "test" );
		
		Assert.assertTrue( cobaia.getParameter("hamsifu").equals("testing...") );
		
	}

}
