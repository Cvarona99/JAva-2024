-- 12. The state name, nickname, and sales tax from records in the state table in the "West" and "South" census regions with a sales tax that isn't 0% (26 rows)
--SELECT * FROM state
SELECT state_name, state_nickname, sales_tax from state WHERE sales_tax != 0 AND (census_region = 'West' or census_region = 'South')