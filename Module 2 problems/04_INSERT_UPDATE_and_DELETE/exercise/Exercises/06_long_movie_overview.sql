-- 6. For movies that are longer than 3 hours and 30 minutes (210 minutes), add the sentence "This is a long movie." to the end of the overview. (5 rows)
-- Note: The overviews end in a period with no trailing spaces. You'll need to also add a space before the new sentence.
UPDATE movie
SET overview = overview || ' This is a long movie.'
-- SELECT title, overview, length_minutes FROM movie WHERE length_minutes > 210
WHERE length_minutes > 210
