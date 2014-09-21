package sks.domain.repository.cleaningtype;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sks.domain.model.CleaningType;

@Repository
@Transactional
public class CleaningTypeRepository {
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;

	public CleaningType findOne(int cleaningTypeId) {
		MapSqlParameterSource param = new MapSqlParameterSource().addValue(
				"cleaningTypeId", cleaningTypeId);
		return jdbcTemplate
				.queryForObject(
						"SELECT cleaning_type_id, cleaning_type_name, cleaning_type_cycle FROM cleaning_type WHERE cleaning_type_id = :cleaningTypeId",
						param,
						(rs, i) -> new CleaningType(rs
								.getInt("cleaning_type_id"), rs
								.getString("cleaning_type_name"), rs
								.getInt("cleaning_type_cycle")));
	}

	public List<CleaningType> findAll() {
		return jdbcTemplate
				.query("SELECT cleaning_type_id, cleaning_type_name, cleaning_type_cycle FROM cleaning_type order by cleaning_type_id",
						(rs, i) -> new CleaningType(rs
								.getInt("cleaning_type_id"), rs
								.getString("cleaning_type_name"), rs
								.getInt("cleaning_type_cycle")));

	}

}
