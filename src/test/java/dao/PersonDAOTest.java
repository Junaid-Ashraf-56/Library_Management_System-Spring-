package dao;

import config.AppConfig;
import model.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AppConfig.class})
@Transactional
class PersonDAOTest {

    @Autowired
    private PersonDAO personDAO;

    @Test
    void testDuplicateLibraryIdInsertion_shouldFail() {
        Person p1 = new Person(1001, "Ali",1,"Atomic Habits","ali@example.com");
        Person p2 = new Person(1001, "Zain",1,"Hyper Focus", "zain@example.com");

        personDAO.addPerson(p1);

        assertThrows(DataIntegrityViolationException.class, () -> {
            personDAO.addPerson(p2);
        });
    }
}
