package com.betofroes.desafiotecnicobetofroes;

import com.betofroes.desafiotecnicobetofroes.dto.TxtDto;
import com.betofroes.desafiotecnicobetofroes.exceptions.ExecutionServiceException;
import com.betofroes.desafiotecnicobetofroes.processors.JsonProcessor;
import com.betofroes.desafiotecnicobetofroes.processors.TxtProcessor;
import com.betofroes.desafiotecnicobetofroes.service.ExecutionService;
import com.betofroes.desafiotecnicobetofroes.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

public class DesafioTecnicoBetofroesApplication {

	private static Logger logger = Logger.getLogger(DesafioTecnicoBetofroesApplication.class.getName());

	public static void main(String[] args) {

		logger.info("Starting application...");

		Properties prop = new Properties();
		try {
			prop.load(DesafioTecnicoBetofroesApplication.class.getClassLoader().getResourceAsStream("config.properties"));

			Arrays.stream(args).forEach(arg -> {
				if(arg.contains("input")) {
					prop.setProperty("input", arg.split("=")[1]);
				}
				if(arg.contains("output")) {
					prop.setProperty("output", arg.split("=")[1]);
				}
				if (arg.contains("properties_txt")) {
					prop.setProperty("properties_txt", arg.split("=")[1]);
				}
            });

			String pathInput =  prop.getProperty("input");
			String pathOutput = prop.getProperty("output");
			String properties_txt = prop.getProperty("properties_txt");

			logger.info("Input: " + pathInput);
			logger.info("Output: " + pathOutput);

			List<TxtDto> config = null;
			if (properties_txt == null || properties_txt.isEmpty() || properties_txt.contains("null")) {
				String configFile = new String(DesafioTecnicoBetofroesApplication.class.getClassLoader().getResourceAsStream("configTxtDoc.json").readAllBytes());
				logger.info("Properties_txt: " + configFile);
				config = FileUtil.readProperties(configFile);
			} else {
				config = FileUtil.readProperties(new File(properties_txt));
				logger.info("Properties_txt: " + properties_txt);
			}

			ExecutionService.getInstance(pathInput, pathOutput, new TxtProcessor(config), new JsonProcessor()).execution();

		} catch (ExecutionServiceException | IOException e){
			logger.severe("Error: " + e.getMessage());
		}
    }
}
