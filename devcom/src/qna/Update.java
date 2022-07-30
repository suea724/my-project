package qna;

import community.CommunityDAO;
import qna.dto.QnaViewDTO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/qna/update.do")
public class Update extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String seq = request.getParameter("seq");

        QnaDAO dao = new QnaDAO();
        QnaViewDTO dto = dao.findQuestion(seq);

        request.setAttribute("dto", dto);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/qna/update.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        
        String seq = request.getParameter("seq");
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        QnaDAO dao = new QnaDAO();
        int result = dao.updateQuestion(title, content, seq);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        if (result == 1) {
            response.sendRedirect("/devcom/qna/view.do?seq=" + seq);
        } else {
            PrintWriter writer = response.getWriter();

            writer.println("<html>");
            writer.println("<body>");
            writer.println("<script>");
            writer.println("alert('수정 실패');");
            writer.println("history.back();");
            writer.println("</script>");
            writer.println("</body>");
            writer.println("</html>");

            writer.close();
        }
    }
}
