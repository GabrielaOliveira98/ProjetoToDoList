package com.mycompany.projeto_integrador_db.persistencias;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
    
    private static String PERSISTENCE_UNIT = "Projeto_Integrador";
    private static EntityManager manager;
    private static EntityManagerFactory factory;
    
    // Função para conectar ao servidor SQL
    
    public static EntityManager conectar() {
        if(factory == null || !factory.isOpen()) {
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        }
        
        if(manager == null || !manager.isOpen()) {
            manager = factory.createEntityManager();
        }
        
        return manager;
    }
    
     // Função para desconectar servidor SQL
    
    public static void desconectar() {
        if(manager.isOpen() && manager != null) {
            manager.close();
            factory.close();
        }
    }
}
