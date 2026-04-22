import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class SaveStudentServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.sendRedirect("index.html");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        String name = req.getParameter("name");
        String phone = req.getParameter("phone");
        String year = req.getParameter("year");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_db?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false", "root", "Santhosh&Mass49");
            PreparedStatement ps = con.prepareStatement("insert into students(name,phone,year) values(?,?,?)");
            ps.setString(1, name); ps.setString(2, phone); ps.setString(3, year);
            ps.executeUpdate();
            out.println("<h2>Student Saved Successfully</h2>");
            out.println("<p>Name: " + name + "</p>");
            out.println("<p>Phone: " + phone + "</p>");
            out.println("<p>Year: " + year + "</p>");
            out.println("<a href='index.html'>Back</a>");
            ps.close(); con.close();
        } catch (Exception e) {
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        }
    }
}
