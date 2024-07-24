package com.github.viniciusmartins._millionrow.oom;

import com.github.viniciusmartins._millionrow.model.MillionRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class DAO {

    public static final String SELECT_ALL = "select * from million_row";

    private final NamedParameterJdbcTemplate namedJdbcTemplate;
    private final DataSource dataSource;

    // abordagem "inocente": trazer a tabela inteira pra memoria
    public void shouldCauseOOM() {
        namedJdbcTemplate.queryForList(SELECT_ALL, Map.of());
    }

    // Java Streams são por default 'Lazy Loading', e pra algumas impl não manteria todos itens em memoria,
    // mas só ela não basta pra prevenir OOM
    // dependendo da base algumas outras configurações são necessárias
    // esse mesmo método vai falhar ou funcionar dependendo da config
    public void testQueryForStream() {
        namedJdbcTemplate.queryForStream(SELECT_ALL, new MapSqlParameterSource(), new MillionRowMapper())
                .forEach(row -> log.info("processando row {}", row.id()));
    }

    @SneakyThrows
    public void testQueryForStream_tryingToChangeConnectionCommitAndFetchSizeWithBeans() {
        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);
        JdbcTemplate jdbc = new JdbcTemplate(dataSource);
        jdbc.setFetchSize(6000);
        jdbc.queryForStream(SELECT_ALL, new MillionRowMapper())
                .forEach(row -> log.info("processando row {}", row.id()));
    }

    // também funciona mas só sem autocommit
    public void testJdbcVoidQueries() {
        namedJdbcTemplate.query(SELECT_ALL,
                rs -> {
                    rs.setFetchSize(5000);
                    int id = rs.getInt("id");
                    log.info("processando row {}", id);
                }
        );
    }

}
