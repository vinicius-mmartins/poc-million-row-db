package com.github.viniciusmartins._millionrow.oom;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class OomTest {

    @Autowired
    private DAO dao;

    @Test
    void shouldCauseOOM() {
        try {
            dao.shouldCauseOOM();
        } catch (Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    void testJdbcStream_withoutChangingAnyParameters() {
        try {
            dao.testQueryForStream();
        } catch (Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    void testJdbcStream_configOnBeans() {
        try {
            dao.testQueryForStream_tryingToChangeConnectionCommitAndFetchSizeWithBeans();
        } catch (Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    void testJdbcVoidQueries() {
        try {
            dao.testJdbcVoidQueries();
        } catch (Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

}
