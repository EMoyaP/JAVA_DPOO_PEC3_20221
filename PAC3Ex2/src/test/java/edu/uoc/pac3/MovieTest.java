package edu.uoc.pac3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;
import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {

    Movie movie;

    MovieTest() {
        try {
            movie = new Movie("Baby Driver",
                    "After being coerced into working for a crime boss, a young getaway driver finds himself taking part in a heist doomed to fail. Baby is a young and partially hearing impaired getaway driver who can make any wild move while in motion with the right track playing.",
                    113, LocalDate.of(2017, 6, 28), true);
        } catch (Exception e) {
            fail("MovieTest failed");
        }
    }

    @Test
    void getId() {
        assertEquals(36, movie.getId().toString().length());
    }

    @Test
    void setId() {
        try {
            Movie m = new Movie("The Truman Show",
                    "Truman Burbank is the unsuspecting star of The Truman Show, a reality television program filmed 24/7 through thousands of hidden cameras and broadcast to a worldwide audience. Christof, the show's creator and executive producer, seeks to capture Truman's authentic emotions and give audiences a relatable everyman.",
                    103, LocalDate.of(2017, 6, 28), false);

            assertNotEquals(movie.getId(), m.getId());
            assertEquals(36, m.getId().toString().length());

        } catch (Exception e) {
            fail("setId failed");
        }
    }

    @Test
    void getTitle() {
        assertEquals("Baby Driver", movie.getTitle());
    }

    @Test
    void setTitle() {
        movie.setTitle("The Truman Show");
        assertEquals("The Truman Show", movie.getTitle());
    }

    @Test
    void getSummary() {
        assertEquals("After being coerced into working for a crime boss, a young getaway driver finds himself taking part in a heist doomed to fail. Baby is a young and partially hearing impaired getaway driver who can make any wild move while in motion with the right track playing.",
                movie.getSummary());
    }

    @Test
    void setSummary() {
        Exception ex = assertThrows(Exception.class, () -> movie.setSummary("A gentle Jewish-Italian waiter, Guido Orefice (Roberto Benigni), meets Dora (Nicoletta Braschi), a pretty schoolteacher, and wins her over with his charm and humor. Eventually they marry and have a son, Giosue (Giorgio Cantarini). Their happiness is abruptly halted, however, when Guido and Giosue are separated from Dora and taken to a concentration camp. Determined to shelter his son from the horrors of his surroundings, Guido convinces Giosue that their time in the camp is merely a game."));
        assertEquals(Movie.ERR_SUMMARY_LENGTH, ex.getMessage());

        try {
            movie.setSummary("A gentle Jewish-Italian waiter has a son, Giosue. Their happiness is abruptly halted, when Guido and Giosue are taken to a concentration camp.");
            assertEquals("A gentle Jewish-Italian waiter has a son, Giosue. Their happiness is abruptly halted, when Guido and Giosue are taken to a concentration camp.", movie.getSummary());
        } catch (Exception e) {
            fail("testSummary failed");
        }
    }

    @Test
    void getDuration() {
        assertEquals(113, movie.getDuration());
    }

    @Test
    void setDuration() {
        Exception ex = assertThrows(Exception.class, () -> movie.setDuration(-89));
        assertEquals(Movie.ERR_DURATION, ex.getMessage());

        ex = assertThrows(Exception.class, () -> movie.setDuration(0));
        assertEquals(Movie.ERR_DURATION, ex.getMessage());

        try {
            movie.setDuration(500);
            assertEquals(500, movie.getDuration());
        } catch (Exception e) {
            fail("setDuration failed");
        }
    }

    @Test
    void getReleaseDate() {
        assertEquals(LocalDate.of(2017, 6, 28), movie.getReleaseDate());
    }

    @Test
    void setReleaseDate() {
        LocalDate release1 = LocalDate.of(2017, 6, 28);
        LocalDate release2 = LocalDate.now();

        movie.setReleaseDate(release1);
        assertEquals(release1, movie.getReleaseDate());

        movie.setReleaseDate(release2);
        assertEquals(release2, movie.getReleaseDate());
    }
    @Test
    void isOv() {
        assertTrue(movie.isOv());
    }

    @Test
    void setOv() {
        movie.setOv(false);
        assertFalse(movie.isOv());
    }

    @Test
    void getTrailer() {
        assertNull(movie.getTrailer());
    }

    @Test
    void setTrailer() {
        movie.setTrailer("https://youtu.be/_XnNVXahJ2k", 183, LocalDate.of(2017,3,11));
        assertNull(movie.getTrailer());

        movie.setTrailer("https://youtu.be/_XnNVXahJ2k", 163, LocalDate.of(2023,3,11));
        assertNull(movie.getTrailer());

        movie.setTrailer("https://youtu.be/_XnNVXahJ2k", 163, LocalDate.of(2017,3,11));
        assertEquals("https://youtu.be/_XnNVXahJ2k", movie.getTrailer().getUrl());
        assertEquals(163, movie.getTrailer().getDuration());
        assertEquals(LocalDate.of(2017,3,11),movie.getTrailer().getReleaseDate());
    }

    @Test
    void getTheater(){
        try{
            assertNull(movie.getTheater(0));
            assertNull(movie.getTheater(Movie.MAX_THEATERS - 1));
        }catch(Exception e){
            fail("getTheater failed");
        }

        Exception ex = assertThrows(Exception.class, () -> movie.getTheater(-89));
        assertEquals(Movie.ERR_WRONG_INDEX, ex.getMessage());

        ex = assertThrows(Exception.class, () -> movie.getTheater(-1));
        assertEquals(Movie.ERR_WRONG_INDEX, ex.getMessage());

        ex = assertThrows(Exception.class, () -> movie.getTheater(Movie.MAX_THEATERS));
        assertEquals(Movie.ERR_WRONG_INDEX, ex.getMessage());
    }

    @Test
    void isInTheTheater(){
        Theater theater = new Theater("Theater 1", 300, 3);
        assertFalse(movie.isInTheTheater(theater));
        assertFalse(movie.isInTheTheater(null));
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Attributes and Methods definition")
    void checkMethodsSanity() {
        Class<Movie> movieClass = Movie.class;

        //check attribute fields
        assertEquals(14, movieClass.getDeclaredFields().length);

        try {
            assertTrue(Modifier.isPrivate(movieClass.getDeclaredField("id").getModifiers()));
            assertTrue(Modifier.isPrivate(movieClass.getDeclaredField("title").getModifiers()));
            assertTrue(Modifier.isPrivate(movieClass.getDeclaredField("summary").getModifiers()));
            assertTrue(Modifier.isPrivate(movieClass.getDeclaredField("duration").getModifiers()));
            assertTrue(Modifier.isPrivate(movieClass.getDeclaredField("releaseDate").getModifiers()));
            assertTrue(Modifier.isPrivate(movieClass.getDeclaredField("ov").getModifiers()));
            assertTrue(Modifier.isPrivate(movieClass.getDeclaredField("trailer").getModifiers()));

            assertTrue(Modifier.isPrivate(movieClass.getDeclaredField("MAX_SUMMARY_LENGTH").getModifiers()));
            assertTrue(Modifier.isFinal(movieClass.getDeclaredField("MAX_SUMMARY_LENGTH").getModifiers()));
            assertTrue(Modifier.isStatic(movieClass.getDeclaredField("MAX_SUMMARY_LENGTH").getModifiers()));

            assertTrue(Modifier.isPublic(movieClass.getDeclaredField("ERR_DURATION").getModifiers()));
            assertTrue(Modifier.isFinal(movieClass.getDeclaredField("ERR_DURATION").getModifiers()));
            assertTrue(Modifier.isStatic(movieClass.getDeclaredField("ERR_DURATION").getModifiers()));

            assertTrue(Modifier.isPublic(movieClass.getDeclaredField("ERR_SUMMARY_LENGTH").getModifiers()));
            assertTrue(Modifier.isFinal(movieClass.getDeclaredField("ERR_SUMMARY_LENGTH").getModifiers()));
            assertTrue(Modifier.isStatic(movieClass.getDeclaredField("ERR_SUMMARY_LENGTH").getModifiers()));

            assertTrue(Modifier.isPublic(movieClass.getDeclaredField("ERR_WRONG_INDEX").getModifiers()));
            assertTrue(Modifier.isFinal(movieClass.getDeclaredField("ERR_WRONG_INDEX").getModifiers()));
            assertTrue(Modifier.isStatic(movieClass.getDeclaredField("ERR_WRONG_INDEX").getModifiers()));

            assertTrue(Modifier.isPublic(movieClass.getDeclaredField("ERR_THEATER_EXISTS").getModifiers()));
            assertTrue(Modifier.isFinal(movieClass.getDeclaredField("ERR_THEATER_EXISTS").getModifiers()));
            assertTrue(Modifier.isStatic(movieClass.getDeclaredField("ERR_THEATER_EXISTS").getModifiers()));

        } catch (NoSuchFieldException e) {
            fail("[ERROR] There is some problem with the definition of the attributes");
            e.printStackTrace();
        }

        //check constructors
        assertEquals(2, movieClass.getDeclaredConstructors().length);

        try {
            assertTrue(Modifier.isPublic(movieClass.getDeclaredConstructor(String.class, String.class, int.class, LocalDate.class, boolean.class).getModifiers()));
            assertTrue(Modifier.isPublic(movieClass.getDeclaredConstructor(String.class, String.class, int.class, LocalDate.class, boolean.class, String.class, int.class, LocalDate.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("There is some problem with the definition of constructors");
            e.printStackTrace();
        }

        //check methods, parameters and return types
        try {
            assertEquals(22,movieClass.getDeclaredMethods().length);

            assertEquals(18, Arrays.stream(movieClass.getDeclaredMethods()).filter(m -> Modifier.isPublic(m.getModifiers())).count());

            assertTrue(Modifier.isPrivate(movieClass.getDeclaredMethod("setId").getModifiers()));
            assertTrue(Modifier.isPrivate(movieClass.getDeclaredMethod("getTheaters").getModifiers()));
            assertTrue(Modifier.isPublic(movieClass.getDeclaredMethod("addTheater", Theater.class).getModifiers()));
            assertTrue(Modifier.isPrivate(movieClass.getDeclaredMethod("findTheaterIndex", Theater.class).getModifiers()));

        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the name attribute");
            e.printStackTrace();
        }
    }
}
