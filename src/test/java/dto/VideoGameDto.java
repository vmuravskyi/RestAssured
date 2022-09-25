package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.google.common.base.MoreObjects;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.joda.time.DateTime;

public class VideoGameDto {

    @JsonProperty(value = "id", access = Access.READ_WRITE)
    private String id;

    @JsonProperty(value = "name", access = Access.READ_WRITE)
    private String name;

    @JsonProperty(value = "releaseDate", access = Access.READ_WRITE)
    private DateTime releaseDate;

    @JsonProperty(value = "reviewScore", access = Access.READ_WRITE)
    private String reviewScore;

    @JsonProperty(value = "category", access = Access.READ_WRITE)
    private String category;

    @JsonProperty(value = "rating", access = Access.READ_WRITE)
    private String rating;


    public String getReviewScore() {
        return reviewScore;
    }

    public void setReviewScore(String reviewScore) {
        this.reviewScore = reviewScore;
    }

    public DateTime getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(DateTime releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        VideoGameDto that = (VideoGameDto) o;

        return new EqualsBuilder().append(reviewScore, that.reviewScore)
            .append(releaseDate, that.releaseDate).append(name, that.name).append(rating, that.rating)
            .append(id, that.id)
            .append(category, that.category).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(reviewScore).append(releaseDate).append(name).append(rating)
            .append(id)
            .append(category).toHashCode();
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("reviewScore", reviewScore)
            .add("releaseDate", releaseDate)
            .add("name", name)
            .add("rating", rating)
            .add("id", id)
            .add("category", category)
            .toString();
    }

}
