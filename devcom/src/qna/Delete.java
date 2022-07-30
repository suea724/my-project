package qna;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/qna/delete.do")
public class Delete extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String seq = request.getParameter("seq");

        QnaDAO dao = new QnaDAO();

        int result = 0;

        result += dao.deleteAnswers(seq);
        result += dao.delete(seq);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        PrintWriter writer = response.getWriter();

        if (result >= 1) {
            writer.println("<html>");
            writer.println("<body>");
            writer.println("<script>");
            writer.println("alert('삭제가 완료되었습니다.');");
            writer.println("location.href='/devcom/qna.do';");
            writer.println("</script>");
            writer.println("</body>");
            writer.println("</html>");
        } else {
            writer.println("<html>");
            writer.println("<body>");
            writer.println("<script>");
            writer.println("alert('삭제 실패');");
            writer.println("history.back();");
            writer.println("</script>");
            writer.println("</body>");
            writer.println("</html>");
        }
        writer.close();

    }

}
