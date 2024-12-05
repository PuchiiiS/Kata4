package Software.ulpgc.kata4.control;

import Software.ulpgc.kata4.model.MovieRating;
import Software.ulpgc.kata4.view.swing.InteractiveHistogramView;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        File file = new File("C:/Users/hsuli/Desktop/Kata 2/title.ratings.tsv");
        List<MovieRating> ratings = new TsvFileRatingReader(file).read();

        HashMap<Integer, Integer> histogram = new HashMap<>();
        for(MovieRating rating : ratings){
            int roundedRating = (int) Math.floor(rating.getAverageRating());
            histogram.put(roundedRating, histogram.getOrDefault(roundedRating, 0) + 1);
        }

        InteractiveHistogramView interactiveHistogramView = new InteractiveHistogramView();
        interactiveHistogramView.renderHistogram(histogram, file);
    }
}


