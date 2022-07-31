package co.com.alianza.mutant.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import co.com.alianza.mutant.dto.AdnDto;
import co.com.alianza.mutant.dto.DnaDto;
import co.com.alianza.mutant.service.HumanService;

@Controller
public class HumanController {

	@Autowired
	private HumanService humanService;

	@PostMapping("/mutant")
	public ResponseEntity<Object> isMutant(@RequestBody @Valid DnaDto request) {

		try {
			boolean response = humanService.isMutant(request.getDna());
			if (response) {
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/stats")
	public ResponseEntity<AdnDto> getStatus() {
		return new ResponseEntity<>(humanService.getStatus(), HttpStatus.OK);
	}

}
