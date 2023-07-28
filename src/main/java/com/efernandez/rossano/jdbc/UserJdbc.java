package com.efernandez.rossano.jdbc;

import com.efernandez.rossano.dto.UserDTO;
import com.efernandez.rossano.mapper.UserDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserJdbc {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserJdbc(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Page<UserDTO> searchUsers(int start, int length, String nameIpt, String emailIpt, String rolIpt, String documentoIpt) {
        String query = "SELECT * FROM user_info as usr JOIN rol AS rl ON usr.rol = rl.code WHERE 1 = 1";
        String finalQuery = " ORDER BY name DESC LIMIT ? OFFSET ?";
        String extraParams = "";
        if (nameIpt != null && !nameIpt.isEmpty()) {
            extraParams += String.format(" AND usr.name LIKE '%%%s%%'", nameIpt);
        }
        if (emailIpt != null && !emailIpt.isEmpty()) {
            extraParams += String.format(" AND usr.email LIKE '%%%s%%'", emailIpt);
        }
        if (rolIpt != null && !rolIpt.isEmpty()) {
            extraParams += String.format(" AND usr.rol = '%s'", rolIpt);
        }
        if (documentoIpt != null && !documentoIpt.isEmpty()) {
            extraParams += String.format(" AND usr.documento = '%s'", documentoIpt);
        }
        query = query + extraParams + finalQuery;
        Object[] params = {length, start};
        List<UserDTO> actas = jdbcTemplate.query(query, params, new UserDtoMapper());
        String countSql = "SELECT COUNT(*) FROM user_info AS usr WHERE 1 = 1" + extraParams;
        long totalRecords = jdbcTemplate.queryForObject(countSql, Long.class);
        return new PageImpl<>(actas, PageRequest.of(start/length, length), totalRecords);
    }
}
