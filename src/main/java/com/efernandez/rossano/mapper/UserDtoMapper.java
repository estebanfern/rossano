package com.efernandez.rossano.mapper;

import com.efernandez.rossano.dto.UserDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDtoMapper implements RowMapper<UserDTO> {

    @Override
    public UserDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(rs.getLong("id"));
        userDTO.setName(rs.getString("name"));
        userDTO.setRol(rs.getString("rol"));
        userDTO.setRolName(rs.getString("nombre"));
        userDTO.setEmail(rs.getString("email"));
        userDTO.setDocumento(rs.getString("documento"));
        return userDTO;
    }

}
