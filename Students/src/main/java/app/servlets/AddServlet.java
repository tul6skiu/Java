package app.servlets;

import app.entities.User;
import app.model.Model;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Properties;

@WebServlet("/add")
public class AddServlet extends HttpServlet {

    private Connection connection;

    @Override
    public void init() throws ServletException {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(getServletContext().getRealPath("/WEB-INF/classes/test-dao/db.properties")));
            String dbUrl = properties.getProperty("db.url");
            String dbUsername = properties.getProperty("db.username");
            String dbpassword = properties.getProperty("db.password");
            String driverClassname = properties.getProperty("db.driverClassName");

            Class.forName(driverClassname);
            connection = DriverManager.getConnection(dbUrl, dbUsername, dbpassword);
        }
        catch (IOException | SQLException | ClassNotFoundException e)
        {
            throw new IllegalStateException(e);
        }
    }




    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/add.jsp");
                requestDispatcher.forward(req, resp);
    }
    /*Cобираем запрос канкатинируем строки выполняем sql запрос*/
    /*разобраться в с датой в базе данных и сделать уже наконец ебанна рот*/
   @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firsName = req.getParameter("first-name");
        String lastName = req.getParameter("last-name");
       LocalDate birthDate = LocalDate.parse(req.getParameter("birthDate"));


        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO data_user(firs_name, last_name, birthdate) VALUES (?,?,?)");
            preparedStatement.setString(1, firsName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setDate(3, Date.valueOf(birthDate));
            preparedStatement.execute();

        }
        catch(SQLException e) {
            throw new IllegalStateException(e);
        }

    }
}
/*можно сломать sql инекцией*/
/*SStatement statement = connection.createStatement();
            String sqlInsert = "INSERT INTO data_user(firs_name, last_name)"+
                    "VALUES ('" + firsName + "','" + lastName + "');";
            System.out.println(sqlInsert);
            statement.execute(sqlInsert);*/