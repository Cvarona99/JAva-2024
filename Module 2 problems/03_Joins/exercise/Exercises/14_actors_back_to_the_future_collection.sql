-- 14. The names of actors who've appeared in the movies in the "Back to the Future Collection", sorted alphabetically.
-- (28 rows)
SELECT DISTINCT person_name FROM person p 
JOIN movie_actor ma ON actor_id = person_id
JOIN movie m ON m.movie_id = ma.movie_id
JOIN collection c ON c.collection_id = m.collection_id
WHERE collection_name LIKE 'Back to the Future%'
ORDER BY person_name ASC
