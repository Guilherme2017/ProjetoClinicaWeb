package dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import jpa.EntityManagerUtil;
import modelo.Medicamento;
import util.Util;

public class MedicamentoDao implements Serializable {
  private String mensagem=" ";
  private EntityManager em;
  
  public MedicamentoDao(){
     em= EntityManagerUtil.getEntityManager();
  }
  
  public List<Medicamento> getLista(){
    return em.createQuery("from Medicamento order by nome").getResultList();
  }
  
  public boolean salvar(Medicamento obj){
       try{
          em.getTransaction().begin();
          if(obj.getId()==null){
              em.persist(obj);
          } else{
              em.merge(obj);
          }
          em.getTransaction().commit();
          mensagem = "Objeto persistido com sucesso!!";
          return true;
       } catch(Exception e){
           if(em.getTransaction().isActive()==false){
               em.getTransaction().begin();
           }
           em.getTransaction().rollback();
           mensagem= "Erro ao persistir objeto "+Util.getMensagemErro(e);
           return false;  
       }
   }
  
   public boolean remover(Medicamento obj){
       try{
          em.getTransaction().begin();
          em.remove(obj);
          em.getTransaction().commit();
          mensagem =" Removido com sucesso!";
          return true;
       }catch(Exception e){
           if(em.getTransaction().isActive()==false){
               em.getTransaction().begin();
           }
           em.getTransaction().rollback();
           mensagem= "Erro ao remover objeto "+Util.getMensagemErro(e);
           return false;  
       }
   }
  
  public Medicamento localizar(Integer id){
       return em.find(Medicamento.class,id);
   }
  
    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}
