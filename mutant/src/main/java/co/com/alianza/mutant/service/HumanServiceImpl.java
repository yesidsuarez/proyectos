package co.com.alianza.mutant.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.alianza.mutant.dto.AdnDto;
import co.com.alianza.mutant.entity.Human;
import co.com.alianza.mutant.repository.HumanRepository;

@Service
public class HumanServiceImpl implements HumanService {

	@Autowired
	private HumanRepository humanRepository;

	@Override
	public boolean isMutant(String[] dna) throws IOException {

		int secuenciasTotales = 0;
		boolean isMutant = false;
		String dnaCadena = Arrays.toString(dna);
		Human dnaHuman = humanRepository.findByDna(dnaCadena);

		if (null != dnaHuman) {
			return dnaHuman.isMutant();
		} else {
			List<String[]> matriz = new ArrayList<>();

			for (String dnaFila : dna) {
				if (dnaFila.length() < 4) {
					throw new IOException("La longitud de la matriz no es permitida " + dnaFila);
				} else if (!validateAdn(dnaFila)) {
					throw new IOException("El DNA ingresada no es permitida " + dnaFila);
				}

				matriz.add(dnaFila.split(""));
			}

			secuenciasTotales = validateHorizontal(matriz) + validateVertical(matriz) + validateDiagonal(matriz);
			if (secuenciasTotales >= 2) {
				isMutant = true;
			}

			Human human = new Human();
			human.setMutant(isMutant);
			human.setDna(dnaCadena);

			humanRepository.save(human);

			return isMutant;

		}

	}

	@Override
	public AdnDto getStatus() {

		double ratio = 0;

		if (humanRepository.countHuman() != 0) {
			ratio = (double) humanRepository.countMutant() / humanRepository.countHuman();
		}

		AdnDto adnDto = new AdnDto();
		adnDto.setCount_human_dna(humanRepository.countHuman());
		adnDto.setCount_mutant_dna(humanRepository.countMutant());
		adnDto.setRatio(ratio);

		return adnDto;
	}

	@Override
	public boolean validateAdn(String dna) {

		Pattern pat = Pattern.compile("[ATCG]+");
		Matcher mat = pat.matcher(dna);

		return mat.matches();
	}

	@Override
	public int validateHorizontal(List<String[]> matriz) {
		int secuencia = 0;

		for (int i = 0; i < matriz.size(); i++) {
			for (int j = 0; j < matriz.get(i).length - 4 + 1; j++) {
				if (matriz.get(i)[j].equals(matriz.get(i)[j + 1]) && matriz.get(i)[j].equals(matriz.get(i)[j + 2])
						&& matriz.get(i)[j].equals(matriz.get(i)[j + 3])) {
					secuencia++;
				}
			}
		}
		return secuencia;
	}

	@Override
	public int validateVertical(List<String[]> matriz) {
		int secuencia = 0;

		for (int i = 0; i < matriz.size(); i++) {
			for (int j = 0; j < matriz.get(i).length - 4 + 1; j++) {
				if (matriz.get(j)[i].equals(matriz.get(j + 1)[i]) && matriz.get(j)[i].equals(matriz.get(j + 2)[i])
						&& matriz.get(j)[i].equals(matriz.get(j + 3)[i])) {
					secuencia++;
				}
			}
		}
		return secuencia;
	}

	@Override
	public int validateDiagonal(List<String[]> matriz) {
		int secuencia = 0;

		for (int i = 0; i < matriz.size() - 4 + 1; i++) {
			for (int j = 0; j < matriz.get(i).length - 4 + 1; j++) {
				if (matriz.get(i)[j].equals(matriz.get(i + 1)[j + 1])
						&& matriz.get(i)[j].equals(matriz.get(i + 2)[j + 2])
						&& matriz.get(i)[j].equals(matriz.get(i + 3)[j + 3])) {
					secuencia++;
				}
			}
		}

		return secuencia;
	}

}
