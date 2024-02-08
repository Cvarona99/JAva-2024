-- 17. The titles and taglines of movies that are in the "Family" genre that Samuel L. Jackson has acted in.
-- Order the results alphabetically by movie title.
-- (4 rows)
SELECT title, tagline from movie m
JOIN movie_actor ma ON ma.movie_id = m.movie_id
JOIN movie_genre mg ON mg.movie_id = ma.movie_id
JOIN genre g ON g.genre_id = mg.genre_id
JOIN person p ON person_id = actor_id
WHERE genre_name = 'Family' AND person_name = 'Samuel L. Jackson'
ORDER BY title 
