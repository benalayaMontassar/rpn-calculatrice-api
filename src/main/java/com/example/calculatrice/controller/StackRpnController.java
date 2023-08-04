package com.example.calculatrice.controller;

import com.example.calculatrice.dto.PushedNewValueBodyDto;
import com.example.calculatrice.dto.StackRpnDto;
import com.example.calculatrice.service.StackRpnService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Optional;

@RestController
@RequestMapping("/rpn")
@CrossOrigin(origins = "*")
@EnableSwagger2
public class StackRpnController {

    private final StackRpnService stackRpnService;

    public StackRpnController(StackRpnService stackRpnService) {
        this.stackRpnService = stackRpnService;
    }

    @PostMapping("/stack")
    public ResponseEntity saveStack(@RequestBody StackRpnDto stackRpnToSave) {
        Optional<StackRpnDto> savedStack = stackRpnService.saveStack(stackRpnToSave);
        return savedStack.isPresent() ? ResponseEntity.status(HttpStatus.CREATED).body(savedStack.get())
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @GetMapping("/stack")
    public ResponseEntity getAllStackRpn() {
        return ResponseEntity.status(HttpStatus.OK).body(stackRpnService.findAllStackRpn());
    }

    @DeleteMapping("/stack/{id}")
    public void deleteById(@PathVariable long id) {
        stackRpnService.deleteById(id);
    }

    @GetMapping("/stack/{id}")
    public ResponseEntity<StackRpnDto> findById(@PathVariable long id) {
      return  stackRpnService.findById(id).isPresent() ? ResponseEntity.status(HttpStatus.OK).body(stackRpnService.findById(id).get()):
              ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @PostMapping("/stack/{id}")
    public ResponseEntity<StackRpnDto> pushValueIntoStack(@PathVariable long id, @RequestBody PushedNewValueBodyDto pushedNewValueBody) {
        return stackRpnService.pushNewValue(id,pushedNewValueBody.getNewValue()) != null ? ResponseEntity.status(HttpStatus.CREATED).body(stackRpnService.pushNewValue(id,pushedNewValueBody.getNewValue())):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
    @PostMapping("/op/{op}/stack/{id}")
    public ResponseEntity<StackRpnDto> applyOperand(@PathVariable String op, @PathVariable long id) {
        return stackRpnService.applyOperandToStack(id,op) != null ? ResponseEntity.status(HttpStatus.CREATED).body(stackRpnService.applyOperandToStack(id,op)):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
