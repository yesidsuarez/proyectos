package co.com.alianza.mutant.service;

import java.io.IOException;
import java.util.List;

import co.com.alianza.mutant.dto.AdnDto;

public interface HumanService {

	boolean isMutant(String[] dna) throws IOException;

	AdnDto getStatus();

	boolean validateAdn(String dna);

	int validateHorizontal(List<String[]> matriz);

	int validateVertical(List<String[]> matriz);

	int validateDiagonal(List<String[]> matriz);
}
