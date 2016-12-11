package edu.wmi.dpri.przychodnia.server.integration.rule;

import com.ibatis.common.jdbc.ScriptRunner;
import org.junit.rules.ExternalResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.Assert.fail;

/**
 * Created by lupus on 11.12.16.
 */
@Component
public class DbScriptRule extends ExternalResource {
    private static final String TEST_DATA_SQL = "test_data.sql";
    private static final String ERASE_ALL_SQL = "erase_all.sql";
    @Value("${spring.datasource.password}")
    private String databasePasword;
    @Value("${spring.datasource.url}")
    private String databaseUrl;
    @Value("${spring.datasource.username}")
    private String databaseUser;

    public void databaseConnectAndExecute(String sqlScriptInClassPath)
            throws ClassNotFoundException, SQLException {
        Connection connection = DriverManager.getConnection(databaseUrl, databaseUser,
                databasePasword);
        try {
            ScriptRunner dealsCreateScriptRunner = new ScriptRunner(connection, false, false);
            dealsCreateScriptRunner.setLogWriter(null);
            InputStream resourceAsStream = DbScriptRule.class.getResourceAsStream(sqlScriptInClassPath);
            Reader reader = new BufferedReader(new InputStreamReader(resourceAsStream));
            dealsCreateScriptRunner.runScript(reader);

        } catch (Exception e) {
            fail("Error in databaseConnectAndExecute");
        }
    }

    public void insertTestDataFromScript() {
        try {
            databaseConnectAndExecute(TEST_DATA_SQL);
        } catch (ClassNotFoundException | SQLException e) {
            fail("Error in insertTestDataFromScript");
        }
    }

    public void removeAllTestData() {
        try {
            databaseConnectAndExecute(ERASE_ALL_SQL);
        } catch (ClassNotFoundException | SQLException e) {
            fail("Error in insertTestDataFromScript");
        }
    }

    @Override
    public void before() throws Throwable {
        removeAllTestData();
        insertTestDataFromScript();
    }

    @Override
    public void after() {
        removeAllTestData();
        insertTestDataFromScript();
    }
}