package br.college.ufu.patternrecognitionwork;

import br.college.ufu.patternrecognitionwork.starter.main.MainMenu;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebStarter {

	public static void main(String[] args) {
		new MainMenu()
				.execute();
	}

}
