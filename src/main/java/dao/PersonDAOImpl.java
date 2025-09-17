package dao;

import model.Person;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class PersonDAOImpl implements PersonDAO{
    private final JdbcTemplate jdbcTemplate;

    public PersonDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addPerson(Person person) {
        String sql = "INSERT INTO person(libraryId,name,borrowBooks,borrowBooksName,email) VALUES(?,?,?,?,?)";
        jdbcTemplate.update(sql,person.getLibraryId(),person.getName(),person.getBorrowBooks(),person.getBorrowBooksName(),person.getEmail());
    }

    @Override
    public void getPersonByLibId(int id) {
        String sql = "SELECT * FROM person WHERE id = ?";
        jdbcTemplate.queryForObject(sql,new Object[]{id},(rs,rowNum)-> new Person(
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
        jdbcTemplate.queryForObject(sql,(rs, rowNum) ->new Person(

        ) );
    }

    @Override
    public void updatePerson() {

    }

    @Override
    public void deletePerson(Person person) {

    }
}
