-- 9. The name, abbreviation, population, and sales tax of all states and territories with a sales tax greater than 6.6% (9 rows)
-- SELECT * from state
SELECT state_name, state_abbreviation, population, sales_tax from state WHERE sales_tax > 6.6
