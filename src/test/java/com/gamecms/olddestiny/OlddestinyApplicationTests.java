package com.gamecms.olddestiny;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.gamecms.olddestiny.Services.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class OlddestinyApplicationTests {

	@Autowired
	OnlyRead onlyRead;

	@Autowired
	OnlyWrite onlyWrite;


	@Test
	public void deveriaCertificarQueAPrimeiraLetraEAEspecificada(){
		String palavra = "conta";
		String resultadoEsperado = "c";
		assertEquals(resultadoEsperado, onlyRead.getInitialChar(palavra));
	}

	@Test
	public void deveriaPassarSeEUmaContaDeDestinoEtc(){
		String conta = "41dConta";
		assertEquals(Boolean.TRUE, onlyRead.isEtcAccount(conta));
	}

}
