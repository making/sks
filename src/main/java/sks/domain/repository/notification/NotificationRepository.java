package sks.domain.repository.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sks.domain.model.Notification;

@Repository
@Transactional
public class NotificationRepository {
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    public Notification findOne(String cleaningUser) {
        MapSqlParameterSource param = new MapSqlParameterSource()
                .addValue("cleaningUser", cleaningUser);
        return jdbcTemplate.queryForObject("SELECT cleaning_user, notification_email FROM notification WHERE cleaning_user = :cleaningUser", param,
                (rs, i) -> new Notification(rs.getString("cleaning_user"), rs.getString("notification_email"))
        );
    }

}
