package sks.domain.repository.dummy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sks.domain.model.Dummy;

@Repository
@Transactional
public class DummyRepository {
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    public Dummy findOne() {
        MapSqlParameterSource param = new MapSqlParameterSource();
        return jdbcTemplate.queryForObject(
                "SELECT sysdate AS dummy FROM dual",
                param, (rs, i) -> new Dummy(rs.getString("dummy")));
    }
}
