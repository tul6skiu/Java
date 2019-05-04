package app.model;

import app.entities.User;
import org.postgresql.jdbc2.optional.ConnectionPool;

import javax.sql.ConnectionPoolDataSource;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoJdbcImpl implements UsersDao{

    /* language=SQL */
    private final String SQL_SELECT_ALL =
            "SELECT * FROM data_user";

    /* language=SQL */
    private final String SQL_CELECT_BY_ID =
            "SELECT * FROM  data_user WHERE id = ?";

    /* language=SQL */
    private final String SQL_DELETE =
            "DELETE FROM data_user WHERE id = ?";

    /* language=SQL */
    private final String UPDATE_USER =
            "UPDATE data_user SET  firs_name=?, last_name=?, birthdate=? WHERE id=?";



    private Connection connection;

    public  UserDaoJdbcImpl (DataSource dataSource) {

        try {
            this.connection = dataSource.getConnection();
        }
        catch (SQLException e)
        {
            throw new IllegalStateException(e);
        }

    }



    @Override
    public List<User> findAllByFirstName(String firstName) {
        return null;
    }

    @Override
    public Optional<User> find(Integer id) {
        try
        {

            PreparedStatement statement = connection.prepareStatement(SQL_CELECT_BY_ID);
            statement.setInt(1,id);

            ResultSet resultSet = statement.executeQuery();

          if (resultSet.next()) {

              String firstName = resultSet.getString("firs_name");
              String lastName = resultSet.getString("last_name");
              Date birthDate = resultSet.getDate("birthdate");
              Optional.of(new User(id, firstName, lastName,birthDate));

          }
            return  Optional.empty();
        }
        catch (SQLException e)
        {
            throw new IllegalStateException(e);
        }
    }



    @Override
    public void save(User model) {

    }

    @Override
    public void update(User model) {
        try{
            PreparedStatement statement = connection.prepareStatement(UPDATE_USER);

            statement.setString(1, model.getFirstName());
            statement.setString(2, model.getLastName());
            statement.setDate(3,(Date.valueOf("birthdate")));
            statement.setInt(4, model.getId());


            System.out.println(model.getFirstName());
            System.out.println(model.getId());


            int rows  = statement.executeUpdate();
            statement.executeUpdate();
            System.out.printf("Updated %d rows", rows);
        }
        catch (SQLException e)
        {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void delet(Integer id) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE);
            statement.setInt(1, Integer.valueOf(id));
            statement.execute();
        }catch (SQLException e)
        {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<User> findAll() {

        try
        {
            List<User> users = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);

            while (resultSet.next())
            {
                Integer id =resultSet.getInt("id");
                String firstName = resultSet.getString("firs_name");
                String lastName = resultSet.getString("last_name");
                Date birthDate = resultSet.getDate("birthdate");


                User user = new User(id, firstName, lastName,birthDate);
                users.add(user);
            }
            return  users;
        }catch (SQLException e)
        {
            throw new IllegalStateException(e);
        }


    }
}
