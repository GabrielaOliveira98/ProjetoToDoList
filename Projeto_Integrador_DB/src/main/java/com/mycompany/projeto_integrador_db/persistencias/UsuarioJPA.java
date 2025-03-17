package com.mycompany.projeto_integrador_db.persistencias;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;


public class UsuarioJPA {
    
    // Método para validação e reconhecimento de usuário especifico
   
    public static Usuario validarUsuario (Usuario user) {
        EntityManager manager = JPAUtil.conectar();
        try {
            Query consulta = manager.createQuery("SELECT u FROM usuario u WHERE u.login = :login");
            consulta.setParameter("login", user.getLogin());
            List<Usuario> lista = consulta.getResultList();
            
            if(!lista.isEmpty()) {
                return lista.get(0);
            }
           
        } catch(Exception e) {
            manager.getTransaction().rollback();
        } finally {
            JPAUtil.desconectar();
        } 
        return null;
    }
    
    // Método para cadastro de novos usuarios
    
     public static void cadastrarUsuario(Usuario usuario) {
        EntityManager manager = JPAUtil.conectar();
        try {
            manager.getTransaction().begin();
            manager.persist(usuario);
            manager.getTransaction().commit();
        } catch(Exception e) {
            manager.getTransaction().rollback();
        } finally {
            JPAUtil.desconectar();
        } 
    }
     
     //Método para listar os usuarios
     
     public static List<Usuario> ListarUsuario () {
       List<Usuario> lista = new ArrayList<Usuario>();
        
        EntityManager manager = JPAUtil.conectar();
        try {
            Query consulta = manager.createQuery("SELECT u FROM Usuario u", Usuario.class);
            lista = consulta.getResultList();
        } catch(Exception e) {
            manager.getTransaction().rollback();
        } finally {
            JPAUtil.desconectar();
        } 
        return lista;
    }
     
     
     //Método para preencher a combobox da tela de login
    
     public static void preencherComboBox(JComboBox<String> comboBox) {
        List<Usuario> usuarios = UsuarioJPA.ListarUsuario();
        
        for (Usuario usuario : usuarios) {
            String user = usuario.getLogin(); 
            comboBox.addItem(user);
        }
    }
     
     //Filtro de Tarefas
     
     public static List<TarefaU> filtrarUsuario(String login){
         List<TarefaU> tarefas = new ArrayList<>();
         
         EntityManager manager = JPAUtil.conectar();
         try {
            Query consulta = manager.createQuery("SELECT descricao FROM tarefas t " +
                                                 "JOIN usuarios u ON t.usuario_id = u.id " +
                                                 "WHERE u.login = ?");
            tarefas = consulta.getResultList();
        } catch(Exception e) {
            manager.getTransaction().rollback();
        } finally {
            JPAUtil.desconectar();
        } 
        return tarefas;
        
     }
     
     
     //Método para buscar o ID do Usuario(objeto) pelo nome e transforma-lo em um inteiro

    public static int buscarId(Usuario login) {
    int idUsuario = -1; 
    EntityManager manager = JPAUtil.conectar();

    try {
        Query consulta = manager.createQuery("SELECT u.id FROM Usuario u WHERE u.login = :login");
        consulta.setParameter("login", login.getLogin()); 
        
        Object resultado = consulta.getSingleResult();
        idUsuario = ((Number) resultado).intValue(); 
    } catch (NoResultException e) {
        System.out.println("Usuário não encontrado: " + login);
    } finally {
        JPAUtil.desconectar();
    }

    return idUsuario;
}
    
    //Método para buscar o usuario(objeto) pelo nome(string)
     
     public static Usuario buscarUsuarioPorNome(String nome) {
    EntityManager manager = JPAUtil.conectar();
    try {
        Query consulta = manager.createQuery("SELECT u FROM Usuario u WHERE u.login = :login", Usuario.class);
        consulta.setParameter("login", nome);
        return (Usuario) consulta.getSingleResult(); 
    } catch (NoResultException e) {
        return null;
    } finally {
        JPAUtil.desconectar();
    }
}
     
}
         


    

