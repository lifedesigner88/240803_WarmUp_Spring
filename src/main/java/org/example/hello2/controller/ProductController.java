package org.example.hello2.controller;

import org.example.hello2.data.dto.ChangeProductNameDto;
import org.example.hello2.data.dto.ProductDto;
import org.example.hello2.data.dto.ProductResponseDto;
import org.example.hello2.service.ProductService;
import org.example.hello2.service.impl.ProductServiceImpleForTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductServiceImpleForTest productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<ProductResponseDto> getProduct(Long number) {
//    {{base_url}}/product?number=1
        return ResponseEntity.ok(
                productService.getProduct(number));
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(
            @RequestBody ProductDto productDto){
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.saveProduct(productDto));
    }

    @PutMapping
    public ResponseEntity<ProductResponseDto> changeProductName(
            @RequestBody ChangeProductNameDto changeProductNameDto) throws Exception {

        return ResponseEntity.ok(
                productService.changeProductName(
                        changeProductNameDto.getNumber(),
                        changeProductNameDto.getName()
                )
        );
    }

    @DeleteMapping("{number}")
    public ResponseEntity<String> deleteProduct(
            @PathVariable Long number) throws Exception {
        productService.deleteProduct(number);
        return ResponseEntity.ok("정상적으로 삭제되었습니다");
    }


}
