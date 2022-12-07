package edu.uoc.pac3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TheaterTest {

    Theater theater;

    TheaterTest() {
        try {
            theater = new Theater("Theater 1", 300, 5);
        } catch (Exception e) {
            fail("MovieTest failed");
        }
    }

    @Test
    void getName() {
        assertEquals("Theater 1", theater.getName());
    }

    @Test
    void setName() {
        theater.setName("UOC DPOO");
        assertEquals("UOC DPOO", theater.getName());
    }

    @Test
    void getCapacity() {
        assertEquals(300, theater.getCapacity());
    }

    @Test
    void getMovies() {
        assertEquals(5, theater.getMovies().length);
    }

    @Test
    void getMovie() {
        try{
            assertNull(theater.getMovie(0));
            assertNull(theater.getMovie(4));
        }catch(Exception e){
            fail("getTheater failed");
        }

        Exception ex = assertThrows(Exception.class, () -> theater.getMovie(-89));
        assertEquals(Movie.ERR_WRONG_INDEX, ex.getMessage());

        ex = assertThrows(Exception.class, () -> theater.getMovie(-1));
        assertEquals(Movie.ERR_WRONG_INDEX, ex.getMessage());

        ex = assertThrows(Exception.class, () -> theater.getMovie(5));
        assertEquals(Movie.ERR_WRONG_INDEX, ex.getMessage());
    }

    @Test
    void movieExists() {
        try{
            assertFalse(theater.movieExists(null));
            assertFalse(theater.movieExists(new Movie("a","a",123,null,true)));
        }catch(Exception e){
            fail("movieExists failed");
        }
    }

    @Test
    void canScreenMoreMovies() {
        assertTrue(theater.canScreenMoreMovies());
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Attributes and Methods definition")
    void checkMethodsSanity() {
        Class<Theater> theaterClass = Theater.class;

        //check attribute fields
        assertEquals(8, theaterClass.getDeclaredFields().length);

        try {
            assertTrue(Modifier.isPrivate(theaterClass.getDeclaredField("name").getModifiers()));
            assertTrue(Modifier.isPrivate(theaterClass.getDeclaredField("capacity").getModifiers()));
            assertTrue(Modifier.isPrivate(theaterClass.getDeclaredField("movies").getModifiers()));

            assertTrue(Modifier.isPublic(theaterClass.getDeclaredField("ERR_WRONG_INDEX").getModifiers()));
            assertTrue(Modifier.isFinal(theaterClass.getDeclaredField("ERR_WRONG_INDEX").getModifiers()));
            assertTrue(Modifier.isStatic(theaterClass.getDeclaredField("ERR_WRONG_INDEX").getModifiers()));

            assertTrue(Modifier.isPublic(theaterClass.getDeclaredField("ERR_NULL_MOVIE").getModifiers()));
            assertTrue(Modifier.isFinal(theaterClass.getDeclaredField("ERR_NULL_MOVIE").getModifiers()));
            assertTrue(Modifier.isStatic(theaterClass.getDeclaredField("ERR_NULL_MOVIE").getModifiers()));

            assertTrue(Modifier.isPublic(theaterClass.getDeclaredField("ERR_NO_MORE_MOVIES").getModifiers()));
            assertTrue(Modifier.isFinal(theaterClass.getDeclaredField("ERR_NO_MORE_MOVIES").getModifiers()));
            assertTrue(Modifier.isStatic(theaterClass.getDeclaredField("ERR_NO_MORE_MOVIES").getModifiers()));

            assertTrue(Modifier.isPublic(theaterClass.getDeclaredField("ERR_MOVIE_EXISTS").getModifiers()));
            assertTrue(Modifier.isFinal(theaterClass.getDeclaredField("ERR_MOVIE_EXISTS").getModifiers()));
            assertTrue(Modifier.isStatic(theaterClass.getDeclaredField("ERR_MOVIE_EXISTS").getModifiers()));

            assertTrue(Modifier.isPublic(theaterClass.getDeclaredField("ERR_MOVIE_DOESNT_EXIST").getModifiers()));
            assertTrue(Modifier.isFinal(theaterClass.getDeclaredField("ERR_MOVIE_DOESNT_EXIST").getModifiers()));
            assertTrue(Modifier.isStatic(theaterClass.getDeclaredField("ERR_MOVIE_DOESNT_EXIST").getModifiers()));

        } catch (NoSuchFieldException e) {
            fail("[ERROR] There is some problem with the definition of the attributes");
            e.printStackTrace();
        }

        //check constructors
        assertEquals(1, theaterClass.getDeclaredConstructors().length);

        try {
            assertTrue(Modifier.isPublic(theaterClass.getDeclaredConstructor(String.class, int.class, int.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("There is some problem with the definition of constructors");
            e.printStackTrace();
        }

        //check methods, parameters and return types
        try {
            assertEquals(12,theaterClass.getDeclaredMethods().length);

            assertEquals(9, Arrays.stream(theaterClass.getDeclaredMethods()).filter(m -> Modifier.isPublic(m.getModifiers())).count());

            assertTrue(Modifier.isPrivate(theaterClass.getDeclaredMethod("setMovie", int.class, Movie.class).getModifiers()));
            assertTrue(Modifier.isPrivate(theaterClass.getDeclaredMethod("findMovieIndex", Movie.class).getModifiers()));

        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the name attribute");
            e.printStackTrace();
        }
    }
}
