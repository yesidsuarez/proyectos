package co.com.alianza.mutant.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import co.com.alianza.mutant.repository.HumanRepository;

class HumanServiceImplTests {

	@InjectMocks
	private HumanServiceImpl humanServiceImpl;

	@Mock
	private HumanRepository humanRepository;

	@SuppressWarnings("deprecation")
	@BeforeEach
	void init() {
		MockitoAnnotations.initMocks(this);
	}

	String[] dna = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };

	List<String[]> getMatriz() {
		List<String[]> matriz = new ArrayList<>();

		for (String dnaFila : dna) {
			matriz.add(dnaFila.split(""));
		}

		return matriz;
	}

	@Test
	void getStatusTest() {

		when(humanRepository.countHuman()).thenReturn(100L);
		when(humanRepository.countMutant()).thenReturn(40L);

		assertNotNull(humanServiceImpl.getStatus());
	}

	@Test
	void isMutantTest() throws IOException {
		assertTrue(humanServiceImpl.isMutant(dna));
	}

	@Test
	void validateAdnTest() {
		assertTrue(humanServiceImpl.validateAdn(dna[0]));
	}

	@Test
	void validateDiagonalTest() {
		assertThat(humanServiceImpl.validateDiagonal(getMatriz())).isEqualTo(1);
	}

	@Test
	void validateHorizontalTest() {
		assertThat(humanServiceImpl.validateHorizontal(getMatriz())).isEqualTo(1);
	}

	@Test
	void validateVerticalTest() {
		assertThat(humanServiceImpl.validateVertical(getMatriz())).isEqualTo(1);
	}

}
