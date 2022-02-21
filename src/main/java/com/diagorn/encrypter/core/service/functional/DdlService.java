package com.diagorn.encrypter.core.service.functional;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * A service that rebuilds the database on every app re-run
 *
 * @author Diagorn
 */
@Service
@Transactional
public class DdlService implements ApplicationContextAware {
    @Value("ddl.file.name")
    private String filename;

    private ApplicationContext applicationContext;
    @PersistenceContext
    private EntityManager entityManager;
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Execute commands from the SQL file on the start of the application
     */
    @PostConstruct
    public void execute() {
        try {
            //getting all the DDL
            String ddl = readSqlFile();

            //executing one statement at one time
            executeStatements(ddl);
        } catch (Exception e) {

        }
    }

    /**
     * Execute all the statements in the DDL
     *
     * @param ddl - string with statements
     */
    private void executeStatements(String ddl) {
        String[] statements = ddl.split(";");
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        for (String statement: statements) {
            try {
                tx = session.beginTransaction();
                session.doWork(connection -> connection.createStatement().execute(statement));
                tx.commit();
            } catch (Exception e) {
                logger.error("Exception while executing DDL commands. Context closing now");
                SpringApplication.exit(applicationContext, () -> 0);
            } finally {
                tx.commit();
            }
        }
    }

    /**
     * Reads all the DDL file
     *
     * @return SQL to be executed
     */
    private String readSqlFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(ResourceUtils.getFile("classpath:" + filename)))) {
            StringBuilder result = new StringBuilder();

            String line = br.readLine();
            while (line != null) {
                result.append(line);
                line = br.readLine();
            }

            return result.toString();
        } catch (Exception e) {
            logger.error("Exception while reading SQL file. Context closing now");
            SpringApplication.exit(applicationContext, () -> 0);
            return "";
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    private Session getSession() {
        return entityManager.unwrap(Session.class);
    }
}
