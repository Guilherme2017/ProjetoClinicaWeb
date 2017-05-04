package controle;

import dao.MedicamentoDao;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Medicamento;
import util.Util;

@ManagedBean(name="ControleMedicamento")
@SessionScoped
public class ControleMedicamento implements Serializable {
   private MedicamentoDao dao;
  private Medicamento objeto; 
  
  public ControleMedicamento(){
      dao= new MedicamentoDao();
  }
  
  public String listar(){
      return "/privado/medicamento/listar?facces-redirect=true";
  }
  
  public String novo(){
   objeto= new Medicamento();
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

    public MedicamentoDao getDao() {
        return dao;
    }

    public void setDao(MedicamentoDao dao) {
        this.dao = dao;
    }

    public Medicamento getObjeto() {
        return objeto;
    }

    public void setObjeto(Medicamento objeto) {
        this.objeto = objeto;
    }
    
}
