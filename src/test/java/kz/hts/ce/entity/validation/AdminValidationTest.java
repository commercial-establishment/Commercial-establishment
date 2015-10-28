package kz.hts.ce.entity.validation;

import kz.hts.ce.entity.Admin;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class AdminValidationTest {

    public static final String ADMIN_CORRECT_NAME = "John";
    public static final String ADMIN_CORRECT_SURNAME = "Doe";
    public static final String ADMIN_CORRECT_EMAIL = "JohnDoe@gmail.com";
    public static final String ADMIN_CORRECT_PASSWORD = "JohnDoe11";
    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void adminFieldsIsEmpty() {
        Admin admin = new Admin();

        Set<ConstraintViolation<Admin>> constraintViolations = validator.validate(admin);

        assertEquals(5, constraintViolations.size());
        assertEquals("may not be empty", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void usernameRegexpDoesNotSuit() {
        Admin admin = new Admin();
        admin.setUsername("John!");
        admin.setName("John");
        admin.setSurname("Doe");
        admin.setEmail("JohnDoe@gmail.com");
        admin.setPassword("JohnDoe11");

        Set<ConstraintViolation<Admin>> constraintViolations = validator.validate(admin);

        assertEquals(1, constraintViolations.size());
        assertEquals("must match \"^[a-z0-9_-]+[a-z0-9_-]$\"", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void usernameRegexpIsCorrect() {
        Admin admin = new Admin();
        admin.setUsername("john");
        admin.setName("John");
        admin.setSurname("Doe");
        admin.setEmail("JohnDoe@gmail.com");
        admin.setPassword("JohnDoe11");

        Set<ConstraintViolation<Admin>> constraintViolations = validator.validate(admin);

        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void usernameCharactersNumberLessThanYouNeed() {
        Admin admin = new Admin();
        admin.setUsername("jo");
        admin.setName("John");
        admin.setSurname("Doe");
        admin.setEmail("JohnDoe@gmail.com");
        admin.setPassword("JohnDoe11");

        Set<ConstraintViolation<Admin>> constraintViolations = validator.validate(admin);

        assertEquals(1, constraintViolations.size());
        assertEquals("size must be between 3 and 14", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void usernameCharactersNumberMoreThanYouNeed() {
        Admin admin = new Admin();
        admin.setUsername("johndoe-johndoe-johndoe");
        admin.setName("John");
        admin.setSurname("Doe");
        admin.setEmail("JohnDoe@gmail.com");
        admin.setPassword("JohnDoe11");

        Set<ConstraintViolation<Admin>> constraintViolations = validator.validate(admin);

        assertEquals(1, constraintViolations.size());
        assertEquals("size must be between 3 and 14", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void emailRegexpDoesNotSuit() {
        Admin admin = new Admin();
        admin.setEmail("JohnDoe@JohnDoe");

        admin.setUsername("john");
        admin.setName("John");
        admin.setSurname("Doe");
        admin.setPassword("JohnDoe11");

        Set<ConstraintViolation<Admin>> constraintViolations = validator.validate(admin);

        assertEquals(1, constraintViolations.size());
        assertEquals("must match \"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]" +
                "+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$\"", constraintViolations.iterator().next().getMessage());
    }
}
