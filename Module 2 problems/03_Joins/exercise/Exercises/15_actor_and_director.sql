-- 15. The title of the movie and the name of director for movies where the director was also an actor in the same movie.
-- Order the results by movie title (A-Z)
-- (73 rows)
SELECT title, person_name FROM movie m
JOIN person p ON p.person_id = m.director_id
JOIN movie_actor ma ON ma.movie_id = m.movie_id
WHERE m.director_id = ma.actor_id
ORDER BY title 

