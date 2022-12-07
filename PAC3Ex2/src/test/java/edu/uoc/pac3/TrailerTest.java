package edu.uoc.pac3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;
import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TrailerTest {

    Trailer trailer;

    TrailerTest(){
        try{
            trailer = new Trailer("www.uoc.edu",83, LocalDate.of(2022,8,1));
        }catch(Exception e){
            fail("TrailerTest failed");
        }
    }

    @Test
    void getUrl() {
        assertEquals("www.uoc.edu", trailer.getUrl());
    }

    @Test
    void setUrl() {
        trailer.setUrl("eimt.uoc.edu");
        assertEquals("eimt.uoc.edu", trailer.getUrl());
    }

    @Test
    void getDuration() {
        assertEquals(83, trailer.getDuration());
    }

    @Test
    void setDuration() {
        Exception ex = assertThrows(Exception.class, () -> trailer.setDuration(-89));
        assertEquals(Trailer.ERR_DURATION, ex.getMessage());

        ex = assertThrows(Exception.class, () -> trailer.setDuration(0));
        assertEquals(Trailer.ERR_DURATION, ex.getMessage());

        ex = assertThrows(Exception.class, () -> trailer.setDuration(Trailer.MAX_DURATION+1));
        assertEquals(Trailer.ERR_DURATION, ex.getMessage());

        try {
            trailer.setDuration(Trailer.MAX_DURATION);
            assertEquals(Trailer.MAX_DURATION, trailer.getDuration());

            trailer.setDuration(94);
            assertEquals(94, trailer.getDuration());

        } catch (Exception e) {
            fail("setDuration failed");
        }
    }

    @Test
    void getFormattedDuration() {
        assertEquals("01:23",trailer.getFormattedDuration());

        try{
            trailer.setDuration(50);
            assertEquals("00:50",trailer.getFormattedDuration());
            trailer.setDuration(61);
            assertEquals("01:01",trailer.getFormattedDuration());

            trailer.setDuration(90);
            assertEquals("01:30",trailer.getFormattedDuration());

            trailer.setDuration(103);
            assertEquals("01:43",trailer.getFormattedDuration());

            trailer.setDuration(180);
            assertEquals("03:00",trailer.getFormattedDuration());

        }catch(Exception e){
            fail("getFormattedDuration failed");
        }
    }

    @Test
    void getReleaseDate() {
        assertEquals(LocalDate.of(2022,8,1),trailer.getReleaseDate());
    }

    @Test
    void setReleaseDate() {
        Exception ex = assertThrows(Exception.class, () -> trailer.setReleaseDate(null));
        assertEquals(Trailer.ERR_RELEASE, ex.getMessage());

        ex = assertThrows(Exception.class, () -> trailer.setReleaseDate(LocalDate.of(2022,12,21)));
        assertEquals(Trailer.ERR_RELEASE, ex.getMessage());

        ex = assertThrows(Exception.class, () -> trailer.setReleaseDate(LocalDate.of(2022,12,11)));
        assertEquals(Trailer.ERR_RELEASE, ex.getMessage());

        try{
            trailer.setReleaseDate(LocalDate.now());
            assertEquals(LocalDate.now(), trailer.getReleaseDate());
        }catch(Exception e){
            fail("setReleaseDate failed");
        }
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Attributes and Methods definition")
    void checkMethodsSanity() {
        Class<Trailer> trailerClass = Trailer.class;

        //check attribute fields, includes the assertion in getFormattedDuration
        assertEquals(7, trailerClass.getDeclaredFields().length);


        try {
            assertTrue(Modifier.isPrivate(trailerClass.getDeclaredField("url").getModifiers()));
            assertTrue(Modifier.isPrivate(trailerClass.getDeclaredField("duration").getModifiers()));
            assertTrue(Modifier.isPrivate(trailerClass.getDeclaredField("releaseDate").getModifiers()));

            assertTrue(Modifier.isPublic(trailerClass.getDeclaredField("MAX_DURATION").getModifiers()));
            assertTrue(Modifier.isFinal(trailerClass.getDeclaredField("MAX_DURATION").getModifiers()));
            assertTrue(Modifier.isStatic(trailerClass.getDeclaredField("MAX_DURATION").getModifiers()));

            assertTrue(Modifier.isPublic(trailerClass.getDeclaredField("ERR_DURATION").getModifiers()));
            assertTrue(Modifier.isFinal(trailerClass.getDeclaredField("ERR_DURATION").getModifiers()));
            assertTrue(Modifier.isStatic(trailerClass.getDeclaredField("ERR_DURATION").getModifiers()));

            assertTrue(Modifier.isPublic(trailerClass.getDeclaredField("ERR_RELEASE").getModifiers()));
            assertTrue(Modifier.isFinal(trailerClass.getDeclaredField("ERR_RELEASE").getModifiers()));
            assertTrue(Modifier.isStatic(trailerClass.getDeclaredField("ERR_RELEASE").getModifiers()));

        } catch (NoSuchFieldException e) {
            fail("[ERROR] There is some problem with the definition of the attributes");
            e.printStackTrace();
        }

        //check constructors
        assertEquals(1, trailerClass.getDeclaredConstructors().length);

        try {
            assertTrue(Modifier.isPublic(trailerClass.getDeclaredConstructor(String.class, int.class, LocalDate.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("There is some problem with the definition of constructors");
            e.printStackTrace();
        }

        //check methods, parameters and return types

            assertEquals(7,trailerClass.getDeclaredMethods().length);
            assertEquals(7,Arrays.stream(trailerClass.getDeclaredMethods())
                    .filter(m -> Modifier.isPublic(m.getModifiers()))
                    .count());


    }
}
