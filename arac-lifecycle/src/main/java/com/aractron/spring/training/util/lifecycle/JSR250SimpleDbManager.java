package com.aractron.spring.training.util.lifecycle;

import java.io.IOException;
import java.sql.SQLException;

import javax.annotation.PostConstruct;

import org.springframework.dao.DataAccessException;

import com.aractron.spring.training.util.SimpleDbManager;

/**
 * Variation of the SimpleDbManager to illustrate JSR-250 annotations.
 * 
 * Switch the test to remove the init-method and destroy method attributes, 
 * then use this class.
 */
public class JSR250SimpleDbManager extends SimpleDbManager {

	@Override
	@PostConstruct
	public void initialize() throws DataAccessException, IOException {
		super.initialize();
	}

	@Override
	// Participants to annotate this method
	public void shutdown() throws SQLException {
		// Participants to fill this method
	}

}
