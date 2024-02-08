package com.techelevator.movies.dao;

import com.techelevator.movies.model.Person;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcPersonDao implements PersonDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcPersonDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Person> getPersons() {
        List<Person> persons = new ArrayList<>();
        String sql = "SELECT * FROM person;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            persons.add(mapRowToPerson(results));
        }
        return persons;
    }

    @Override
    public Person getPersonById(int id) {
        Person person = null;
        String sql = "SELECT * FROM person WHERE person_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        while(results.next()) {
            person = mapRowToPerson(results);
        }
        return person;

//        return new Person(-1, "Not implemented yet", null, null, "", "", "");
    }

    @Override
    public List<Person> getPersonsByName(String name, boolean useWildCard) {
        List<Person> persons = new ArrayList<>();
        String sql = "SELECT DISTINCT * FROM person WHERE person_name ILIKE ?;";
        if (useWildCard){
            name = "%" + name + "%";
        }
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,name);
        while (results.next()) {
            persons.add(mapRowToPerson(results));
        }
        return persons;
    }

    @Override
    public List<Person> getPersonsByCollectionName(String collectionName, boolean useWildCard) {
        List<Person> persons = new ArrayList<>();
        String sql = "SELECT DISTINCT p.* FROM person p JOIN movie_actor ma ON p.person_id = ma.actor_id JOIN movie m ON m.movie_id = ma.movie_id JOIN collection c ON c.collection_id = m.collection_id WHERE c.collection_name ILIKE ? GROUP BY p.person_id ;";
        if (useWildCard) {
            collectionName = "%" + collectionName + "%";
        }
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,collectionName);
        while (results.next()) {
            persons.add(mapRowToPerson(results));
        }
        return persons;
    }

    private Person mapRowToPerson(SqlRowSet rowSet) {
        Person person = new Person();
        person.setId(rowSet.getInt("person_id"));
        person.setName(rowSet.getString("person_name"));
        if (rowSet.getDate("birthday") != null) {
            person.setBirthday(rowSet.getDate("birthday").toLocalDate());
        }
        if (rowSet.getDate("deathday") != null) {
            person.setDeathDate(rowSet.getDate("deathday").toLocalDate());
        }
        person.setBiography(rowSet.getString("biography"));
        person.setProfilePath(rowSet.getString("profile_path"));
        person.setHomePage(rowSet.getString("home_page"));
        return person;

    }
}
