package qna;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/qna/view/updateanswer.do")
public class UpdateAnswer extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String seq = request.getParameter("seq");

        QnaDAO dao = new QnaDAO();

        int result = dao.deleteAnswer(seq);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        PrintWriter writer = response.getWriter();

        if (result >= 1) {
            writer.printf("{\"result\" : \"%d\"}", result);
        }
        writer.close();

    }

}
