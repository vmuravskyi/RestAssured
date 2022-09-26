package dto;


import org.joda.time.DateTime;

public class VideoGameBuilder {

    private String id;
    private String name;
    private DateTime releaseDate;
    private String reviewScore;
    private String category;
    private String rating;

    public VideoGameBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public VideoGameBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public VideoGameBuilder withReleaseDate(DateTime releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public VideoGameBuilder withReviewScore(String reviewScore) {
        this.reviewScore = reviewScore;
        return this;
    }

    public VideoGameBuilder withCategory(String category) {
        this.category = category;
        return this;
    }

    public VideoGameBuilder withRating(String rating) {
        this.rating = rating;
        return this;
    }

    public VideoGameDto build() {
        return new VideoGameDto(id, name, releaseDate, reviewScore, category, rating);
    }

}
