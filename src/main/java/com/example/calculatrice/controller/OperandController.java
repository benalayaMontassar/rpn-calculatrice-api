package com.example.calculatrice.controller;

import com.example.calculatrice.model.Operand;
import com.example.calculatrice.service.OperandService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rpn")
@CrossOrigin(origins = "*")
public class OperandController {

    private final OperandService operandService;

    public OperandController(OperandService operandService) {
        this.operandService = operandService;
    }

    @GetMapping("/op")
    public ResponseEntity<List<Operand>> getAllOperands() {
        return new ResponseEntity<>(operandService.findAllOperands(), HttpStatus.OK);
    }
}
