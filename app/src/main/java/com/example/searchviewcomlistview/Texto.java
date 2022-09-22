package com.example.searchviewcomlistview;

class Texto {
    String texto;

    @Override
    public String toString() {
        return "Texto{" +
                "texto='" + texto + '\'' +
                '}';
    }

    public Texto(String texto) {
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
