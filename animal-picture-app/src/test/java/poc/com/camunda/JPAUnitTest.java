package poc.com.camunda;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import poc.com.camunda.adapters.jpa.models.Picture;
import poc.com.camunda.adapters.jpa.repositories.PictureRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JPAUnitTest {

	@Autowired
	PictureRepository pictureRepo;

	@Test
	public void should_find_all_pictures() {
		List<Picture> regs = pictureRepo.findAll();
		assertFalse(regs.isEmpty());
	}

	@Test
	public void should_find_picture_by_id() {
		Picture c = new Picture();
		c = pictureRepo.save(c);
		Picture foundCourse = pictureRepo.findById(c.getId()).get();
		assertTrue(foundCourse.getId().equals(c.getId()));
	}

}
