package Servlets;

import View.Master;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EditarDadosRegistroForm", urlPatterns = "/editar")
public class EditarDadosRegistroForm extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Master master = new Master();
        master.editarDadosDeRegistroFormulario(req);

        resp.addHeader("Access-Control-Allow-Origin", "*");
    }
}
