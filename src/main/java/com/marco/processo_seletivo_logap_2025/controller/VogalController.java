package com.marco.processo_seletivo_logap_2025.controller;

import com.marco.processo_seletivo_logap_2025.model.Vogal;
import com.marco.processo_seletivo_logap_2025.service.VogalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class VogalController {

    @Autowired
    private VogalService vogalService;

    @GetMapping("/vogal")
    public Vogal execultar(@RequestParam String palavra) {
        long inicio = System.currentTimeMillis();
        String vogal = vogalService.encontrarVogal(palavra);
        long fim = System.currentTimeMillis();
        System.out.println("Esta Rodandoooooo");

        return new Vogal(palavra, vogal, (fim - inicio) + "ms");
    }
}
