package qna;

import qna.dto.AnswerDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/qna/view/addanswer.do")
public class AddAnswer extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        AnswerDTO dto = AnswerDTO.builder()
                                    .id(req.getParameter("id"))
                                    .qseq(req.getParameter("qseq"))
                                    .content(req.getParameter("content"))
                                    .build();

        QnaDAO dao = new QnaDAO();

        int result = dao.addAnswer(dto);
        AnswerDTO latest = dao.getLatestAnswer(dao.getLatestAnswerSeq());

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        PrintWriter writer = resp.getWriter();

        if (result == 1) {

            writer.print("{");
            writer.printf("\"name\": \"%s\",",latest.getName());
            writer.printf("\"regdate\": \"%s\",",latest.getRegdate());
            writer.printf("\"content\": \"%s\"",latest.getContent());
            writer.print("}");

            writer.close();
        }
    }
}
