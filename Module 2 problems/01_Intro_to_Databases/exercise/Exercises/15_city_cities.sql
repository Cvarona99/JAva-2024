-- 15. The name, state abbreviation, and population for cities that end with the word "City" (11 rows)
SELECT city_name, state_abbreviation, population from city WHERE city_name LIKE '%City';
