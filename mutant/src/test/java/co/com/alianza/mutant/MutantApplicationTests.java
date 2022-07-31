package co.com.alianza.mutant;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MutantApplicationTests {

	@Test
	void main() {
		MutantApplication.main(new String[] {});
		assertNotNull("1");
	}

}
