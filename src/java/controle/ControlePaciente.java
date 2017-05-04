package controle;

import dao.PacienteDao;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Paciente;
import util.Util;

@ManagedBean(name="ControlePaciente")
@SessionScoped
public class ControlePaciente implements Serializable{
    PacienteDao dao;
    Paciente objeto;
    
   public ControlePaciente(){
      dao= new PacienteDao();
   }
   
    public String listar(){
      return "/privado/paciente/listar?facces-redirect=true";
  }
  
  public String novo(){
   objeto= new Paciente();
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

    public PacienteDao getDao() {
        return dao;
    }

    public void setDao(PacienteDao dao) {
        this.dao = dao;
    }

    public Paciente getObjeto() {
        return objeto;
    }

    public void setObjeto(Paciente objeto) {
        this.objeto = objeto;
    }
    
}
