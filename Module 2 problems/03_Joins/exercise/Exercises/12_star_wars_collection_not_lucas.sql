-- 12. The titles of the movies in the "Star Wars Collection" that weren't directed by George Lucas, sorted alphabetically.
-- (5 rows)
SELECT title FROM movie m
JOIN collection c ON c.collection_id = m.collection_id
JOIN person p ON p.person_id = m.director_id
WHERE collection_name = 'Star Wars Collection' AND person_name != 'George Lucas'
ORDER by title ASC
