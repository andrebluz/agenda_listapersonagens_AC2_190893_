package com.example.agenda_listapersonagens_ac2_190893.dao;

import com.example.agenda_listapersonagens_ac2_190893.model.Personagem;

import java.util.ArrayList;
import java.util.List;



public class PersonagemDAO {

    private final static List<Personagem> personagens = new ArrayList<>();
    private static int contadorDeId = 1;

    //método salvar dentro do dao
    public void salvar(Personagem personagemSalvo) {
        //adiciona dados personagemSalvo ao dao
        personagens.add(personagemSalvo);
        personagemSalvo.setId(contadorDeId);

        //atualiza id
        contadorDeId++;
    }

    //envia para ListaPersonagemActivity para printar no XML
    public List<Personagem> todos(){

        return new ArrayList<>(personagens);
    }

    //editar dados do dao 1
    public void editar(Personagem personagem)
    {
        Personagem personagemEscolhido = BuscaPersonagemId(personagem);
        if (personagemEscolhido != null) {
            int posicaoPersonagem = personagens.indexOf(personagemEscolhido);
            personagens.set(posicaoPersonagem, personagem);
        }
    }

    //editar dados do dao 2
    public void remover(Personagem name){
        personagens.remove(name);
    }

    private Personagem BuscaPersonagemId(Personagem personagem) {

        for (Personagem p : personagens) {
            if (p.getId() == personagem.getId()) {
                return p;
            }
        }
        return null;
    }
}
