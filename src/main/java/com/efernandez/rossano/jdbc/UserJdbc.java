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
        String query = "SELECT * FROM user_info as usr";
        String finalQuery = " JOIN rol AS rl ON usr.rol = rl.code ORDER BY name DESC LIMIT ? OFFSET ?";
        String extraParams = "";

        query = query + extraParams + finalQuery;
        Object[] params = {length, start};
        List<UserDTO> actas = jdbcTemplate.query(query, params, new UserDtoMapper());
        String countSql = "SELECT COUNT(*) FROM user_info" + extraParams;
        long totalRecords = jdbcTemplate.queryForObject(countSql, Long.class);
        return new PageImpl<>(actas, PageRequest.of(start/length, length), totalRecords);
    }
}
