
package util;

import java.lang.reflect.Method;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class DAOGenerico {

    EntityManager em;

    public Object inserir(Object obj) {
        try {
            em = Banco.getInstancia().getEm();
            em.getTransaction().begin();
            em.persist(obj);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
        return obj;
    }

    public void alterar(Object objeto) {
        em = Banco.getInstancia().getEm();
        em.getTransaction().begin();
        em.merge(objeto);
        em.getTransaction().commit();
    }
    
    public void excluir(Object objeto) {
        try {
            em = Banco.getInstancia().getEm();
            em.getTransaction().begin();
            Method getChave = objeto.getClass().getMethod("getId", new Class[0]);
            objeto = em.find(objeto.getClass(), getChave.invoke(objeto, new Object[0]));
            em.remove(objeto);
            em.getTransaction().commit();
            
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }

    public List listar(Class classe) {
        Query q = null;
        try {
            em = Banco.getInstancia().getEm();
            em.getTransaction().begin();
            q = em.createQuery("from " + classe.getSimpleName());
            em.getTransaction().commit();
        } catch (Exception e) {
           e.printStackTrace();
           em.getTransaction().rollback();
        }
        return q.getResultList();
    }

    public Object recuperaPorId(Class classe, Long id) {
        em = Banco.getInstancia().getEm();
        Object obj = null;
        em.getTransaction().begin();
        obj = em.find(classe, id);
        em.getTransaction().commit();
        return obj;
    }

    public List listarComCondicao(Class classe, String condicao) {
        em = Banco.getInstancia().getEm();
        em.getTransaction().begin();
        Query q = em.createQuery("from " + classe.getSimpleName() + " where " + condicao);
        em.getTransaction().commit();
        return q.getResultList();
    }
}

