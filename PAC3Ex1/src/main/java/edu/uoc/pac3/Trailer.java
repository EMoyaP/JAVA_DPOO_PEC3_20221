package edu.uoc.pac3;

import java.time.Duration;
import java.time.LocalDate;

/**
 * Trailer class
 *
 * @author Eugenio Moya Pérez
 * @version 1.0
 */
public class Trailer {

    /**
     * URL of the trailer.
     */
    private String url;

    /**
     * Duration of the trailer.
     */
    private int duration;

    /**
     * Release date of the trailer.
     */
    private LocalDate releaseDate;

    /**
     * Maximum duration of the trailer.
     */
    public static final int MAX_DURATION = 180;

    /**
     * Error message when the duration is 0, negative or greater than the maximum duration.
     */
    public static final String ERR_DURATION = "[ERROR] The duration of the trailer cannont be 0, negative or greater than " + MAX_DURATION;

    /**
     * Error message when the URL is null or a date that is later than the current date.
     */
    public static final String ERR_RELEASE = "[ERROR] The release date of the trailer cannont be null or a date that is later than today";

    /**
     * Constructor of the class Trailer.
     *
     * @param url         URL of the trailer.
     * @param duration    Duration of the trailer.
     * @param releaseDate Release date of the trailer.
     * @throws Exception if the duration is 0, negative or greater than the maximum duration.
     */
    public Trailer(String url, int duration, LocalDate releaseDate) throws Exception {
        try {
            setUrl(url);
            setDuration(duration);
            setReleaseDate(releaseDate);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Getter of the URL.
     *
     * @return URL of the trailer as a String.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Setter of the URL.
     *
     * @param url URL of the trailer.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Getter of the duration.
     *
     * @return Duration of the trailer as an int.
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Setter of the duration.
     *
     * @param duration Duration of the trailer.
     * @throws Exception if the duration is 0, negative or greater than the maximum duration.
     */
    public void setDuration(int duration) throws Exception {
        if (duration <= 0 || duration > MAX_DURATION) {
            throw new Exception(Trailer.ERR_DURATION);
        } else {
            this.duration = duration;
        }
    }

    /**
     * Getter of the release date.
     *
     * @return Release date of the trailer as a String with the format "mm:ss".
     */
    public String getFormattedDuration() {
        assert duration > 0;
        Duration d = Duration.ofMinutes(duration);
        return String.format("%02d:%02d", d.toMinutes() / 60, d.toMinutes() % 60);
    }

    /**
     * Getter of the release date.
     *
     * @return Release date of the trailer as a LocalDate.
     */
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    /**
     * Setter of the release date.
     *
     * @param releaseDate Release date of the trailer.
     * @throws Exception if the release date is null or a date that is later than the current date.
     */
    public void setReleaseDate(LocalDate releaseDate) throws Exception {
        if (releaseDate == null || releaseDate.isAfter(LocalDate.now())) {
            throw new Exception(ERR_RELEASE);
        } else {
            this.releaseDate = releaseDate;
        }
    }

}
