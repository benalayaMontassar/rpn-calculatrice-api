package com.example.calculatrice.service;

import com.example.calculatrice.model.Operand;

import java.util.List;
import java.util.Optional;

public interface OperandService {
     List<Operand> findAllOperands();

     Optional<Operand> findById(String id);


}
