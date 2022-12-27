package Servlets;

import Model.RegistroDataForm;
import View.Master;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ListarDados", urlPatterns = "/listar")
public class ListarDadosRegistroForm extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Master master = new Master();
        List<RegistroDataForm> listaDeFormulario = master.listarDadosDeRegistroFormulario();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter pw = resp.getWriter();
        RegistroDataForm registroDataForm;

        JSONArray jsonArray = new JSONArray();
        resp.addHeader("Access-Control-Allow-Origin", "*");

        for(int i =0; i<listaDeFormulario.size();i++){
            registroDataForm = listaDeFormulario.get(i);
            JSONObject json = new JSONObject(registroDataForm);
            jsonArray.put(json);
        }

        pw.print(jsonArray);
        pw.flush();


    }
}
