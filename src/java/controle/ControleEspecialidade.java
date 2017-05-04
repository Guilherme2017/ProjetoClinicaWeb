package controle;

import dao.EspecialidadeDao;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Especialidade;
import util.Util;

@ManagedBean(name="ControleEspecialidade")
@SessionScoped
public class ControleEspecialidade implements Serializable {
   private EspecialidadeDao dao;
  private Especialidade objeto; 
  
  public ControleEspecialidade(){
      dao= new EspecialidadeDao();
  }
  
  public String listar(){
      return "/privado/especialidade/listar?facces-redirect=true";
  }
  
  public String novo(){
   objeto= new Especialidade();
   return "formulario";   
  }
  
  public String salvar(){
      if(dao.salvar(objeto)){
        Util.mensagemInformacao(dao.getMensagem());
        return "listar";
      } else{
        Util.mensagemErro(dao.getMensagem());
        return "formulario";
       }
  }
  
  public String cancelar(){
      return "listar";
    }
  
  public String editar(Integer id){
      try{
          objeto = dao.localizar(id);
          return "formulario";
      } catch(Exception e){
          Util.mensagemErro(dao.getMensagem());
          return "formulario";
      }
  }
  
  public void remover(Integer id){
      objeto= dao.localizar(id);
      if(dao.remover(objeto)){
          Util.mensagemInformacao(dao.getMensagem());
      } else{
          Util.mensagemErro(dao.getMensagem());
      }
  }

    public EspecialidadeDao getDao() {
        return dao;
    }

    public void setDao(EspecialidadeDao dao) {
        this.dao = dao;
    }

    public Especialidade getObjeto() {
        return objeto;
    }

    public void setObjeto(Especialidade objeto) {
        this.objeto = objeto;
    }
    
}
