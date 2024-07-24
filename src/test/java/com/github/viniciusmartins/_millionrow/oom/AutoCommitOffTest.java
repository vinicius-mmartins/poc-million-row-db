package com.github.viniciusmartins._millionrow.oom;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * <p>Desativar autocommit mantem cursor aberto possibilitando lazy loading no Java Stream.</p>
 * <p>Acredito que do contrario ele acaba trazendo tudo pra memória antes de fechar o cursor.</p>
 * <p>Fetch size define quantos objetos vem para a memória por round trip até a base</p>
 */
@SpringBootTest(properties = {
        "spring.datasource.hikari.auto-commit=false",
        "spring.jdbc.template.fetch-size=6000"
})
public class AutoCommitOffTest {

    @Autowired
    private DAO dao;

    @Test
    void testJdbcStream() {
        try {
            dao.testQueryForStream();
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
