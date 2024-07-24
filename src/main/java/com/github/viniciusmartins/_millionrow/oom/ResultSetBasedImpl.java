package com.github.viniciusmartins._millionrow.oom;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@Component
@Slf4j
@RequiredArgsConstructor
public class ResultSetBasedImpl {

    private final DataSource dataSource;

    /**
     * https://jdbc.postgresql.org/documentation/query/
     */
    @SneakyThrows
    public void cursorBasedResultSet() {
        Connection conn = dataSource.getConnection();
        conn.setAutoCommit(false);
        Statement st = conn.createStatement();
        st.setFetchSize(6000);
        ResultSet rs = st.executeQuery(DAO.SELECT_ALL);
        while (rs.next()) {
            log.info("processando row {}", rs.getInt("id"));
        }
        rs.close();
        st.close();
    }

}
