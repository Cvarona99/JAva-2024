-- 9. Remove "Memento" from the movie table
-- You'll have to remove data from two other tables before you can remove it (13 rows, 2 rows, 1 row) 

DELETE from movie_actor
WHERE movie_id IN (SELECT movie_id from movie WHERE title = 'Memento');

DELETE from movie_genre
WHERE movie_id IN (SELECT movie_id from movie WHERE title = 'Memento');




DELETE from movie
WHERE title = 'Memento';
