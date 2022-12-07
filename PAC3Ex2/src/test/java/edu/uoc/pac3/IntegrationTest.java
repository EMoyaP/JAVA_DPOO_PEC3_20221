package edu.uoc.pac3;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
class IntegrationTest {
	Theater theater1, theater2;
	Movie movie1, movie2;

	@BeforeAll
	void init(){
		try {
			theater1 = new Theater("Theater 1", 300, 5);
            theater2 = new Theater("Theater 2", 150, 2);

		    movie1 = new Movie("Baby Driver",
                    "After being coerced into working for a crime boss, a young getaway driver finds himself taking part in a heist doomed to fail. Baby is a young and partially hearing impaired getaway driver who can make any wild move while in motion with the right track playing.",
                    113, LocalDate.of(2017, 6, 28), true);

            movie2 = new Movie("The Truman Show",
                    "Truman Burbank is the unsuspecting star of The Truman Show, a reality television program filmed 24/7 through thousands of hidden cameras and broadcast to a worldwide audience. Christof, the show's creator and executive producer, seeks to capture Truman's authentic emotions and give audiences a relatable everyman.",
                    103, LocalDate.of(2017, 6, 28), false);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Init failed");
		}
	}

	@Test
	@Order(1)
	void testIntegration1() {
		try {
			theater1.addMovie(movie1);
			assertEquals(movie1, theater1.getMovie(0));
			assertEquals(5, theater1.getMovies().length);
			assertEquals(theater1, movie1.getTheater(0));
			assertTrue(movie1.isInTheTheater(theater1));
		} catch (Exception e) {
			e.printStackTrace();
			fail("Integration1 failed");
		}
	}

	@Test
	@Order(2)
	void testIntegration2()  {
		try{
			Exception ex = assertThrows( Exception.class,() -> movie1.addTheater(theater1));
			assertEquals(Movie.ERR_THEATER_EXISTS, ex.getMessage());

			ex = assertThrows( Exception.class,() -> theater1.addMovie(movie1));
			assertEquals(Theater.ERR_MOVIE_EXISTS, ex.getMessage());
		}catch(Exception e) {
			e.printStackTrace();
			fail("Integration2 failed");
		}
	}


	@Test
	@Order(3)
	void testIntegration3() {
		try {
			movie1.removeTheater(theater1);
			assertTrue(Arrays.stream(theater1.getMovies()).allMatch(m -> m == null));
			assertTrue(theater1.canScreenMoreMovies());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Integration3 failed");
		}
	}

	@Test
	@Order(4)
	void testIntegration4() {
		try {
			movie1.addTheater(theater1);
			assertEquals(movie1, theater1.getMovie(0));
			assertEquals(4,Arrays.stream(theater1.getMovies()).filter(m -> m == null).count());
			assertEquals(movie1, theater1.getMovies()[0]);
			assertEquals(theater1, movie1.getTheater(0));
			assertTrue(movie1.isInTheTheater(theater1));
		} catch (Exception e) {
			e.printStackTrace();
			fail("Integration4 failed");
		}
	}


	@Test
	@Order(5)
	void testIntegration5() {
		try {
			theater1.removeMovie(movie1);
			assertTrue(Arrays.stream(theater1.getMovies()).allMatch(m -> m == null));

			for(var i = 0 ; i<Movie.MAX_THEATERS; i++){
				assertNull(movie1.getTheater(i));
			}

		} catch (Exception e) {
			e.printStackTrace();
			fail("Integration5 failed");
		}
	}

