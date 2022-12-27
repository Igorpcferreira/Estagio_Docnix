package br.com.IgorFerreira.Dao;



import br.com.IgorFerreira.Dao.Generics.DaoGenericsExec;
import br.com.IgorFerreira.Model.Contato;
import br.com.IgorFerreira.Model.RegistroDataForm;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;


import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DaoRegistroForm extends DaoGenericsExec {

    public List<RegistroDataForm> listar(EntityManager entityManager) {
        return gravarFormulariosNaLista(entityManager);
    }

    public boolean verificarDuplicidadeNoArrayDeDadosFormulario(List<RegistroDataForm> listaDeDados, Integer idRegistroFormulario) {
        for (RegistroDataForm listaDeDado : listaDeDados) {
            if (listaDeDado.getIdFormulario().equals(idRegistroFormulario)) {
                return true;
            }
        }
        return false;
    }

    public List<Contato> buscarSalvarNovaListaContatosAlternativos(List<Contato> listaDeContatos, Integer idFormulario){
        List<Contato> novaListaDeContatos = new ArrayList<>();
        for (Contato listaDeContato : listaDeContatos) {
            if (listaDeContato.getId_formulario().equals(idFormulario)) {
                novaListaDeContatos.add(listaDeContato);
            }
        }
        return novaListaDeContatos;
    }

    public List<HashMap<String, Object>> consultarFormularios(EntityManager em){
        Session session = em.unwrap(Session.class);
        Criteria criteria = session.createCriteria(RegistroDataForm.class, "bean");
        criteria.createAlias("bean.lista_de_contatos", "lista_de_contatos");
        ProjectionList projectionList = Projections.projectionList();
        projectionList.add(Projections.property("bean.idFormulario").as("idFormulario"));
        projectionList.add(Projections.property("bean.nome").as("nome"));
        projectionList.add(Projections.property("bean.email").as("email"));
        projectionList.add(Projections.property("bean.cpf").as("cpf"));
        projectionList.add(Projections.property("bean.endereco").as("endereco"));
        projectionList.add(Projections.property("bean.telefone").as("telefone"));
        projectionList.add(Projections.property("bean.escolaridade").as("escolaridade"));
        projectionList.add(Projections.property("bean.genero").as("genero"));
        projectionList.add(Projections.property("bean.dataDeNascimento").as("dataDeNascimento"));


        projectionList.add(Projections.property("lista_de_contatos.IdContato").as("idContato"));
        projectionList.add(Projections.property("lista_de_contatos.nomeContato").as("nomeContato"));
        projectionList.add(Projections.property("lista_de_contatos.emailContato").as("emailContato"));
        projectionList.add(Projections.property("lista_de_contatos.telefoneContato").as("telefoneContato"));
        criteria.setProjection(projectionList);
        criteria.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
        List<HashMap<String, Object>> list = criteria.list();
        return list;
    }

    public List<RegistroDataForm> gravarFormulariosNaLista(EntityManager entityManager){
        List<RegistroDataForm> listaDeDadosFormulario = new ArrayList<>();
        List<Contato> listaDeContatosAlternativos = new ArrayList<>();
        for (HashMap<String, Object> map : consultarFormularios(entityManager)) {
            RegistroDataForm registroDataForm = new RegistroDataForm();
            registroDataForm.setIdFormulario((Integer) map.get("idFormulario"));
            Contato contato = new Contato();
            contato.setId_formulario((Integer) map.get("idFormulario"));
            contato.setIdContato((Integer) map.get("idContato"));
            contato.setNomeContato((String) map.get("nomeContato"));
            contato.setEmailContato((String) map.get("emailContato"));
            contato.setTelefoneContato((String) map.get("telefoneContato"));
            listaDeContatosAlternativos.add(contato);
            if (!verificarDuplicidadeNoArrayDeDadosFormulario(listaDeDadosFormulario, registroDataForm.getIdFormulario())) {
                registroDataForm.setNome((String) map.get("nome"));
                registroDataForm.setEmail((String) map.get("email"));
                registroDataForm.setCpf((String) map.get("cpf"));
                registroDataForm.setEndereco((String) map.get("endereco"));
                registroDataForm.setTelefone((String) map.get("telefone"));
                registroDataForm.setEscolaridade((String) map.get("escolaridade"));
                registroDataForm.setGenero((String) map.get("genero"));
                registroDataForm.setDataDeNascimento((String) map.get("dataDeNascimento"));

                listaDeDadosFormulario.add(registroDataForm);
            }
            entityManager.close();
        }
        for (RegistroDataForm registroDataForm : listaDeDadosFormulario) {
            List<Contato> novalistaDeContatos = buscarSalvarNovaListaContatosAlternativos(listaDeContatosAlternativos, registroDataForm.getIdFormulario());
            registroDataForm.setLista_de_contatos(novalistaDeContatos);
        }
        return listaDeDadosFormulario;
    }


}
