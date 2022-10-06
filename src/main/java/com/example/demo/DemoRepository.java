package com.example.demo;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DemoRepository
{

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public DemoRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate)
    {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void persist()
    {
        String sql = "INSERT INTO foo (number) VALUES (:number)";

        List<MapSqlParameterSource> params = List.of(1, 2, 3).stream().map(e ->
            {
                MapSqlParameterSource source = new MapSqlParameterSource();
                source.addValue("number", e);
                return source;
            }).collect(Collectors.toList());

        namedParameterJdbcTemplate.batchUpdate(sql, params.toArray(MapSqlParameterSource[]::new));
    }
}
