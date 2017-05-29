package com.aractron.spring.training.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Simple database manager utility for testing with local databases.
 * 
 * @author aaron.levensailor
 */
public class SimpleDbManager {
	
	/**
	 * Class Logger instance 
	 */
	private static Log log = LogFactory.getLog(SimpleDbManager.class);

	/**
	 * Class instance of the DataSource interface that represents a DB
	 */
	private DataSource dataSource;
	
	/**
	 * DB name
	 */
	private String dbName = "test";
	
	/**
	 * The Data Definition Language file that contains the DB's schema definitions
	 */
	private Resource ddl;
	
	/**
	 * The file that contains the data definition for the Db tables
	 */
	private Resource sql;

	/**
	 * Return the {@link DataSource} that provides connectivity to the database.
	 * 
	 * @return null by default
	 */
	public DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * Return the reference name for the database.
	 * 
	 * @return 'test' by default
	 */
	public String getDbName() {
		return dbName;
	}

	/**
	 * Retrieve the {@link Resource} containing table definitions and
	 * constraints.
	 * 
	 * @return null by default
	 */
	public Resource getDdl() {
		return ddl;
	}

	/**
	 * Retrieve the {@link Resource} containing data updates (inserts and
	 * updates).
	 * 
	 * @return null by default
	 */
	public Resource getSql() {
		return sql;
	}

	/**
	 * This initialize method is explicitly written in a non-Spring fashion to
	 * demonstrate the init-method attribute of bean definitions.
	 * 
	 * @throws DataAccessException
	 * @throws IOException
	 */
	public void initialize() throws DataAccessException, IOException {
		log.info("Initializing database '" + getDbName() + "'");
		JdbcTemplate jt = new JdbcTemplate(dataSource);
		jt.execute(read(ddl));
		jt.execute(read(sql));
	}

	/**
	 * Method that closes the connection to the database.
	 * 
	 * @throws SQLException
	 */
	public void shutdown() throws SQLException {
		log.info("Shutting down database '" + getDbName() + "'");
		if (dataSource == null) {
			dataSource.getConnection().close();
		}
	}

	/**
	 * Utility method to read the SQL and DDL files.
	 * 
	 * @param resource
	 * @return string contents of the Resource file passed in
	 * @throws IOException
	 */
	private String read(Resource resource) throws IOException {
		InputStream is = null;
		StringWriter sw = new StringWriter();
		try {
			is = resource.getInputStream();
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(is));
			BufferedWriter writer = new BufferedWriter(sw);
			for (int c = reader.read(); c != -1; c = reader.read()) {
				writer.write(c);
			}
			writer.flush();
		} finally {
			if (is != null) {
				is.close();
			}
		}
		return sw.toString();
	}

	/**
	 * Setter for dataSource.
	 * 
	 * @param dataSource The dataSource to set.
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * Setter for dbName.
	 * 
	 * @param dbName The dbName to set.
	 */
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	/**
	 * Setter for ddl.
	 * 
	 * @param ddl The ddl to set.
	 */
	public void setDdl(Resource ddl) {
		this.ddl = ddl;
	}

	/**
	 * Setter for sql.
	 * 
	 * @param sql The sql to set.
	 */
	public void setSql(Resource sql) {
		this.sql = sql;
	}
}
