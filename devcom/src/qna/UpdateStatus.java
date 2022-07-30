package qna;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/qna/view/updatestatus.do")
public class UpdateStatus extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String qseq = req.getParameter("qseq");
        String status = req.getParameter("status");

        QnaDAO dao = new QnaDAO();

        int result = dao.updateSolved(qseq);

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        PrintWriter writer = resp.getWriter();

        if (result == 1) {

            writer.print("{");
            writer.printf("\"result\": \"%d\"",result);
            writer.print("}");

            writer.close();
        }
    }
}
