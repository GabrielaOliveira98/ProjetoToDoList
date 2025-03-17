package com.mycompany.projeto_integrador_db.persistencias;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "tarefas") // Nome da tabela no banco de dados
public class TarefaU {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String descricao;
    private boolean concluida;
    
    // Relacionamentos entre as tabelas
    
    @OneToMany(mappedBy = "tarefa")
    private List<Checkbox> checkboxes;
     
    @ManyToOne
    @JoinColumn(name = "usuario_id") 
    private Usuario usuario;
    

     // Construtor sem parâmetro
    
    public TarefaU() {
    }
    
    // Construtor com parâmetro

    public TarefaU(int id, Usuario usuario, String descricao, boolean concluida) {
        this.id = id;
        this.usuario = usuario;
        this.descricao = descricao;
        this.concluida = concluida;
    }
    
    public TarefaU(Usuario usuario) {
    }
    
    // Métodos Getter e Setter

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Checkbox> getCheckboxes() {
        return checkboxes;
    }

    public void setCheckboxes(List<Checkbox> checkboxes) {
        this.checkboxes = checkboxes;
    } 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario_id() {
        return usuario;
    }

    public void setUsuario_id(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }
    
    
    
}
