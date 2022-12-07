package edu.uoc.pac3;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Movie class
 *
 * @author Eugenio Moya Pérez
 * @version 1.0
 */
public class Movie {
    /**
     * Object of type Trailer associated by composition with the class Movie.
     */
    private Trailer trailer;

    /**
     * ID of the movie.
     */
    private UUID id;

    /**
     * Title of the movie.
     */
    private String title;

    /**
     * Summary of the movie.
     */
    private String summary;

    /**
     * Duration of the movie.
     */
    private int duration;

    /**
     * Release date of the movie.
     */
    private LocalDate releaseDate;

    /**
     * If the voice of the movie is original.
     */
    private boolean ov;

    /**
     * Maximum duration of the movie.
     */
    private static final int MAX_SUMMARY_LENGTH = 450;

    /**
     * Error message when the duration is 0 or negative.
     */
    public static final String ERR_DURATION = "[ERROR] the duration of the movie cannot be 0 or negative";

    /**
     * Error message when the length is greater than the maximum length.
     */
    public static final String ERR_SUMMARY_LENGTH = "[ERROR] The summary's length cannot be greater than " +
            MAX_SUMMARY_LENGTH + " characters";

    /**
     * Constructor of the class Movie without parameter for Trailer.
     *
     * @param title       This parameter is the Title of the movie.
     * @param summary     This parameter is the Summary of the movie.
     * @param duration    This parameter is the Duration of the movie.
     * @param releaseDate This parameter is the Release date of the movie.
     * @param ov          This parameter is the OV of the movie.
     * @throws Exception  This exception is thrown whith the exception of the methods setSummary and setDuration.
     */
    public Movie(String title, String summary, int duration, LocalDate releaseDate, boolean ov) throws Exception {

        try {
            setId();
            setTitle(title);
            setSummary(summary);
            setDuration(duration);
            setReleaseDate(releaseDate);
            setOv(ov);
            this.trailer = null;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Constructor of the class Movie with parameter for Trailer.
     *
     * @param title              This parameter is the Title of the movie.
     * @param summary            This parameter is the Summary of the movie.
     * @param duration           This parameter is the Duration of the movie.
     * @param releaseDate        This parameter is the Release date of the movie.
     * @param ov                 This parameter is the OV of the movie.
     * @param url                This parameter is the URL of the trailer.
     * @param trailerDuration    This parameter is the Duration of the trailer.
     * @param trailerReleaseDate This parameter is the Release date of the trailer.
     * @throws Exception         This exception is thrown whith the exception of the methods setSummary and setDuration.
     */
    public Movie(String title, String summary, int duration, LocalDate releaseDate, boolean ov,
                 String url, int trailerDuration, LocalDate trailerReleaseDate) throws Exception {
        try {
            setId();
            setTitle(title);
            setSummary(summary);
            setDuration(duration);
            setReleaseDate(releaseDate);
            setOv(ov);
            setTrailer(url, trailerDuration, trailerReleaseDate);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    /**
     * Getter of the ID of the movie.
     *
     * @return The ID of the movie as an UUID format.
     */
    public UUID getId() {
        return id;
    }

    /**
     * Setter of the ID of the movie. Sets the id of the movie as a random UUID format
     */
    private void setId() {

        this.id = UUID.randomUUID();
    }

    /**
     * Getter of the Title of the movie.
     *
     * @return The Title of the movie as a String format.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter of the Title of the movie.
     *
     * @param title This parameter is the Title of the movie.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter of the Summary of the movie.
     *
     * @return The Summary of the movie as a String format.
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Setter of the Summary of the movie.
     *
     * @param summary This parameter is the Summary of the movie.
     * @throws Exception This exception is thrown when the length is greater than the MAX_SUMMARY_LENGTH.
     */
    public void setSummary(String summary) throws Exception {
        if (summary.length() > MAX_SUMMARY_LENGTH) {
            throw new Exception(ERR_SUMMARY_LENGTH);
        } else {
            this.summary = summary;
        }

    }

    /**
     * Getter of the Duration of the movie.
     *
     * @return The Duration of the movie as an int format.
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Setter of the Duration of the movie.
     *
     * @param duration This parameter is the Duration of the movie.
     * @throws Exception This exception is thrown when the duration is 0 or negative.
     */
    public void setDuration(int duration) throws Exception {
        if (duration <= 0) {
            throw new Exception(Movie.ERR_DURATION);
        } else {
            this.duration = duration;
        }
    }

    /**
     * Getter of the Release date of the movie.
     *
     * @return The Release date of the movie as a LocalDate format.
     */
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    /**
     * Setter of the Release date of the movie.
     *
     * @param releaseDate This parameter is the Release date of the movie.
     */
    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * Getter of the OV of the movie.
     *
     * @return The OV of the movie as a boolean format.
     */
    public boolean isOv() {
        return ov;
    }

    /**
     * Setter of the OV of the movie.
     *
     * @param ov This parameter is the OV of the movie.
     */
    public void setOv(boolean ov) {
        this.ov = ov;
    }

    /**
     * Getter of the Trailer of the movie.
     *
     * @return The Trailer of the movie as a Trailer object format.
     */
    public Trailer getTrailer() {
        return trailer;
    }

    /**
     * Setter of the Trailer of the movie.
     *
     * @param url         This parameter is the URL of the trailer.
     * @param duration    This parameter is the Duration of the trailer.
     * @param releaseDate This parameter is the Release date of the trailer.
     */
    public void setTrailer(String url, int duration, LocalDate releaseDate) {

        try {
            this.trailer = new Trailer(url, duration, releaseDate);
            setDuration(duration);
            setReleaseDate(releaseDate);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
