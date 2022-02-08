package com.qa.dogs.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.qa.dogs.domain.Dogs;
import com.qa.dogs.repo.DogRepo;

@SpringBootTest
@ActiveProfiles("test")
public class DogsServiceTest {
	
	private Dogs newDog;
	private Dogs savedDog;
	
	@Autowired
	private DogsService service;
	
	@MockBean
	private DogRepo repo;
	
	@BeforeEach
	void setUp() {
		newDog = new Dogs("Cadie", 3);
		savedDog = new Dogs(1L, "Cadie", 3);
		
		
	}
	
	@Test
	void testCreate() {
		Mockito.when(this.repo.save(newDog)).thenReturn(savedDog);
		assertThat(this.service.create(newDog)).isEqualTo(savedDog);
		Mockito.verify(this.repo, Mockito.times(1)).save(newDog);
		
	}
	
	@Test
	void testUpdate() {
		Long id = 1L;
		Dogs toUpdate = new Dogs ("Diego", 2);
		Optional<Dogs> optDog = Optional.of(new Dogs(id, null, 0));
		Dogs updated = new Dogs(id, toUpdate.getName(), toUpdate.getAge());
		
		Mockito.when(this.repo.findById(id)).thenReturn(optDog);
		Mockito.when(this.repo.save(updated)).thenReturn(updated);
		
		
		assertThat(this.service.update(id, updated)).isEqualTo(updated);
		
		Mockito.verify(this.repo, Mockito.times(1)).save(updated);
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		
	}
	
	@Test
	void testDelete() {
		Long id = 1L;
		
		Optional<Dogs> optDog = Optional.of(new Dogs (id, null, 0));
		
		Dogs deleted = optDog.get();
		
		Mockito.when(this.repo.findById(id)).thenReturn(optDog);
		
		assertThat(this.service.delete(id)).isEqualTo(deleted);
		
		Mockito.verify(this.repo, Mockito.times(1)).deleteById(id);
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		
	}
	
	
	

}
