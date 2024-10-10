package com.example.inicial1;

import com.example.inicial1.services.MutantServices;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Inicial1ApplicationTests {

	@Autowired
	public MutantServices servicio;

	//Aca debajo estan los test propuestos por el profe

	@Test
	public void arrayVacio(){
		String[] dna = {};
		Assumptions.assumeFalse(servicio.isMutant(dna));
	}

	@Test
	public void arrayNxM(){
		String[] dna = {
				"BBBBB",
				"BBBBB",
				"BBBBB",
				"BBBBB"
		};
		Assertions.assertThrows(IllegalArgumentException.class,()->{servicio.isMutant(dna);});
	}

	@Test
	public void arrayNumeros(){
		String[] dna = {
				"5555",
				"5555",
				"5555",
				"5555"
		};
		Assertions.assertThrows(IllegalArgumentException.class,()->{servicio.isMutant(dna);});
	}

	@Test
	public void recibirNull(){
		Assertions.assertThrows(NullPointerException.class,()->{servicio.isMutant(null);});
	}

	@Test
	public void arrayOtrasLetras(){
		String[] dna = {
				"BBBB",
				"HHHH",
				"BBBB",
				"HHHH"
		};
		Assertions.assertThrows(IllegalArgumentException.class,()->{servicio.isMutant(dna);});
	}

	@Test
	public void prueba1(){
		String[] dna = {
				"AAAA",
				"CCCC",
				"TCAG",
				"GGTC"
		};
		Assumptions.assumeTrue(servicio.isMutant(dna));
	}

	@Test
	public void prueba2(){
		String[] dna = {
				"AAAT",
				"AACC",
				"AAAC",
				"CGGG"
		};
		Assumptions.assumeFalse(servicio.isMutant(dna));
	}

	@Test
	public void prueba3(){
		String[] dna = {
				"TGAC",
				"AGCC",
				"TGAC",
				"GGTC"
		};
		Assumptions.assumeTrue(servicio.isMutant(dna));
	}

	@Test
	public void prueba4(){
		String[] dna = {
				"AAAA",
				"AAAA",
				"AAAA",
				"AAAA"
		};
		Assumptions.assumeTrue(servicio.isMutant(dna));
	}

	@Test
	public void prueba5(){
		String[] dna = {
				"TGAC",
				"ATCC",
				"TAAG",
				"GGTC"
		};
		Assumptions.assumeFalse(servicio.isMutant(dna));
	}

	@Test
	public void prueba6(){
		String[] dna = {
				"TCGGGTGAT",
				"TGATCCTTT",
				"TACGAGTGA",
				"AAATGTACG",
				"ACGAGTGCT",
				"AGACACATG",
				"GAATTCCAA",
				"ACTACGACC",
				"TGAGTATCC"
		};
		Assumptions.assumeTrue(servicio.isMutant(dna));
	}

	@Test
	public void prueba7(){
		String[] dna = {
				"TTTTTTTTT",
				"TTTTTTTTT",
				"TTTTTTTTT",
				"TTTTTTTTT",
				"CCGACCAGT",
				"GGCACTCCA",
				"AGGACACTA",
				"CAAAGGCAT",
				"GCAGTCCCC"
		};
		Assumptions.assumeTrue(servicio.isMutant(dna));
	}
}
