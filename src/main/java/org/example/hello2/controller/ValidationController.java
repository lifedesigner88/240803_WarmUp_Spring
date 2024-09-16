package org.example.hello2.controller;

import org.example.hello2.data.dao.group.ValidationGroup1;
import org.example.hello2.data.dao.group.ValidationGroup2;
import org.example.hello2.data.dto.ValidRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("validation")
public class ValidationController {

    private final Logger LOGGER = LoggerFactory.getLogger(ValidationController.class);

    @PostMapping("/valid")
    public ResponseEntity<String> checkValidationByValid(
            @Valid @RequestBody ValidRequestDto validRequestDto) {

        LOGGER.info(validRequestDto.toString());
        return ResponseEntity.status(HttpStatus.OK).body(validRequestDto.toString());

    }


    @PostMapping("validated")
    public ResponseEntity<String> checkValidation(
            @Validated @RequestBody ValidRequestDto validRequestDto) {

        LOGGER.info(validRequestDto.toString());
        return ResponseEntity.status(HttpStatus.OK).body(validRequestDto.toString());

    }


    @PostMapping("validated/group1")
    public ResponseEntity<String> checkValidationByGroup1(
            @Validated(ValidationGroup1.class) @Valid @RequestBody ValidRequestDto validRequestDto) {

        LOGGER.info(validRequestDto.toString());
        return ResponseEntity.status(HttpStatus.OK).body(validRequestDto.toString());
    }

    @PostMapping("validated/group2")
    public ResponseEntity<String> checkValidationByGroup2(
            @Validated(ValidationGroup2.class) @Valid @RequestBody ValidRequestDto validRequestDto) {

        LOGGER.info(validRequestDto.toString());
        return ResponseEntity.status(HttpStatus.OK).body(validRequestDto.toString());
    }

    @PostMapping("validated/all-group")
    public ResponseEntity<String> checkValidationByAllGroup(
            @Validated({
                    ValidationGroup1.class,
                    ValidationGroup2.class
            }) @Valid @RequestBody ValidRequestDto validRequestDto) {

        LOGGER.info(validRequestDto.toString());
        return ResponseEntity.status(HttpStatus.OK).body(validRequestDto.toString());

    }
}
