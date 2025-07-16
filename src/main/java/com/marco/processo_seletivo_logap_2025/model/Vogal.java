package com.marco.processo_seletivo_logap_2025.model;


public class Vogal {
    private String palavra;
    private String vogal;
    private String tempo;

    public String getPalavra() {
        return palavra;
    }

    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }

    public String getVogal() {
        return vogal;
    }

    public void setVogal(String vogal) {
        this.vogal = vogal;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public Vogal(String palavra, String vogal, String tempo) {
        this.palavra = palavra;
        this.vogal = vogal;
        this.tempo = tempo;
    }
}
