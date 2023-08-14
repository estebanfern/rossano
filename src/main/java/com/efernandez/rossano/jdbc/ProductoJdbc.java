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

@Repository
public class ProductoJdbc {

    private final JdbcTemplate jdbcTemplate;
    private final Logger logger = LoggerFactory.getLogger(ProductoJdbc.class);

    @Autowired
    public ProductoJdbc(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public Page<Producto> searchUsers(int start, int length, String codeFilter, String internalFilter, String nameFilter, String catFilter, String descFilter) {
        String query = "SELECT *, ct.code AS cat_code, ct.nombre AS cat_nombre FROM producto as prd INNER JOIN categoria AS ct ON prd.cat = ct.code WHERE 1 = 1";
        String finalQuery = " ORDER BY codigo_barra DESC LIMIT ? OFFSET ?";
        String extraParams = "";
        if (codeFilter != null && !codeFilter.isEmpty()) {
            extraParams += String.format(" AND prd.codigo_barra LIKE '%%%s%%'", codeFilter);
        }
        if (internalFilter != null && !internalFilter.isEmpty()) {
            extraParams += String.format(" AND prd.codigo_interno LIKE '%%%s%%'", internalFilter);
        }
        if (nameFilter != null && !nameFilter.isEmpty()) {
            extraParams += String.format(" AND prd.nombre LIKE '%%%s%%'", nameFilter);
        }
        if (catFilter != null && !catFilter.isEmpty()) {
            extraParams += String.format(" AND prd.cat = '%s'", catFilter);
        }
        if (descFilter != null && !descFilter.isEmpty()) {
            extraParams += String.format(" AND prd.descripcion LIKE '%%%s%%'", descFilter);
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
