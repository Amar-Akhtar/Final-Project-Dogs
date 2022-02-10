package com.qa.dogs.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import nl.jqno.equalsverifier.EqualsVerifier;

@SpringBootTest
@ActiveProfiles("test")
public class DogUnitTest {

	@Test
	public void testEquals() {
		EqualsVerifier.forClass(Dogs.class).usingGetClass().verify();
	}

	@Test
	public void testConstructorWithId() {
		Dogs cadie = new Dogs(1L, "Cadie", 3);
		assertNotNull(cadie.getId());
		assertNotNull(cadie.getName());
		assertNotNull(cadie.getAge());

		assertEquals((Long) 1L, cadie.getId());
		assertEquals("Cadie", cadie.getName());
		assertEquals(3, cadie.getAge());

	}

	@Test
	public void testtoString() {
		Dogs cadie = new Dogs(1L, "Cadie", 3);
		assertEquals("Dogs [id=1, name=Cadie, age=3]", cadie.toString());
	}

}
