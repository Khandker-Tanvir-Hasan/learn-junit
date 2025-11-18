package com.baeldung.lju;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LifecycleMethodsAndResourceHandlingUnitTest {

    final static Logger logger = LoggerFactory.getLogger(LifecycleMethodsAndResourceHandlingUnitTest.class);
    static BufferedReader fileReader;

    @BeforeAll
    static void setupUsingResource() throws Exception {
        InputStream fileStream = LifecycleMethodsAndResourceHandlingUnitTest.class.getClassLoader()
                .getResourceAsStream("file.txt");
        fileReader = new BufferedReader(new InputStreamReader(fileStream));

        logger.info("fileReader is ready: {}", fileReader.ready());
    }

    @AfterAll
     static void cleanupResource() throws Exception {
        fileReader.close();
        logger.info("fileReader is closed");
    }

    @Test
    void givenOpenResource_whenReadLines1_thenLineIsLogged() throws Exception {
        for (int i = 0; i < 3; i++) {
            logger.info(fileReader.readLine());
        }
    }

    @Test
    void givenOpenResource_whenReadLines2_thenLineIsLogged() throws Exception {

        for (int i = 0; i < 4; i++) {
            logger.info(fileReader.readLine());
        }
    }
}
