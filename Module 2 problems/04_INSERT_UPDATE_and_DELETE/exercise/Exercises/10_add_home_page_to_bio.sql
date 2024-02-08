-- 10. For all people born before 1950 and have a home page, add the home page to the end of their biography. (142 rows)
-- Note: Assume all biographies end in a period with no trailing spaces. You'll need to also add a space before the website address.
UPDATE person 
SET biography = biography || ' ' || home_page
-- SELECT birthday, biography, home_page FROM person
WHERE birthday Between '1800-01-01' and '1949-12-31' and home_page != 'NULL'

