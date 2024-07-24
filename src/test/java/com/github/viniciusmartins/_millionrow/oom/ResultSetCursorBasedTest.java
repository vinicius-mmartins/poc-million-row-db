package com.github.viniciusmartins._millionrow.oom;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class ResultSetCursorBasedTest {

    @Autowired
    private ResultSetBasedImpl resultSetBasedImpl;

    @Test
    void test() {
        try {
            resultSetBasedImpl.cursorBasedResultSet();
        } catch (Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

}
