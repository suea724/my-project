package community;

import community.dto.CommunityListDTO;
import common.Pagination;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

@WebServlet("/community.do")
public class List extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String isSearch = request.getParameter("isSearch");

        // 새로고침 시 조회수 증가를 막는 속성
        HttpSession session = request.getSession();
        session.setAttribute("read", "n");

        CommunityDAO dao = new CommunityDAO();

        int page = 1;

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        Pagination pagination = new Pagination(page, dao.getTotalCount(), 10, 10);
        System.out.println("pagination = " + pagination);

        ArrayList<CommunityListDTO> list = null;

        if (isSearch != null && isSearch.equals("true")) { // 검색결과인 경우

            String type = request.getParameter("type");
            String keyword = request.getParameter("keyword");

            list = dao.findBySearch(type, keyword);

            request.setAttribute("isSearch", isSearch);
            request.setAttribute("type", type);
            request.setAttribute("keyword", keyword);

        } else { // 전체 리스트 출력 화면일 경우
            list = dao.list(page);
        }

        request.setAttribute("pagination", pagination);
        request.setAttribute("list", setList(list));

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/community/list.jsp");
        dispatcher.forward(request, response);
    }

    private ArrayList<CommunityListDTO> setList(ArrayList<CommunityListDTO> list) {

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
}
