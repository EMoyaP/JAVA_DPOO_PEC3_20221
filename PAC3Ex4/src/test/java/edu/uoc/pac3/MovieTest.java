package edu.uoc.pac3;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MovieTest {

	private Movie movie1;
	private Movie movie2;

	MovieTest(){
		Actor jim = new Actor("Jim", "Carrey");
		Actor laura = new Actor("Laura", "Linney");
		Actor ed = new Actor("Ed", "Harris");
		movie1 = new Movie("The Truman Show", "Bla, bla, bla", new Actor[]{jim,laura,ed});
		movie2 = new Movie("Man on the moon", "Explains the life of Andy Kaufman", new Actor[]{jim});
	}

	@Test
	void testJsonMovie() {
		assertEquals("{\"title\":\"The Truman Show\",\"summary\":\"Bla, bla, bla\",\"casting\":[{\"name\":\"Jim\",\"surname\":\"Carrey\"},{\"name\":\"Laura\",\"surname\":\"Linney\"},{\"name\":\"Ed\",\"surname\":\"Harris\"}]}", movie1.getJSON());
		assertEquals("{\"title\":\"Man on the moon\",\"summary\":\"Explains the life of Andy Kaufman\",\"casting\":[{\"name\":\"Jim\",\"surname\":\"Carrey\"}]}", movie2.getJSON());
	}

	@Test
	void testJsonMoviesArray() {
		Movie[] arrayMovies = new Movie[]{movie1, movie2};
		assertEquals("[{\"title\":\"The Truman Show\",\"summary\":\"Bla, bla, bla\",\"casting\":[{\"name\":\"Jim\",\"surname\":\"Carrey\"},{\"name\":\"Laura\",\"surname\":\"Linney\"},{\"name\":\"Ed\",\"surname\":\"Harris\"}]}, {\"title\":\"Man on the moon\",\"summary\":\"Explains the life of Andy Kaufman\",\"casting\":[{\"name\":\"Jim\",\"surname\":\"Carrey\"}]}]", Arrays.stream(arrayMovies).map(Movie::getJSON).collect(Collectors.toList()).toString());
	}

	@Test
	void testPrettyJsonMovie() {
		assertEquals("""
				{
				  "title": "The Truman Show",
				  "summary": "Bla, bla, bla",
				  "casting": [
				    {
				      "name": "Jim",
				      "surname": "Carrey"
				    },
				    {
				      "name": "Laura",
				      "surname": "Linney"
				    },
				    {
				      "name": "Ed",
				      "surname": "Harris"
				    }
				  ]
				}""", movie1.getPrettyJSON());
	}

	@Test
	void testPrettyJsonMovie2() {

		assertEquals("""
				{
				  "title": "Man on the moon",
				  "summary": "Explains the life of Andy Kaufman",
				  "casting": [
				    {
				      "name": "Jim",
				      "surname": "Carrey"
				    }
				  ]
				}""", movie2.getPrettyJSON());
	}


	@Test
	void testGetObject() {
		String inputJson = "{'title':'Mr. Nobody','summary':'Great movie about the alternate life paths that could have resulted from his making different decisions in his life','casting':[{'name':'Jared','surname':'Leto'},{'name':'Sarah','surname':'Polley'}]}";

		Movie movie = Movie.getObject(inputJson);
		assertNotNull(movie);
		assertEquals("Mr. Nobody", movie.getTitle());
		assertEquals("Great movie about the alternate life paths that could have resulted from his making different decisions in his life", movie.getSummary());
		assertEquals(2, movie.getCasting().length);
		assertEquals("Jared", movie.getCasting()[0].getName());
		assertEquals("Leto", movie.getCasting()[0].getSurname());
		assertEquals("Sarah", movie.getCasting()[1].getName());
		assertEquals("Polley", movie.getCasting()[1].getSurname());
	}
}
