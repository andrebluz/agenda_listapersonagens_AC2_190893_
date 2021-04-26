package com.example.agenda_listapersonagens_ac2_190893.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Personagem implements Serializable {
    private int id = 0;

    //variaveis privadas para o construtor
    private String nome;
    private String nascimento;
    private String altura;

    //método construtor
   public Personagem(String nome, String nascimento, String altura) {
        this.nome = nome;
        this.nascimento = nascimento;
        this.altura = altura;
    }

    public Personagem(){

    }


    @NonNull
    @Override
    //exibir como string o nome na tela de listagem
    public String toString()
    {
        return nome;
    }

    //GET
    public String getNome() {
        return nome;
    }

    public String getNascimento() {
        return nascimento;
    }

    public String getAltura() {
        return altura;
    }

    public int getId(){
       return id;
    }

    //SET
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public void setId(int contadorDeId){
       this.id = id;
    }

    //BOOL
    public boolean idValido(){
       return id > 0;
    }

}
