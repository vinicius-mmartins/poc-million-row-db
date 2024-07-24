package com.github.viniciusmartins._millionrow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void run(String... args) throws Exception {

		jdbcTemplate.execute("""
				create table if not exists million_row (
				    id int4 not null primary key,
				    coluna_um varchar(255),
				    coluna_dois varchar(255),
				    coluna_tres varchar(255),
				    coluna_quatro int4
				)
				""");

		Integer c = jdbcTemplate.queryForObject("select count(1) from million_row", Integer.class);

		if (c == 0) {
			for (int i=0; i<999999; i++) {
				jdbcTemplate.execute(String.format("""
						insert into million_row (id, coluna_um, coluna_dois, coluna_tres, coluna_quatro)
						values (
							%s,
							'colunas pra pesar o bean',
							'colunas pra pesar o bean',
							'colunas pra pesar o bean',
							999999
						)
						""", i));
			}
		}

	}
}
