package com.example.calculatrice.service;

import com.example.calculatrice.dto.StackRpnDto;
import com.example.calculatrice.model.StackRpn;

import java.util.List;
import java.util.Optional;

public interface StackRpnService {

     Optional<StackRpnDto> saveStack(StackRpnDto stackRpnToSave);

     List<StackRpnDto> findAllStackRpn();

     void deleteById(long id);

     Optional<StackRpnDto> findById(long id);

     StackRpnDto pushNewValue(long id, String value);

     StackRpnDto applyOperandToStack(long id, String op);


}
