package com.gamecms.olddestiny;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import com.gamecms.olddestiny.Services.*;

import java.io.File;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
		System.out.println("o dir é : " + rankingTxt);
		assertNotNull(onlyRead.getRankingPlayer(path));
	}

}
