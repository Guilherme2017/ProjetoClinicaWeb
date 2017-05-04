package util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Util {
   public static String getMensagemErro(Exception e){
        while (e.getCause() != null){
            e = (Exception) e.getCause();
         
        }String retorno = e.getMessage();
        if(retorno.contains("viola restrição de chave estrangeira")){
            retorno = "registro não pode ser removido por possuir referencia em outros objetos.";
        }
        return retorno;
    }
    
    public static void mensagemInformacao(String mensagem){
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public static void mensagemErro(String mensagem){
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }  
}
