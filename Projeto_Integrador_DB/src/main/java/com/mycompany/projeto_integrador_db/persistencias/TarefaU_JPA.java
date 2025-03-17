package com.mycompany.projeto_integrador_db.persistencias;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class TarefaU_JPA {
    
    //Método de cadastro da tarefa
    
    public static void cadastrar(TarefaU tarefaunica) {
        EntityManager manager = JPAUtil.conectar();
        try {
            manager.getTransaction().begin();
            manager.persist(tarefaunica);
            manager.getTransaction().commit();
        } catch(Exception e) {
            manager.getTransaction().rollback();
        } finally {
            JPAUtil.desconectar();
        } 
    }
    
    //Método para listar as tarefas pelo id 
    
    public static List<TarefaU> listar3(int id) {
        EntityManager manager = JPAUtil.conectar();
        List<TarefaU> lista = new ArrayList<>();

    try {
        Query consulta = manager.createQuery(
            "SELECT t FROM TarefaU t WHERE t.usuario.id = :usuario_id AND t.concluida = :concluida ", TarefaU.class);
        consulta.setParameter("usuario_id", id);
        consulta.setParameter("concluida", false);
        
        lista = consulta.getResultList();

        for (TarefaU t : lista) {
            System.out.println("ID: " + t.getId() + " | Tarefa: " + t.getDescricao() + " | Concluída: " + t.isConcluida());
        }

    } catch (Exception e) {
        e.printStackTrace(); 

    } finally {
        if (manager != null && manager.isOpen()) {
            manager.close(); 
        }
    }

    return lista;
}
    
    
    //Método retorna a lista de tarefas concluidas
    

    public static List<TarefaU> conclusao(int id) {
        EntityManager manager = JPAUtil.conectar();
        List<TarefaU> lista = new ArrayList<>();

    try {
        Query consulta = manager.createQuery(
            "SELECT t FROM TarefaU t WHERE t.usuario.id = :usuario_id AND t.concluida = :concluida ", TarefaU.class);
        consulta.setParameter("usuario_id", id);
        consulta.setParameter("concluida", true);
        
        lista = consulta.getResultList();

        for (TarefaU t : lista) {
            System.out.println("ID: " + t.getId() + " | Tarefa: " + t.getDescricao() + " | Concluída: " + t.isConcluida());
        }

    } catch (Exception e) {
        e.printStackTrace(); 

    } finally {
        if (manager != null && manager.isOpen()) {
            manager.close(); 
        }
    }

    return lista;
}
    
    //Método que marca a tarefa como concluida
    
    public static void marcarComoConcluida(int tarefaId) {
        EntityManager manager = JPAUtil.conectar(); 
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin(); 
            
            TarefaU tarefa = manager.find(TarefaU.class, tarefaId);

            if (tarefa != null) {
                
                tarefa.setConcluida(true);
                
                manager.merge(tarefa);
                transaction.commit(); 

                System.out.println("Tarefa " + tarefaId + " marcada como concluída.");
            } else {
                System.out.println("Tarefa com ID " + tarefaId + " não encontrada.");
            }

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback(); 
            }
            e.printStackTrace(); 
        } finally {
            if (manager != null && manager.isOpen()) {
                manager.close();
            }
        }
    }
    
    
    //Método para receber a quntidade máxima da barra de progresso
    
    
    public static int MaximoProgresso(int id) {
        EntityManager manager = JPAUtil.conectar();
        int totalTarefasInt = 0;
        
        try {
            Query queryTotal = manager.createQuery("SELECT COUNT(t) FROM TarefaU t WHERE t.usuario.id = :usuario_id");
            queryTotal.setParameter("usuario_id", id);
            Long totalTarefas = (Long) queryTotal.getSingleResult();
            totalTarefasInt = totalTarefas != null ? totalTarefas.intValue() : 0;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (manager != null && manager.isOpen()) {
                manager.close();
            }
        }
        return totalTarefasInt;
    }
    
    
    //Método para receber a quntidade atual da barra de progresso
    
    public static int Progresso(int id) {
        EntityManager manager = JPAUtil.conectar();
        int tarefasConcluidasInt = 0;
        
        try {
            
            Query queryConcluidas = manager.createQuery("SELECT COUNT(t) FROM TarefaU t WHERE t.concluida = true AND t.usuario.id = :usuario_id");
            queryConcluidas.setParameter("usuario_id", id);
            Long tarefasConcluidas = (Long) queryConcluidas.getSingleResult();
            tarefasConcluidasInt = tarefasConcluidas != null ? tarefasConcluidas.intValue() : 0;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (manager != null && manager.isOpen()) {
                manager.close();
            }
        }
        return tarefasConcluidasInt;
    }
    
    //Método para excluir tarefa
        
        public static void excluir (int id) {
            EntityManager manager = JPAUtil.conectar();
            try {
                manager.getTransaction().begin();
                TarefaU l = manager.find(TarefaU.class, id);
                if (l != null) {
                    manager.remove(l);
                }
                manager.getTransaction().commit();
            } catch(Exception e) {
                manager.getTransaction().rollback();
            } finally {
                JPAUtil.desconectar();
            } 
        }
        
        //Método para cadastrar checkbox a tarefa
        
        
    public static void cadastrarCheckbox(Checkbox checkbox) {
        EntityManager manager = JPAUtil.conectar();
        try {
            manager.getTransaction().begin();
            manager.persist(checkbox);
            manager.getTransaction().commit();
        } catch(Exception e) {
            manager.getTransaction().rollback();
        } finally {
            JPAUtil.desconectar();
        } 
    }
    
    //Método para buscar tarefa

    public static TarefaU buscarTarefa(int id) {
        EntityManager manager = JPAUtil.conectar();
        TarefaU tarefa = null;

    try {
        Query consulta = manager.createQuery("SELECT c FROM TarefaU c WHERE c.id = :id", TarefaU.class);
        consulta.setParameter("id", id); 
        
        tarefa = (TarefaU) consulta.getSingleResult();
        System.out.println(tarefa);
    } catch (NoResultException e) {
        System.out.println("Tarefa não encontrada: " + id);
    } catch (Exception e) {
        e.printStackTrace(); 
    } finally {
        if (manager != null && manager.isOpen()) {
            manager.close(); 
        }
    }

    return tarefa;
} 
    
    //Método para buscar todas as checkbox de uma tarefa
    
    public static List<Checkbox> buscarCheckboxesPorTarefa(int tarefaId) {
    EntityManager manager = JPAUtil.conectar();
    List<Checkbox> checkboxes = null;

    try {
        Query consulta = manager.createQuery(
            "SELECT c FROM Checkbox c WHERE c.tarefa.id = :tarefa_id", Checkbox.class
        );
        consulta.setParameter("tarefa_id", tarefaId);
        checkboxes = consulta.getResultList();
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        if (manager != null && manager.isOpen()) {
            manager.close();
        }
    }

    return checkboxes;
}
    
    //Método para buscar o id da Tarefa
    
   
    public static int buscarIdTarefa(int idPassado) {
    int idTarefa = -1; 
    EntityManager manager = JPAUtil.conectar();

    try {
        Query consulta = manager.createQuery("SELECT t.id FROM TarefaU t WHERE t.id = :id");
        consulta.setParameter("id", idPassado); 
        
        Object resultado = consulta.getSingleResult();
        idTarefa = ((Number) resultado).intValue();
        
        System.out.println("encontrada: " + idPassado);
    } catch (NoResultException e) {
        System.out.println("Tarefa nao encontrada: " + idPassado);
    } finally {
        JPAUtil.desconectar();
    }

    return idTarefa;
}
    
    
    //Método para atualizar a tarefa
    
    
    public static void atualizarTarefa(int idTarefa, String novaDescricao, boolean novaConclusao) {
        EntityManager manager = JPAUtil.conectar();

        try {
            manager.getTransaction().begin(); 
            Query consulta = manager.createQuery(
                "UPDATE TarefaU t SET t.descricao = :novaDescricao, t.concluida = :novaConclusao WHERE t.id = :id"
            );
            consulta.setParameter("novaDescricao", novaDescricao); 
            consulta.setParameter("novaConclusao", novaConclusao);
            consulta.setParameter("id", idTarefa); 

            int linhasAfetadas = consulta.executeUpdate(); 
            System.out.println("Linhas afetadas: " + linhasAfetadas);

            manager.getTransaction().commit(); 
        } catch (Exception e) {
            if (manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            JPAUtil.desconectar(); 
        }
    }
    
    //Método para conferir tarefas concluidas,usado para adicionar moedas
    
    public static boolean moedas(int idPassado) {
        boolean resultado = false;
        EntityManager manager = JPAUtil.conectar();

    try {
        Query consulta = manager.createQuery("SELECT t.concluida FROM TarefaU t WHERE t.id = :id");
        consulta.setParameter("id", idPassado); 
        
        Object obj = consulta.getSingleResult();  
        
        if (obj instanceof Boolean) {  
            resultado = (Boolean) obj;
        }
        System.out.println("encontrada: " + idPassado);
    } catch (NoResultException e) {
        System.out.println("Tarefa nao encontrada: " + idPassado);
    } finally {
        JPAUtil.desconectar();
    }
    return resultado ;
    }
    
    //Método para buscar a quantidade de checkbox pelo id de uma tarefa
    
    public static int buscarQuantidadeCheckbox(int idPassado) {
    int quantidade = -1; 
    EntityManager manager = JPAUtil.conectar();

    try {
        Query consulta = manager.createQuery("SELECT COUNT(c) FROM Checkbox c WHERE c.tarefa.id = :id");
        consulta.setParameter("id", idPassado); 
        
        Object resultado = consulta.getSingleResult();
        quantidade = ((Number) resultado).intValue();
        
        System.out.println("encontrada: " + idPassado);
    } catch (NoResultException e) {
        System.out.println("Tarefa nao encontrada: " + idPassado);
    } finally {
        JPAUtil.desconectar();
    }

    return quantidade;
}
    
    
    //Método para saber se uma checkbox esta marcada
    
    public static boolean marcadaCheckbox(int idPassado) {
     boolean resultado = false;
    EntityManager manager = JPAUtil.conectar();

    try {
        Query consulta = manager.createQuery("SELECT c.marcada FROM Checkbox c WHERE c.tarefa.id = :id");
        consulta.setParameter("id", idPassado); 
        
        Object obj = consulta.getSingleResult();  
        
        if (obj instanceof Boolean) {  
            resultado = (Boolean) obj;
        }
        System.out.println("encontrada: " + idPassado);
    } catch (NoResultException e) {
        System.out.println("Tarefa nao encontrada: " + idPassado);
    } finally {
        JPAUtil.desconectar();
    }

    return resultado ;
    }
    
    //Método para atualizar a checkbox
    
   
    public static void atualizarCheck(String nomeCheck2) {
    EntityManager manager = JPAUtil.conectar();
    EntityTransaction transaction = manager.getTransaction();

    try {
       
        if (!transaction.isActive()) {
            transaction.begin();
        }
        Query consulta = manager.createQuery(
            "UPDATE Checkbox c SET c.marcada = :atualizacao WHERE c.checkbox = :nomeCheck"
        );
        consulta.setParameter("atualizacao", true);
        consulta.setParameter("nomeCheck", nomeCheck2); 

       
        int linhasAfetadas = consulta.executeUpdate();
        
        
        System.out.println("Linhas afetadas: " + linhasAfetadas);

        
        transaction.commit();
        
    } catch (Exception e) {
        
        if (transaction.isActive()) {
            transaction.rollback();
        }
        e.printStackTrace();
    } finally {
        // Desconecta o EntityManager após a operação
        JPAUtil.desconectar();
    }
   }
    
    
}
