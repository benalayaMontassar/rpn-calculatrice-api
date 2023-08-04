package com.example.calculatrice.service.impl;

import com.example.calculatrice.model.Operand;
import com.example.calculatrice.repository.OperandRepository;
import com.example.calculatrice.service.OperandService;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OperandServiceImpl implements OperandService {

    private final OperandRepository operandRepository;

    public OperandServiceImpl(OperandRepository operandRepository) {
        this.operandRepository = operandRepository;
    }

    @Override
    public List<Operand> findAllOperands() {
        return operandRepository.findAll();
    }

    @Override
    public Optional<Operand> findById(String id) {
        return operandRepository.findById(id);
    }
}
