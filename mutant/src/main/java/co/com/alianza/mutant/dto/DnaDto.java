package co.com.alianza.mutant.dto;

import java.io.Serializable;

public class DnaDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String[] dna;

	public String[] getDna() {
		return dna;
	}

	public void setDna(String[] dna) {
		this.dna = dna;
	}

}
