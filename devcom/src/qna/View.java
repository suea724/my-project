package qna;

import community.dto.CommunityViewDTO;
import qna.dto.AnswerDTO;
import qna.dto.QnaViewDTO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/qna/view.do")
public class View extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String seq = request.getParameter("seq");

        QnaDAO dao = new QnaDAO();

        // 한 게시물 내에서는 조회수 증가 반영 X
        if (session.getAttribute("read") == null || session.getAttribute("read").equals("n")) {
            dao.addViewCnt(seq);
        }
        session.setAttribute("read", "y");

        QnaViewDTO dto = dao.findQuestion(seq);
        ArrayList<AnswerDTO> answers = dao.findAnswers(seq);

        request.setAttribute("dto", setView(dto));
        request.setAttribute("answers", setAnswers(answers));

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/qna/view.jsp");
        dispatcher.forward(request, response);
    }

    // 답변 개행 처리
    private ArrayList<AnswerDTO> setAnswers(ArrayList<AnswerDTO> answers) {

        for (AnswerDTO answer : answers) {
            answer.setContent(answer.getContent().replace("<", "&lt;").replace(">", "&gt;"));
            answer.setContent(answer.getContent().replace("\r\n", "<br>"));
        }

        return answers;
    }

    // 질문 개행 처리 및 태그 비활성화
    private QnaViewDTO setView(QnaViewDTO dto) {
        dto.setContent(dto.getContent().replace("<", "&lt;").replace(">", "&gt;"));
        dto.setContent(dto.getContent().replace("\r\n", "<br>"));

        return dto;
    }
}
