package com.gamecms.olddestiny;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import com.gamecms.olddestiny.Services.*;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OlddestinyApplicationTests {

	@Autowired
	OnlyRead onlyRead;

	@Autowired
	OnlyWrite onlyWrite;

	@Value("${wydserver.rankingtxt}")
	String rankingTxt;




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

	@Test
	public void deveriaRetornarUmaListaDeRanking(){
		Path path = Paths.get(rankingTxt);
		assertNotNull(onlyRead.getRankingPlayer(path));
	}

	@Test
	public void deveriaVerificarSeASenhaEstaCorreta(){
		assertFalse(onlyRead.isCorrectPassword("aeli2931", "testando4").contains("Erro"));
	}


	@Test
	public void deveriaPassarSeASenhaEstaIncorreta(){
		assertTrue(onlyRead.isCorrectPassword("aelix29312", "testando4").contains("Erro"));
	}


}
