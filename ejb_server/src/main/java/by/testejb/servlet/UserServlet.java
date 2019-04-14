package by.testejb.servlet;

import by.testejb.entity.User;
import by.testejb.service.UserEJB;
import com.google.gson.Gson;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/newuser")
public class UserServlet extends HttpServlet {

    private static final long serialVersionUID = 7576597924521098492L;
    private Gson gson = new Gson();

    @EJB
    private UserEJB userEJB;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getUserPrincipal().getName());
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        String s = gson.toJson(userEJB.getAllUsers());
        writer.print(s);
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        User user = new User(Long.valueOf(id), name, password);
        userEJB.saveUser(user);
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
