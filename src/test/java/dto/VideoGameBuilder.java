package dto;


import org.joda.time.DateTime;

public class VideoGameBuilder {

    private String id;
    private String name;
    private DateTime releaseDate;
    private String reviewScore;
    private String category;
    private String rating;

    public VideoGameBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public VideoGameBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public VideoGameBuilder setReleaseDate(DateTime releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public VideoGameBuilder setReviewScore(String reviewScore) {
        this.reviewScore = reviewScore;
        return this;
    }

    public VideoGameBuilder setCategory(String category) {
        this.category = category;
        return this;
    }

    public VideoGameBuilder setRating(String rating) {
        this.rating = rating;
        return this;
    }

    public VideoGameDto build() {
        return new VideoGameDto(id, name, releaseDate, reviewScore, category, rating);
    }

}
