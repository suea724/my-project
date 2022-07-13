package study;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/study/delete.do")
public class Delete extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String seq = req.getParameter("seq");

        StudyDAO dao = new StudyDAO();

        int result = dao.del(seq);

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter writer = resp.getWriter();

        if (result == 1) {

            writer.println("<html>");
            writer.println("<body>");
            writer.println("<script>");
            writer.println("alert('삭제가 완료되었습니다.');");
            writer.println("location.href='/devcom/study.do'");
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
