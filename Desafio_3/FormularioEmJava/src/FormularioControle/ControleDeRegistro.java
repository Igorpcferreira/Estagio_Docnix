package FormularioControle;
import FormularioRegistro.RegistroMethodd;
import FormularioJava.Registrar;
import java.util.ArrayList;


public class ControleDeRegistro {
    
    RegistroMethodd registroMethod = new RegistroMethodd();

    public void incluir(Registrar registrar, ArrayList<Registrar> arrayList){
        registroMethod.incluir(registrar, arrayList);
    }

    public void listar(ArrayList<Registrar> arrayList){
        registroMethod.listar(arrayList);
    }

    public void  excluir(String id, ArrayList<Registrar> arrayList)
    {
        registroMethod.excluir(id,arrayList);
    }

    public Registrar editar(String id, ArrayList<Registrar> arrayList, Registrar registrar)
    {
        return registroMethod.editar(id, arrayList, registrar);
    }

    

}
