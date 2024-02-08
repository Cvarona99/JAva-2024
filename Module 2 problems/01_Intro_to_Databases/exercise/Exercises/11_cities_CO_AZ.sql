-- 11. The name, state, and population of all cities in Colorado (CO) or Arizona (AZ) (22 rows)
--SELECT * from city
SELECT city_name, state_abbreviation, population from city WHERE state_abbreviation = 'CO' or state_abbreviation = 'AZ' 