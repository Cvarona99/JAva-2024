-- 5. The titles and release dates of all the movies that are in the Comedy genre. 
-- Order the results by release date, earliest to latest. 
-- (220 rows)
SELECT title, release_date FROM movie m
JOIN movie_genre mg ON mg.movie_id = m.movie_id
JOIN genre g ON g.genre_id = mg.genre_id
WHERE genre_name = 'Comedy'
ORDER by release_date ASC
