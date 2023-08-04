package com.example.calculatrice.service.impl;

import com.example.calculatrice.dto.StackRpnDto;
import com.example.calculatrice.model.StackRpn;
import com.example.calculatrice.repository.StackRpnRepository;
import com.example.calculatrice.service.OperandService;
import com.example.calculatrice.service.StackRpnService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class StackRpnServiceImpl implements StackRpnService {

    private final StackRpnRepository stackRpnRepository;

    private final OperandService operandService;

    public StackRpnServiceImpl(StackRpnRepository stackRpnRepository, OperandService operandService) {
        this.stackRpnRepository = stackRpnRepository;
        this.operandService = operandService;
    }

    @Override
    public Optional<StackRpnDto> saveStack(StackRpnDto stackRpnToSave) {
        return Optional.of(mapToDto(stackRpnRepository.save(mapFromDto(stackRpnToSave))));
    }

    @Override
    public List<StackRpnDto> findAllStackRpn() {
        return stackRpnRepository.findAll().stream().map(stack -> mapToDto(stack)).collect(Collectors.toList());
    }

    @Override
    public void deleteById(long id) {
        stackRpnRepository.deleteById(id);
    }

    @Override
    public Optional<StackRpnDto> findById(long id) {
        return stackRpnRepository.findById(id).isPresent() ?
                Optional.of(mapToDto(stackRpnRepository.findById(id).get())) : Optional.empty();

    }

    @Override
    public StackRpnDto pushNewValue(long id, String value) {
        Optional<StackRpn> foundedStack = stackRpnRepository.findById(id);
        if (foundedStack.isPresent()) {
            StackRpn stackRpn = foundedStack.get();
            stackRpn.setContent(stackRpn.getContent().concat(",").concat(value));
            return mapToDto(stackRpnRepository.save(stackRpn));
        }
        return null;
    }

    @Override
    public StackRpnDto applyOperandToStack(long id, String op) {
        Optional<StackRpn> foundedStack = stackRpnRepository.findById(id);
        if (foundedStack.isPresent()) {
            StackRpn stackRpn = foundedStack.get();
            if (operandService.findById(op).isPresent()) {
                String operand = operandService.findById(op).get().getName();
                stackRpn.setContent(convertToString(pushIntoStack(stackRpn.getContent(), operand)));
                return saveStack(mapToDto(stackRpn)).get();
            }
        }
        return null;
    }

    private Stack<Float> pushIntoStack(String content, String operand) {
        Stack<Float> stack = convertToStack(content);
        Float lastElement = stack.lastElement();
        stack.pop();
        Float beforeLastElement = stack.lastElement();
        stack.pop();

        switch (operand) {
            case "+":
                stack.push(lastElement + beforeLastElement);
                break;
            case "-":
                stack.push(lastElement - beforeLastElement);
                break;
            case "*":
                stack.push(lastElement * beforeLastElement);
                break;
            case "/": {
                if (beforeLastElement != 0) {
                    stack.push(lastElement / beforeLastElement);
                } else {
                    stack.push(beforeLastElement);
                    stack.push(lastElement);
                }
                break;
            }
        }
        return stack;
    }

    private StackRpn mapFromDto(StackRpnDto stackRpnDto) {
        StackRpn stackRpn = new StackRpn();
        stackRpn.setContent(convertToString(stackRpnDto.getContent()));
        stackRpn.setName(stackRpnDto.getName());
        return stackRpn;
    }

    private StackRpnDto mapToDto(StackRpn stackRpn) {
        return new StackRpnDto(stackRpn.getId(), stackRpn.getName(), convertToStack(stackRpn.getContent()));
    }

    private Stack<Float> convertToStack(String content) {
        Stack<Float> stack = new Stack<>();
        Stream.of(content.split(","))
                .map(Float::parseFloat)
                .forEach(el -> stack.push(el));
        return stack;
    }

    private String convertToString(Stack<Float> content) {
        return StringUtils.join(content, ",");
    }
}
