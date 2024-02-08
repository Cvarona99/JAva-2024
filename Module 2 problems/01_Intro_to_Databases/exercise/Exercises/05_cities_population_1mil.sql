-- 5. The name, state abbreviation, and population of cities with a population greater than 1,000,000 people (10 rows)

-- SELECT * from city
SELECT city_name, state_abbreviation, population from city WHERE population > 1000000
