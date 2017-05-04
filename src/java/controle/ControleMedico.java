package controle;

import dao.EspecialidadeDao;
import dao.MedicoDao;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Medico;
import util.Util;

@ManagedBean(name="ControleMedico")
@SessionScoped
public class ControleMedico implements Serializable {
   private MedicoDao dao;
   private Medico objeto;
   private EspecialidadeDao daoEspecialidade;
   
    public ControleMedico(){
      dao= new MedicoDao();
      daoEspecialidade= new EspecialidadeDao();
    }
    
    public String listar(){
      return "/privado/medico/listar?facces-redirect=true";
    }
  
    public String novo(){
        setObjeto(new Medico());
     return "formulario";   
    }
  
    public String salvar(){
       if(getDao().salvar(getObjeto())){
          Util.mensagemInformacao(getDao().getMensagem());
           return "listar";
        } else{
          Util.mensagemErro(getDao().getMensagem());
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
          Util.mensagemErro(getDao().getMensagem());
          return "formulario";
        }
    }
  
    public void remover(Integer id){
        setObjeto(getDao().localizar(id));
        if(getDao().remover(getObjeto())){
          Util.mensagemInformacao(getDao().getMensagem());
        } else{
          Util.mensagemErro(getDao().getMensagem());
        }
    }

    public MedicoDao getDao() {
        return dao;
    }

    public void setDao(MedicoDao dao) {
        this.dao = dao;
    }

    public Medico getObjeto() {
        return objeto;
    }

    public void setObjeto(Medico objeto) {
        this.objeto = objeto;
    }

    public EspecialidadeDao getDaoEspecialidade() {
        return daoEspecialidade;
    }

    public void setDaoEspecialidade(EspecialidadeDao daoEspecialidade) {
        this.daoEspecialidade = daoEspecialidade;
    }    
}
