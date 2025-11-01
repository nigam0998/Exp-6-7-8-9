import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class EmployeeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String empID = request.getParameter("empID");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "root", "password");
            
            if (empID != null && !empID.isEmpty()) {
                ps = con.prepareStatement("SELECT * FROM Employee WHERE EmpID=?");
                ps.setInt(1, Integer.parseInt(empID));
                rs = ps.executeQuery();
                if (rs.next()) {
                    out.println("<h2>Employee Details</h2>");
                    out.println("<p>ID: " + rs.getInt("EmpID") + "</p>");
                    out.println("<p>Name: " + rs.getString("Name") + "</p>");
                    out.println("<p>Salary: " + rs.getFloat("Salary") + "</p>");
                } else {
                    out.println("<h2>No Employee found for ID: " + empID + "</h2>");
                }
            } else {
                ps = con.prepareStatement("SELECT * FROM Employee");
                rs = ps.executeQuery();
                out.println("<h2>All Employees</h2>");
                out.println("<table border='1'><tr><th>ID</th><th>Name</th><th>Salary</th></tr>");
                while (rs.next()) {
                    out.println("<tr><td>" + rs.getInt("EmpID") + "</td><td>" + rs.getString("Name")
                                + "</td><td>" + rs.getFloat("Salary") + "</td></tr>");
                }
                out.println("</table>");
            }
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        } finally {
            try { rs.close(); ps.close(); con.close(); } catch (Exception ex) {}
        }
    }
}
