package com.efernandez.rossano.mapper;

import com.efernandez.rossano.dao.Producto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SimpleProductoMapper implements RowMapper<Producto> {

    @Override
    public Producto mapRow(ResultSet rs, int rowNum) throws SQLException {
        Producto producto = new Producto();
        producto.setCodigoBarra(rs.getString("codigo_barra"));
        producto.setCodigoInterno(rs.getString("codigo_interno"));
        producto.setCodigoBarra(rs.getString("codigo_barra"));
        producto.setDescripcion(rs.getString("descripcion"));
        producto.setExistencia(rs.getLong("existencia"));
        producto.setNombre(rs.getString("nombre"));
        producto.setCat(rs.getString("cat"));
        producto.setPrecioCosto(rs.getLong("precio_costo"));
        producto.setPrecioFinal(rs.getLong("precio_final"));
        return producto;

    }
}
