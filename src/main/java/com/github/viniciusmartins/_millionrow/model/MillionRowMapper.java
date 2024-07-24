package com.github.viniciusmartins._millionrow.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MillionRowMapper implements RowMapper<MillionRow> {

    @Override
    public MillionRow mapRow(ResultSet rs, int rowNum) throws SQLException {
        return MillionRow.builder()
                .id(rs.getInt("id"))
                .colunaUm(rs.getString("coluna_um"))
                .colunaDois(rs.getString("coluna_dois"))
                .colunaTres(rs.getString("coluna_tres"))
                .colunaQuatro(rs.getInt("coluna_quatro"))
                .build();
    }
}
