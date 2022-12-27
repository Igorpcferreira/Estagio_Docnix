package Servlets;
import View.Master;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


    @WebServlet(name = "ExcluirDadosRegistroForm", urlPatterns = "/excluir")
    public class ExcluirDadosRegistroForm extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            Master master = new Master();
            master.excluirDadosDeRegistroFormulario(req.getParameter("id"));

            resp.addHeader("Access-Control-Allow-Origin", "*");
        }
    }