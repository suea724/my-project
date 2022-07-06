package study;

import study.dto.StudyListDTO;
import study.dto.StudyViewDTO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/study/add.do")
public class Add extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/study/add.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();

        String id = (String)session.getAttribute("auth");

         String category = req.getParameter("category");
         String progressway = req.getParameter("progressway");
         String recruitment = req.getParameter("recruitment");
         String startdate = req.getParameter("startdate");
         String duration = req.getParameter("duration");
         String contact = req.getParameter("contact");
         String title = req.getParameter("title");
         String content = req.getParameter("content");

        StudyViewDTO dto = StudyViewDTO.builder()
                                        .category(category)
                                        .progressway(progressway)
                                        .recruitment(recruitment)
                                        .startdate(startdate)
                                        .duration(duration)
                                        .contact(contact)
                                        .title(title)
                                        .content(content)
                                        .id(id)
                                        .build();

         StudyDAO dao = new StudyDAO();
         int result = dao.add(dto);
        String maxseq = dao.getMaxSeq();

        if (result == 1) {
            resp.sendRedirect("/devcom/study/view.do?seq=" + maxseq);
        } else {
            PrintWriter writer = resp.getWriter();

            writer.println("<html>");
            writer.println("<body>");
            writer.println("<script>");
            writer.println("alert('등록 실패');");
            writer.println("history.back();");
            writer.println("</script>");
            writer.println("</body>");
            writer.println("</html>");

            writer.close();
        }

    }
}