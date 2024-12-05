package Software.ulpgc.kata4.control;

import Software.ulpgc.kata4.model.MovieRating;
import Software.ulpgc.kata4.model.RatingRepository;
import Software.ulpgc.kata4.view.swing.InteractiveHistogramView;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        InteractiveHistogramView view = new InteractiveHistogramView();

        RatingRepository repository = new RatingRepository();
        List<MovieRating> ratings = repository.getAllRatings();

        HashMap<Integer, Integer> histogram = new HashMap<>();
        for(MovieRating rating : ratings){
            int roundedRating = (int) Math.floor(rating.getAverageRating());
            histogram.put(roundedRating, histogram.getOrDefault(roundedRating, 0) + 1);
        }

        view.renderHistogram(histogram);
    }
}


