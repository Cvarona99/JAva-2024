-- 13. The directors of the movies in the "Harry Potter Collection", sorted alphabetically.
-- (4 rows)
SELECT DISTINCT person_name from person p 
JOIN movie m ON m.director_id = p.person_id
JOIN collection c ON c.collection_id = m.collection_id
WHERE collection_name = 'Harry Potter Collection'
