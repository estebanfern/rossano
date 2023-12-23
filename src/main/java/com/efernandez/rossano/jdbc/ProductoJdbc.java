package com.efernandez.rossano.jdbc;

import com.efernandez.rossano.dao.Producto;
import com.efernandez.rossano.mapper.ProductoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.StringJoiner;

@Repository
public class ProductoJdbc {

    private final JdbcTemplate jdbcTemplate;
    private final Logger logger = LoggerFactory.getLogger(ProductoJdbc.class);

    @Autowired
    public ProductoJdbc(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Producto> get(Producto producto) {
        String query = "SELECT * FROM producto WHERE (";
        StringJoiner joiner = new StringJoiner(" ");
        if (producto.getCodigoBarra() != null) {
            joiner.add(String.format("codigo_barra = '%s'", producto.getCodigoBarra()));
        }
        if (producto.getCodigoInterno() != null) {
            joiner.add(String.format("codigo_interno = '%s'", producto.getCodigoInterno()));
        }
        joiner.add(")");
        query = query + joiner;
        logger.info("Executing SQL Query: {}", query);
        return jdbcTemplate.query(query, new ProductoMapper());
    }

    public Page<Producto> searchUsers(int start, int length, String codeFilter, String internalFilter, String nameFilter, String catFilter, String descFilter) {
        String query = "SELECT *, ct.code AS cat_code, ct.nombre AS cat_nombre FROM producto as prd INNER JOIN categoria AS ct ON prd.cat = ct.code WHERE 1 = 1";
        String finalQuery = " ORDER BY codigo_barra DESC LIMIT ? OFFSET ?";
        String extraParams = "";
        if (codeFilter != null && !codeFilter.isEmpty()) {
            extraParams += String.format(" AND UPPER(prd.codigo_barra) LIKE '%%%s%%'", codeFilter.toUpperCase());
        }
        if (internalFilter != null && !internalFilter.isEmpty()) {
            extraParams += String.format(" AND UPPER(prd.codigo_interno) LIKE '%%%s%%'", internalFilter.toUpperCase());
        }
        if (nameFilter != null && !nameFilter.isEmpty()) {
            extraParams += String.format(" AND UPPER(prd.nombre) LIKE '%%%s%%'", nameFilter.toUpperCase());
        }
        if (catFilter != null && !catFilter.isEmpty()) {
            extraParams += String.format(" AND prd.cat = '%s'", catFilter);
        }
        if (descFilter != null && !descFilter.isEmpty()) {
            extraParams += String.format(" AND UPPER(prd.descripcion) LIKE '%%%s%%'", descFilter.toUpperCase());
        }
        query = query + extraParams + finalQuery;
        Object[] params = {length, start};
        logger.info("Executing SQL Query: {} - PARAMS: {}", query, params);
        List<Producto> actas = jdbcTemplate.query(query, params, new ProductoMapper());
        String countSql = "SELECT COUNT(*) FROM producto AS prd WHERE 1 = 1" + extraParams;
        long totalRecords = jdbcTemplate.queryForObject(countSql, Long.class);
        return new PageImpl<>(actas, PageRequest.of(start/length, length), totalRecords);
    }
}
