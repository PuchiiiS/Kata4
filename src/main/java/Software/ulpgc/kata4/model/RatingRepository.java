package Software.ulpgc.kata4.model;

import Software.ulpgc.kata4.model.DatabaseConnection;
import Software.ulpgc.kata4.model.MovieRating;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RatingRepository {
    public List<MovieRating> getAllRatings(){
        List<MovieRating> ratings = new ArrayList<>();
        String sql = "SELECT id, averageRating, numVotes FROM movies";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)){

            while (resultSet.next()){
                String id = resultSet.getString("id");
                double averageRating = resultSet.getDouble("averageRating");
                int numVotes = resultSet.getInt("numVotes");

                ratings.add(new MovieRating(id, averageRating, numVotes));
            }
        }   catch (SQLException e){
            throw new RuntimeException("Error al leer los datos",e);
        }
        return  ratings;
    }
}
