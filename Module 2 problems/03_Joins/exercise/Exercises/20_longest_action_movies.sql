-- 20. The titles, lengths, and release dates of the 5 longest movies in the "Action" genre. 
-- Order the movies by length (highest first), then by release date (latest first).
-- (5 rows, expected lengths around 180 - 200)
SELECT title, length_minutes, release_date FROM movie m
JOIN movie_genre mg ON mg.movie_id = m.movie_id
JOIN genre g ON g.genre_id = mg.genre_id
WHERE genre_name = 'Action'
ORDER BY length_minutes DESC, release_date DESC LIMIT 5