	@Test
	@Order(6)
	void testIntegration6() {
		try {
			theater1.addMovie(movie2);
			assertTrue(Arrays.stream(theater1.getMovies()).noneMatch(m -> m == movie1));
			assertTrue(Arrays.stream(theater1.getMovies()).anyMatch(m -> m == movie2));

			assertEquals(movie2, theater1.getMovie(0));
			assertEquals(4,Arrays.stream(theater1.getMovies()).filter(m -> m == null).count());
			assertEquals(2,Arrays.stream(theater2.getMovies()).filter(m -> m == null).count());

			assertTrue(theater1.movieExists(movie2));
			assertEquals(theater1, movie2.getTheater(0));

			for(var i = 1 ; i<5; i++){
				assertNull(theater1.getMovie(i));
			}

			for(var i = 0 ; i<Movie.MAX_THEATERS; i++){
				assertNull(movie1.getTheater(i));
			}

			assertEquals(1, Arrays.stream(theater1.getMovies()).filter(m -> m != null).count());

			Exception ex = assertThrows( Exception.class,() -> theater1.addMovie(null));
			assertEquals(Theater.ERR_NULL_MOVIE, ex.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Integration6 failed");
		}
	}

	@Test
	@Order(7)
	void testIntegration7() {
		try{
			movie1.addTheater(theater2);
			movie2.addTheater(theater2);

			assertEquals(movie1, theater2.getMovie(0));
			assertEquals(movie2, theater2.getMovie(1));
			assertEquals(4,Arrays.stream(theater1.getMovies()).filter(m -> m == null).count());
			assertEquals(0,Arrays.stream(theater2.getMovies()).filter(m -> m == null).count());
			assertFalse(Arrays.stream(theater1.getMovies()).allMatch(m -> m == null));
			assertEquals(movie2, theater1.getMovie(0));
			assertTrue(Arrays.stream(theater2.getMovies()).allMatch(m -> m != null));
			assertEquals(movie1, theater2.getMovies()[0]);
			assertEquals(movie2, theater2.getMovies()[1]);
			assertEquals(theater2, movie1.getTheater(0));

			for(var i = 1 ; i<Movie.MAX_THEATERS; i++){
				assertNull(movie1.getTheater(i));
			}

			assertEquals(theater1, movie2.getTheater(0));
			assertEquals(theater2, movie2.getTheater(1));
		}catch(Exception e) {
			e.printStackTrace();
			fail("Integration7 failed");
		}
	}


	@Test
	@Order(8)
	void testIntegration8() {
		try{
			theater2.removeMovie(movie2);
			theater2.removeMovie(movie1);

			assertTrue(Arrays.stream(theater2.getMovies()).allMatch(m -> m == null));

			assertFalse(movie1.isInTheTheater(theater2));
			assertFalse(movie2.isInTheTheater(theater2));
		}catch(Exception e) {
			e.printStackTrace();
			fail("Integration8 failed");
		}
	}

	@Test
	@Order(9)
	void testIntegration9() {
		try{
			for(int i = 0; i < theater2.getMovies().length-2; i++) {
				theater2.addMovie(new Movie("Title","Summary",1,null,true));
			}

			for(var i = 0; i<theater2.getMovies().length-2; i++){
				assertNotNull(theater2.getMovie(i));
			}

			for(var i = theater2.getMovies().length-2; i<theater2.getMovies().length; i++){
				assertNull(theater2.getMovie(i));
			}

			theater2.addMovie(new Movie("Title","Summary",1,null,true));
			assertTrue(theater2.canScreenMoreMovies());
			theater2.addMovie(new Movie("Title","Summary",1,null,true));
			assertFalse(theater2.canScreenMoreMovies());
			assertTrue(Arrays.stream(theater2.getMovies()).allMatch(m -> m != null));

			Exception ex = assertThrows( Exception.class,() -> theater2.addMovie(new Movie("T","S",1,null,false)));
			assertEquals(Theater.ERR_NO_MORE_MOVIES, ex.getMessage());

			assertFalse(theater2.canScreenMoreMovies());
		}catch(Exception e) {
			e.printStackTrace();
			fail("Integration9 failed");
		}
	}

	@Test
	@Order(10)
	void testIntegration10() {
		try{
			movie1.addTheater(theater1);
		}catch(Exception e){
			fail("testIntegration10 failed - 1");
		}

		assertEquals(movie2,theater1.getMovies()[0]);
		assertEquals(movie1,theater1.getMovies()[1]);
		assertTrue(movie1.isInTheTheater(theater1));
		assertFalse(movie1.isInTheTheater(theater2));

		Exception ex = assertThrows( Exception.class,() -> movie1.addTheater(theater1));
		assertEquals(Movie.ERR_THEATER_EXISTS, ex.getMessage());

		assertTrue(movie1.isInTheTheater(theater1));
		assertFalse(movie1.isInTheTheater(theater2));

		try{
			movie1.notInTheaters();
		}catch(Exception e){
			fail("testIntegration10 failed - 2");
		}

		assertFalse(movie1.isInTheTheater(theater1));
		assertFalse(movie1.isInTheTheater(theater2));

		try{
			movie1.addTheater(theater1);
		}catch(Exception e){
			fail("testIntegration10 failed - 3");
		}

		Theater theater3 = new Theater("Theater 3", 50, 1);
		try{
			movie1.addTheater(theater3);
		}catch(Exception e){
			fail("testIntegration10 failed - 4");
		}

		assertTrue(movie1.isInTheTheater(theater1));
		assertFalse(movie1.isInTheTheater(theater2));
		assertTrue(movie1.isInTheTheater(theater3));

		try{
			assertTrue(theater1.movieExists(movie1));
			assertFalse(theater2.movieExists(movie1));
			assertTrue(theater3.movieExists(movie1));
		}catch(Exception e){
			fail("testIntegration10 failed - 5");
		}


		try{
			movie1.notInTheaters();
		}catch(Exception e){
			fail("testIntegration10 failed - 6");
		}

		assertFalse(movie1.isInTheTheater(theater1));
		assertFalse(movie1.isInTheTheater(theater2));
		assertFalse(movie1.isInTheTheater(theater3));

		try{
			assertFalse(theater1.movieExists(movie1));
			assertFalse(theater2.movieExists(movie1));
			assertFalse(theater2.movieExists(movie1));
		}catch(Exception e){
			fail("testIntegration10 failed - 7");
		}

		try{
			assertTrue(theater1.movieExists(movie2));
		}catch(Exception e){
			fail("testIntegration10 failed - 8");
		}

		assertTrue(movie2.isInTheTheater(theater1));
		assertFalse(movie2.isInTheTheater(theater2));

		try{
			assertFalse(theater2.movieExists(movie2));
		}catch(Exception e){
			fail("testIntegration10 failed - 9");
		}

		ex = assertThrows( Exception.class,() -> theater2.removeMovie(new Movie("t","s",5,LocalDate.now(),true)));
		assertEquals(Theater.ERR_MOVIE_DOESNT_EXIST, ex.getMessage());

	}
}
