package org.sosim.util;

import junit.framework.Assert;

import org.junit.Test;

public class CircularListTest {

	private CircularList<Integer> cobaia;
	
	@Test
	public void constructorTest() {
		
		Integer[] array = new Integer[]{1,3,5};
		
		cobaia = new CircularList<Integer>(array);
		
		for(int i = 0;i < 3; i++){
			
			Assert.assertTrue(cobaia.getCurrentValue() == ( i*2+1 ));
			
			cobaia.next();
		}

	}
	
	@Test
	public void addTest() {
		
		constructorTest();
		
		int sizeOld = cobaia.getSize();
		
		cobaia.add(2);
		
		int sizeNew = cobaia.getSize();
		
		Assert.assertTrue( cobaia.getCurrentValue() == 2 );
		
		Assert.assertTrue( sizeOld + 1 == sizeNew );
	}
	
	@Test
	public void removeTest() {
		
		constructorTest();
		
		int sizeOld = cobaia.getSize();
		
		cobaia.removeNext();
		
		int sizeNew = cobaia.getSize();
		
		Assert.assertTrue( sizeOld == sizeNew + 1 );
	}

}
