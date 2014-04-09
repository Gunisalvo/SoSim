package org.sosim.model;

import org.junit.Assert;
import org.junit.Test;

public class EntityTest {

	private Entity cobaia;
	
	@Test(expected = NullPointerException.class)
	public void emptyConstructorTest() {
		
		cobaia = new ConcreteEntity();
		
		Assert.assertNull( cobaia.getId() );
		
	}
	
	@Test
	public void integerConstructorTest() {
		
		cobaia = new ConcreteEntity(8);
		
		Assert.assertTrue( cobaia.getId() == 8 );
		
	}
	
	@Test
	public void getterSetterTest() {
		
		cobaia = new ConcreteEntity();
		
		cobaia.setId( 3 );
		
		Assert.assertTrue( cobaia.getId() == 3 );
		
	}

}
