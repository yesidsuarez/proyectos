package co.com.alianza.mutant.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import co.com.alianza.mutant.dto.AdnDto;
import co.com.alianza.mutant.dto.DnaDto;
import co.com.alianza.mutant.service.HumanService;

class HumanControllerTests {

	@InjectMocks
	private HumanController humanController;

	@Mock
	private HumanService humanService;

	@SuppressWarnings("deprecation")
	@BeforeEach
	void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void isMutantTestCatch() {
		assertNotNull(humanController.isMutant(null));
	}

	@Test
	void isMutantTrueTest() throws IOException {

		DnaDto dnaDto = new DnaDto();

		when(humanService.isMutant(dnaDto.getDna())).thenReturn(true);

		assertNotNull(humanController.isMutant(dnaDto));
	}

	@Test
	void isMutantFalseTest() throws IOException {

		DnaDto dnaDto = new DnaDto();

		when(humanService.isMutant(dnaDto.getDna())).thenReturn(false);

		assertNotNull(humanController.isMutant(dnaDto));
	}

	@Test
	void getStatusTest() {

		AdnDto adnDto = new AdnDto();

		when(humanService.getStatus()).thenReturn(adnDto);

		assertNotNull(humanController.getStatus());

	}
}
