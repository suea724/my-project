package user;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/register.do")
public class Register extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/registerForm.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String path = request.getRealPath("/pic");
        int size = 1024 * 1024 * 100;

        MultipartRequest multi = new MultipartRequest(request, path, size, "UTF-8", new DefaultFileRenamePolicy());
        String id = multi.getParameter("id");
        String pw = multi.getParameter("pw");
        String name = multi.getParameter("name");
        String pic = multi.getFilesystemName("pic");

        if (pic == null) {
            pic = "pic.png";
        }

        UserDTO dto = UserDTO.builder()
                                .id(id)
                                .pw(pw)
                                .name(name)
                                .pic(pic)
                                .build();

        UserDAO dao = new UserDAO();
        int result = dao.add(dto);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        PrintWriter writer = response.getWriter();

        if (result == 1) {
            response.sendRedirect("/devcom/login.do");
        } else {
            writer.println("<html>");
            writer.println("<body>");
            writer.println("<script>");
            writer.println("alert('회원가입 실패');");
            writer.println("history.back();");
            writer.println("</script>");
            writer.println("</body>");
            writer.println("</html>");
        }

        writer.close();
    }
}
