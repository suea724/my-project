package community;

import community.dto.CommunityListDTO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

@WebServlet("/community.do")
public class List extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 새로고침 시 조회수 증가를 막는 속성
        HttpSession session = request.getSession();
        session.setAttribute("read", "n");

        CommunityDAO dao = new CommunityDAO();
        ArrayList<CommunityListDTO> list = dao.findAll();

        // 오늘 작성된 글일 경우 시간 출력, 오늘 이전에 작성된 글일 경우 날짜 출력
        LocalDate today = LocalDate.now();

        for (CommunityListDTO dto : list) {
            if (dto.getRegdate().substring(0, 10).equals(today.toString())) {
                dto.setRegdate(dto.getRegdate().substring(11));
            } else {
                dto.setRegdate(dto.getRegdate().substring(0, 10));
            }
        }

        request.setAttribute("list", list);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/community/list.jsp");
        dispatcher.forward(request, response);
    }
}
