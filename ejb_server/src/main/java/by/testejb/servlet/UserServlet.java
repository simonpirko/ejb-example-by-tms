package by.testejb.servlet;

import by.testejb.entity.User;
import by.testejb.service.UserEJB;
import com.google.gson.Gson;

import javax.ejb.EJB;
import javax.inject.Inject;
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

//    public static void main(String[] args) {
//            int a=2,b=3;
//            if (a++<2 && ++b>0)
//                System.out.println (a*b);//12 and 8
//            System.out.println (a+""+b);// 3 and 2 ' ' 4
//
//    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
