package co.com.alianza.mutant.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.com.alianza.mutant.entity.Human;

@Repository
public interface HumanRepository extends JpaRepository<Human, BigInteger> {

	Human findByDna(String dna);

	@Query(value = "SELECT COUNT(h) FROM Human h WHERE h.isMutant = true")
	Long countMutant();

	@Query(value = "SELECT COUNT(h) FROM Human h WHERE h.isMutant = false")
	Long countHuman();
}
