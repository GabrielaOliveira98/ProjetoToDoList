
package com.mycompany.projeto_integrador_db.persistencias;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Checkbox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;  

    private String checkbox;  
    private boolean marcada;
    
    // Relacionamento entre as tabela

    @ManyToOne
    @JoinColumn(name = "tarefa_id") 
    private TarefaU tarefa;

    public Checkbox() {
    }

    public Checkbox(int id, String checkbox, boolean marcada, TarefaU tarefa) {
       this.id = id;
       this.checkbox = checkbox;
       this.marcada = marcada;
       this.tarefa = tarefa;
    }
    
    // Getters e Setters
    public int getId() { 
        return id; 
    }
    public void setId(int id) { 
        this.id = id; 
    }

    public String getCheckbox() { 
        return checkbox; 
    }
    public void setCheckbox(String checkbox) { 
        this.checkbox = checkbox;
    }

    public boolean isMarcada() { 
        return marcada; 
    }
    public void setMarcada(boolean marcada) {
        this.marcada = marcada;
    }

    public TarefaU getTarefaU() { 
        return tarefa; 
    }
    public void setTarefaU(TarefaU tarefa) {
        this.tarefa = tarefa; 
    }
}

