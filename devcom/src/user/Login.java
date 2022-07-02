package user;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login.do")
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/loginForm.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String id = request.getParameter("id");
        String pw = request.getParameter("pw");

        UserDAO dao = new UserDAO();

        UserDTO loginUser = dao.login(id, pw);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        if (loginUser != null) {
            session.setAttribute("auth", id);
            response.sendRedirect("/devcom/index.do");
        } else {
            PrintWriter writer = response.getWriter();

            writer.println("<html>");
            writer.println("<body>");
            writer.println("<script>");
            writer.println("alert('로그인 실패');");
            writer.println("history.back();");
            writer.println("</script>");
            writer.println("</body>");
            writer.println("</html>");

            writer.close();
        }
    }
}
