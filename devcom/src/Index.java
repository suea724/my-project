import community.CommunityDAO;
import community.dto.CommunityListDTO;
import qna.QnaDAO;
import qna.dto.QnaListDTO;
import study.StudyDAO;
import study.dto.StudyListDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/index.do", "/"})
public class Index extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        CommunityDAO cdao = new CommunityDAO();
        StudyDAO sdao = new StudyDAO();
        QnaDAO qdao = new QnaDAO();

        ArrayList<CommunityListDTO> clist = cdao.findTen();
        ArrayList<StudyListDTO> slist = sdao.findSix();
        ArrayList<QnaListDTO> qlist = qdao.findTen();

        request.setAttribute("clist", setClist(clist));
        request.setAttribute("slist", setSlist(slist));
        request.setAttribute("qlist", setQlist(qlist));

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
        dispatcher.forward(request, response);
    }

    private ArrayList<CommunityListDTO> setClist(ArrayList<CommunityListDTO> list) {

        LocalDate today = LocalDate.now();

        for (CommunityListDTO dto : list) {

            // 오늘 작성된 글일 경우 시간 출력, 오늘 이전에 작성된 글일 경우 날짜 출력
            if (dto.getRegdate().substring(0, 10).equals(today.toString())) {
                dto.setRegdate(dto.getRegdate().substring(11));
            } else {
                dto.setRegdate(dto.getRegdate().substring(0, 10));
            }

            // 제목의 길이가 40글자가 넘어가면 말줄임 표시
            if (dto.getTitle().length() > 40) {
                dto.setTitle(dto.getTitle().substring(0, 40) + "...");
            }

        }
        return list;
    }

    private ArrayList<StudyListDTO> setSlist(ArrayList<StudyListDTO> list) {

        for (StudyListDTO dto : list) {

            // 제목의 길이가 20글자가 넘어가면 말줄임 표시
            if (dto.getTitle().length() > 20) {
                dto.setTitle(dto.getTitle().substring(0, 20) + "...");
            }

            dto.setStartdate(dto.getStartdate().substring(0, 10));

        }
        return list;
    }

    private ArrayList<QnaListDTO> setQlist(ArrayList<QnaListDTO> list) {

        LocalDate today = LocalDate.now();

        for (QnaListDTO dto : list) {

            // 오늘 작성된 글일 경우 시간 출력, 오늘 이전에 작성된 글일 경우 날짜 출력
            if (dto.getRegdate().substring(0, 10).equals(today.toString())) {
                dto.setRegdate(dto.getRegdate().substring(11));
            } else {
                dto.setRegdate(dto.getRegdate().substring(0, 10));
            }

            // 제목의 길이가 40글자가 넘어가면 말줄임 표시
            if (dto.getTitle().length() > 40) {
                dto.setTitle(dto.getTitle().substring(0, 40) + "...");
            }

        }
        return list;
    }
}
