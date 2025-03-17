package com.mycompany.projeto_integrador_db.persistencias;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;

//@Entity para a representação e manipulação da tabela no banco de dados
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private int id;
    private String login;
    
    // Relacionamentos entre as tabelas
    
    @OneToMany(mappedBy = "usuario")
    private List<TarefaU> tarefas ;
    
    // Construtor sem parâmetro

    public Usuario() {
        
    }
    
    // Construtor com parâmetro

    public Usuario(int id, String login) {
        this.id = id;
        this.login = login;   
    }
    
    // Métodos Getter e Setter

    public List<TarefaU> getTarefas() {
        return tarefas;
    }

    public void setTarefas(List<TarefaU> tarefas) {
        this.tarefas = tarefas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    
}
