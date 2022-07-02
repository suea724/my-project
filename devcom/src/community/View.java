package community;

import community.dto.CommunityViewDTO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/community/view.do")
public class View extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String seq = request.getParameter("seq");

        CommunityDAO dao = new CommunityDAO();

        // 한 게시물 내에서는 조회수 증가 반영 X
        if (session.getAttribute("read") == null || session.getAttribute("read").equals("n")) {
            dao.addViewCnt(seq);
        }
        session.setAttribute("read", "y");

        // 개행 처리
        CommunityViewDTO dto = dao.find(seq);
        dto.setContent(dto.getContent().replace("\r\n", "<br>"));

        request.setAttribute("dto", dto);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/community/view.jsp");
        dispatcher.forward(request, response);
    }
}
