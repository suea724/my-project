package user;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/mypage.do")
public class Mypage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String id = (String)session.getAttribute("auth");

        UserDAO dao = new UserDAO();
        UserDTO dto = dao.get(id);

        request.setAttribute("dto", dto);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/mypage.jsp");
        dispatcher.forward(request, response);
    }
}
