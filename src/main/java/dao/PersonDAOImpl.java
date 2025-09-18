package dao;

import model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class PersonDAOImpl implements PersonDAO{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addPerson(Person person) {
        String sql = "INSERT INTO person(libraryId,name,borrowBooks,borrowBooksName,email) VALUES(?,?,?,?,?)";
        jdbcTemplate.update(sql,person.getLibraryId(),person.getName(),person.getBorrowBooks(),person.getBorrowBooksName(),person.getEmail());
    }

    @Override
    public Person getPersonByLibId(int id) {
        String sql = "SELECT * FROM person WHERE libraryId = ?";
        return jdbcTemplate.queryForObject(sql,new Object[]{id},(rs,rowNum)-> new Person(
           rs.getInt("libraryId"),
           rs.getString("name"),
           rs.getInt("borrowBooks"),
           rs.getString("borrowBooksName"),
           rs.getString("email")
        ));
    }

    @Override
    public List<Person> getAllPerson() {
        String sql = "SELECT * FROM person";
        return jdbcTemplate.query(sql,((rs, rowNum) ->new Person(
                rs.getInt("libraryId"),
                rs.getString("name"),
                rs.getInt("borrowBooks"),
                rs.getString("borrowBooksName"),
                rs.getString("email")
        )));
    }

    @Override
    public void updatePerson(Person person) {
        String sql = "UPDATE person set name = ?,borrowBooks = ?,borrowBooksName = ?,email = ? WHERE libraryId = ?";
        jdbcTemplate.update(sql,
                person.getLibraryId(),
                person.getName(),
                person.getBorrowBooks(),
                person.getBorrowBooksName(),
                person.getEmail());
    }

    @Override
    public void deletePerson(int id) {
        String sql = "DELETE  FROM person WHERE libraryId = ?";
        jdbcTemplate.update(sql,id);
    }

    @Override
    public Person getPersonByEmail(String email){
        String sql = "SELECT * FROM person WHERE email = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{email}, (rs,rowNum) -> new Person(
                    rs.getInt("libraryId"),
                    rs.getString("name"),
                    rs.getInt("borrowBooks"),
                    rs.getString("borrowBooksName"),
                    rs.getString("email")
            ));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public int getPersonLibByEmail(String email) {
        String sql = "SELECT libraryId FROM person WHERE email = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{email}, Integer.class);
        } catch (EmptyResultDataAccessException e) {
            return -1; // or throw a custom exception
        }
    }

}
