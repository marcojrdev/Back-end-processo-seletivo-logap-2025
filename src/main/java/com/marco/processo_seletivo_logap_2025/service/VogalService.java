package com.marco.processo_seletivo_logap_2025.service;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class VogalService {

    public String encontrarVogal(String entrada) {
        Set<Character> repetidas = new HashSet<Character>();
        Set<Character> unica = new HashSet<>();

        for (char c : entrada.toLowerCase().toCharArray()) {
            if (unica.contains(c)) {
                unica.remove(c);
                repetidas.add(c);
            } else if ( !repetidas.contains(c) ) {
                unica.add(c);
            }
        }

        for (int i = 1; i < entrada.length() - 1; i++) {
            char anterior = entrada.charAt(i - 1);
            char atual = entrada.charAt(i);
            char proximo = entrada.charAt(i + 1);

            if (isVogal(anterior) && !isVogal(atual) && isVogal(proximo) ) {
                char vogal =  proximo;
                if (!repetidas.contains(Character.toLowerCase(vogal))) {
                    return String.valueOf(vogal);

                }
            }
        }
        return  null;
    }
    private boolean isVogal(char c) {
        return "aeiouAEIOU".indexOf(c) != -1;
    }
}
