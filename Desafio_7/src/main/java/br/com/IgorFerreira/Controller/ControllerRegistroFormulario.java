package br.com.IgorFerreira.Controller;

import br.com.IgorFerreira.Dao.DaoRegistroForm;
import br.com.IgorFerreira.Model.RegistroDataForm;
import br.com.IgorFerreira.Util.JpaUtil;
import br.com.IgorFerreira.Util.Util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/RegistroFormulario")
public class ControllerRegistroFormulario {
    EntityManagerFactory em = Persistence.createEntityManagerFactory("FormularioJava_Jersey");
    DaoRegistroForm daoRegistroForm = new DaoRegistroForm();
    Util util = new Util();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/incluir")
    public Response incluir(String stringJsonRegistroFormulario) {

        Response response = null;

        daoRegistroForm.incluir(util.registrarDadosFormulario(stringJsonRegistroFormulario));

        try {
            response = Response.status(Response.Status.OK).build();
        } catch (Exception e){
            System.out.println(e);
        }
        return response;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listar")
    public List<RegistroDataForm> listar() {

        return daoRegistroForm.listar(JpaUtil.getEntityManager(em));

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/editar")
    public void editar(String stringJsonRegistroFormulario) {

        try {

            daoRegistroForm.editar(util.registrarDadosFormulario(stringJsonRegistroFormulario));

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/excluir")
    public void excluir(@QueryParam("id") int id) {

        RegistroDataForm registroDataForm = new RegistroDataForm();

        try{

            registroDataForm.setIdFormulario(id);
            daoRegistroForm.excluir(id, registroDataForm);

        } catch (Exception e){
            System.out.println(e);
        }

    }

}
