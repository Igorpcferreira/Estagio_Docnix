package Servlets;

import View.Master;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "IncluirDadosRegistroForm", urlPatterns = "/incluir")
public class IncluirDadosRegistroForm extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Master master = new Master();
        master.registrarDadosForm(req);

        resp.addHeader("Access-Control-Allow-Origin", "*");

    }
}
