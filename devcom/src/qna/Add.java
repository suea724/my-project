package qna;

import community.CommunityDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/qna/add.do")
public class Add extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/qna/add.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        request.setCharacterEncoding("UTF-8");

        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String id = (String) session.getAttribute("auth");

        QnaDAO dao = new QnaDAO();
        int result = dao.save(title, content, id);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        PrintWriter writer = response.getWriter();

        if (result == 1) {

            String seq = dao.getMaxSeq();

            response.sendRedirect("/devcom/qna/view.do?seq=" + seq);
            writer.println("<html>");
            writer.println("<body>");
            writer.println("<script>");
            writer.printf("location.href='/devcom/qna/view.do?seq=%s';", seq);
            writer.println("</script>");
            writer.println("</body>");
            writer.println("</html>");

        } else {

            writer.println("<html>");
            writer.println("<body>");
            writer.println("<script>");
            writer.println("alert('글 등록 실패');");
            writer.println("history.back();");
            writer.println("</script>");
            writer.println("</body>");
            writer.println("</html>");

        }
        writer.close();

    }
}