package FormularioRegistro;
import java.util.ArrayList;
import FormularioJava.Registrar;


public class RegistroMethodd {

    public void incluir(Registrar registrar, ArrayList<Registrar> arrayList) {
        arrayList.add(registrar);
    }

    public void listar(ArrayList<Registrar> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println(arrayList.get(i).toString());
        }
    }

    public void excluir(String id, ArrayList<Registrar> arrayList){
        for (int i = 0; i < arrayList.size(); i++) {
            //Excluindo o id selecionado
            if (id.equals(String.valueOf(i))) {
                arrayList.remove(i);
            }
            //Arrumando o ID
            if (!arrayList.isEmpty()) {
                arrayList.get(i).setID(String.valueOf(i));
            }
        }
    }
    
    public Registrar editar(String id, ArrayList<Registrar> arrayList, Registrar registrar)
    {
        for (int i = 0; i < arrayList.size(); i++) {
            if (id.equals(String.valueOf(i))) {
                arrayList.set(i, registrar);
                return arrayList.get(i);
            }
        }
        return null;
    }
}
