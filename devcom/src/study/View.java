package study;

import study.dto.StudyViewDTO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/study/view.do")
public class View extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String seq = req.getParameter("seq");

        StudyDAO dao = new StudyDAO();

        dao.addViewCnt(seq);
        StudyViewDTO dto = dao.get(seq);

        dto.setStartdate(dto.getStartdate().substring(0, 10));

        req.setAttribute("dto", dto);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/study/view.jsp");
        dispatcher.forward(req, resp);
    }

}
