-- 13. The name, abbreviation, and population of all records in the state table with no official nickname (NULL) (5 rows)
--SELECT * from state
SELECT state_name, state_abbreviation, population from state WHERE state_nickname is NULL