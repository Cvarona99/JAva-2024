-- 18. The count of the number of cities (name column 'num_cities') and the state abbreviation for each state and territory (exclude state abbreviation DC).
-- Order the results by state abbreviation.
-- (55 rows)
SELECT count(city_name) AS num_cities, state_abbreviation from city WHERE state_abbreviation != 'DC'GROUP BY state_abbreviation ORDER BY state_abbreviation
