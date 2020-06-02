import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CountPageServlet", urlPatterns = "/count")
public class CountPageServlet extends HttpServlet {
    static int counter = 1;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String reset = request.getParameter("reset");
        String count = String.valueOf(counter);
        if(reset == null) {
            counter++;
            out.println("<h1>Counter: " + count + "</h1>");
            out.println("Reset counter by get request <reset> = restart");
        } else if(reset.equals("restart")) {
            counter = 0;
            out.println("<h1>Counter: " + count + "</h1>");
            out.println("Reset counter by get request <reset> = restart");
        }
    }
}
