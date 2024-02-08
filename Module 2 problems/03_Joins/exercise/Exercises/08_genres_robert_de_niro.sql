-- 8. The genres of movies that Robert De Niro has appeared in that were released in 2010 or later, sorted alphabetically.
-- (6 rows)
SELECT DISTINCT genre_name FROM genre g
JOIN movie_genre mg ON mg.genre_id = g.genre_id
JOIN movie_actor ma ON ma.movie_id = mg.movie_id
JOIN person p ON p.person_id = ma.actor_id
JOIN movie m ON m.movie_id = mg.movie_id
WHERE person_name = 'Robert De Niro' AND release_date >= '2010-01-01'
ORDER BY genre_name ASC