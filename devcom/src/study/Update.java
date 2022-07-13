package study;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import study.dto.StudyViewDTO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/study/update.do")
public class Update extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String seq = req.getParameter("seq");

        StudyDAO dao = new StudyDAO();
        StudyViewDTO dto = dao.get(seq);

        // 시간을 제외한 날짜만
        dto.setStartdate(dto.getStartdate().substring(0, 10));

        req.setAttribute("dto", dto);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/study/update.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String seq = req.getParameter("seq");
        String category = req.getParameter("category");
        String progressway = req.getParameter("progressway");
        String recruitment = req.getParameter("recruitment");
        String startdate = req.getParameter("startdate");
        String contact = req.getParameter("contact");
        String title = req.getParameter("title");
        String content = req.getParameter("content");

        StudyViewDTO dto = StudyViewDTO.builder()
                .seq(seq)
                .category(category)
                .progressway(progressway)
                .recruitment(recruitment)
                .startdate(startdate)
                .contact(contact)
                .title(title)
                .content(content)
                .build();

        System.out.println("dto = " + dto);

        StudyDAO dao = new StudyDAO();
        int result = dao.update(dto);

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter writer = resp.getWriter();

        if (result == 1) {

            writer.println("<html>");
            writer.println("<body>");
            writer.println("<script>");
            writer.println("alert('수정이 완료되었습니다.');");
            writer.printf("location.href='/devcom/study/view.do?seq=%s'", seq);
            writer.println("</script>");
            writer.println("</body>");
            writer.println("</html>");

        } else {
            writer.println("<html>");
            writer.println("<body>");
            writer.println("<script>");
            writer.println("alert('수정 실패.');");
            writer.println("history.back();");
            writer.println("</script>");
            writer.println("</body>");
            writer.println("</html>");

        }
        writer.close();

    }
}
