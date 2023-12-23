package com.efernandez.rossano.controller.api;

import com.efernandez.rossano.dao.Producto;
import com.efernandez.rossano.dto.DataTableResponse;
import com.efernandez.rossano.model.GenericException;
import com.efernandez.rossano.service.ProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productos")
public class ProductoRestController {

    private final ProductoService productoService;

    public ProductoRestController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/search")
    public DataTableResponse<Producto> searchProductos(@RequestParam("draw") int draw,
                                                       @RequestParam("start") int start,
                                                       @RequestParam("length") int length,
                                                       @RequestParam(name = "codeFilter", required = false) String codeFilter,
                                                       @RequestParam(name = "internalFilter", required = false) String internalFilter,
                                                       @RequestParam(name = "nameFilter", required = false) String nameFilter,
                                                       @RequestParam(name = "catFilter", required = false) String catFilter,
                                                       @RequestParam(name = "descFilter", required = false) String descFilter
    ) {
        return new DataTableResponse<>(draw, productoService.searchProductos(start, length, codeFilter, internalFilter, nameFilter, catFilter, descFilter));
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
        productoService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/save")
    public ResponseEntity<Producto> saveNewProducto(@RequestBody Producto producto) throws GenericException {
        return ResponseEntity.ok()
                .body(productoService.save(producto));
    }


}
