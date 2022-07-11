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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/study/add.do")
public class Add extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        StudyDAO dao = new StudyDAO();

        req.setAttribute("langs", dao.findAllLangs());

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

         String stackStr = req.getParameter("stack");

         // JSON Simple 라이브러리 사용해서 태그 처리
        ArrayList<String> langs = new ArrayList<>();

        if (!stackStr.equals("") && stackStr != null) {
            JSONParser parser = new JSONParser();

            try {
                JSONArray array = (JSONArray) parser.parse(stackStr);

                for (Object obj : array) {

                    JSONObject value = (JSONObject) obj;
                    langs.add((String) value.get("value"));
                }

            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        StudyDAO dao = new StudyDAO();

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
                                        .langs(langs)
                                        .build();

        // 스터디 글 추가
         int result = dao.add(dto);

        // 기존 테이블에 존재하지 않는 언어 추가
        ArrayList<String> allLangs = dao.findAllLangs();

        for (String lang : langs) {
            if (!allLangs.contains(lang)) {
                dao.addLangs(lang);
            }
        }

        // 방금 추가된 글의 번호
        String maxseq = dao.getMaxSeq();
        // 글과 해시태그 연결 테이블에 각 seq 추가
        dao.addStudyLang(maxseq, langs);

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