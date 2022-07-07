package study;

import common.Pagination;
import study.dto.StudyListDTO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/study.do")
public class List extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int page = 1;

        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }

        StudyDAO dao = new StudyDAO();
        Pagination pagination = new Pagination(page, dao.getTotalCount(), 9, 10);

        ArrayList<StudyListDTO> list = dao.list(page);

        req.setAttribute("pagination", pagination);
         req.setAttribute("list", setList(list));

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/study/list.jsp");
        dispatcher.forward(req, resp);

    }

    private ArrayList<StudyListDTO> setList(ArrayList<StudyListDTO> list) {

        for (StudyListDTO dto : list) {

            // 제목의 길이가 20글자가 넘어가면 말줄임 표시
            if (dto.getTitle().length() > 20) {
                dto.setTitle(dto.getTitle().substring(0, 20) + "...");
            }

            dto.setStartdate(dto.getStartdate().substring(0, 10));

        }
        return list;
    }
}
