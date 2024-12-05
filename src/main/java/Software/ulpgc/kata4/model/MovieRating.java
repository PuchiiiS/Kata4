package Software.ulpgc.kata4.model;

public class MovieRating {
        private final String id;
        private final double averageRating;
        private final int numVotes;

    public MovieRating(String id, double averageRating, int numVotes) {
        this.id = id;
        this.averageRating = averageRating;
        this.numVotes = numVotes;
    }

    public String getId() {
        return id;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public int getNumVotes() {
        return numVotes;
    }
}
