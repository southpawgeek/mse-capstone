
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import edu.brandeis.gps.rseg127.lms.entities.User;
import edu.brandeis.gps.rseg127.lms.services.UserService;
import edu.brandeis.gps.rseg127.lms.repos.UserRepo;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.NoSuchElementException;

@SpringBootTest(classes = edu.brandeis.gps.rseg127.lms.Application.class)
@ExtendWith(SpringExtension.class)
@DisplayName("Testing User Creation")
public class UsersTest {
    User user = new User();
    @Autowired
    private UserService userService;

    @BeforeEach
    void beforeEach() {
        System.out.println("Before the Test of a New User Account");
    }

    @AfterEach
    void afterEach() {
        System.out.println("After the Test of a New User Account");
    }

    public void patron() {
        System.out.println("Creating a new Patron user");

        user.setUsername("jerry");
        user.setFirstName("Jerry");
        user.setMiddleName("D");
        user.setLastName("Sanchez");
        user.setEmailAddress("jerry.sanchez@test.com");
        user.setPasswordHash("testPatronUser");
        user.setHomeAddress("Earth C-137");
        user.setMailAddress("Earth C-137");
        user.setPhone1("555-555-2137");
        user.setPatronId("PAT9868");
        user.setUserType("PAT");
    }

//    public void librarian() {
//        System.out.println("Creating a new Librarian user");
//
//        user.setUsername("SimpleRick");
//        user.setFirstName("Simple");
//        user.setMiddleName("Rick");
//        user.setLastName("Sanchez");
//        user.setEmailAddress("simple.sanchez@test.com");
//        user.setPasswordHash("testLibrarianUser");
//        user.setHomeAddress("Dimension 35C");
//        user.setMailAddress("Dimension 35C");
//        user.setPhone1("555-555-0035");
//        user.setPatronId("LIB9878");
//        user.setUserType("LIB");
//    }

//    public void administrator() {
//        System.out.println("Creating a new Administrator user");
//
//        user.setUsername("EvilMorty");
//        user.setFirstName("Morty");
//        user.setMiddleName("T");
//        user.setLastName("Sanchez");
//        user.setEmailAddress("morty.sanchez@test.com");
//        user.setPasswordHash("testAdminUser");
//        user.setHomeAddress("Earth Unknown Dimension");
//        user.setMailAddress("Earth Unknown Dimension");
//        user.setPhone1("555-555-1234");
//        user.setPatronId("ADM9870");
//        user.setUserType("ADM");
//    }

//    @Test
//    @DisplayName("Test 1: Create a Patron User")
//    public void createPatronUser() {
//        patron();
//        assertEquals(user.getUsername(),"jerry");
//    }

//    @Test
//    @DisplayName("Test 2: Create a Librarian User")
//    public void createLibrarianUser() {
//        librarian();
//        assertEquals(user.getUsername(),"SimpleRick");
//    }
//
//    @Test
//    @DisplayName("Test 3: Create an Administrator User")
//    public void createAdministratorUser() {
//        administrator();
//        assertEquals(user.getUsername(),"EvilMorty");
//    }
//
//    @Test
//    @DisplayName("Test 4: Add new Patron user to the database")
//    public void savePatronTest() {
//        patron();
//        System.out.println(userService);
//        System.out.println(user);
//        assertEquals(userService.createUser(user),user);
//    }

//    @Test
//    @DisplayName("Test 5: Add new Librarian user to the database")
//    public void saveLibrarianTest() {
//        librarian();
//        System.out.println(userService);
//        System.out.println(user);
//        assertEquals(userService.createUser(user),user);
//    }
//
//    @Test
//    @DisplayName("Test 6: Add new Administrator user to the database")
//    public void saveAdministratorTest() {
//        administrator();
//        System.out.println(userService);
//        System.out.println(user);
//        assertEquals(userService.createUser(user),user);
//    }

//    @Test
//    @DisplayName("Test 7: Update a Patron User's Information")
//    void updatePatronTest() {
//        System.out.println("Updating a Patron User Profile");
//        // look at database to get user ID based on username
//        user = userService.getUser(1176);
//        user.setUsername("RickC-137B");
//        assertEquals(userService.updateUser(user),user);
//    }
//
//    @Test
//    @DisplayName("Test 8: Update a Librarian User's Information")
//    void updateLibrarianTest() {
//        System.out.println("Updating a Libarian User Profile");
//        // look at database to get user ID based on username
//        user = userService.getUser(1182);
//        user.setUsername("PickleRick");
//        assertEquals(userService.updateUser(user),user);
//    }

//    @Test
//    @DisplayName("Test 9: Update an Administrator User's Information")
//    void updateAdministratorTest() {
//        System.out.println("Updating an Administrator User Profile");
//        // look at database to get user ID based on username
//        user = userService.getUser(1181);
//        user.setUsername("CowyboyMorty");
//        assertEquals(userService.updateUser(user),user);
//    }

    //comment out tests 10-12 until there are known user IDs to delete
//    @Test
//    @DisplayName("Test 10: Remove a Patron User")
//    void removePatronTest() {
//        System.out.println("Remove a Patron User by ID");
//        // look at database to get user ID based on username
//        userService.deleteUser(1176);
//        assertThrows(NoSuchElementException.class, () -> {userService.getUser(1176);},"Removed User");
////    }
//
//    @Test
//    @DisplayName("Test 11: Remove a Librarian User")
//    void removeLibrarianTest() {
//        System.out.println("Remove a Librarian User by ID");
//        // look at database to get user ID based on username
//        userService.deleteUser(1182);
//        assertThrows(NoSuchElementException.class, () -> {userService.getUser(1182);},"Removed User");
//    }
//
//    @Test
//    @DisplayName("Test 12: Remove an Administrator User")
//    void removeAdministratorTest() {
//        System.out.println("Remove an Administrator User by ID");
//        // look at database to get user ID based on username
//        userService.deleteUser(1181);
//        assertThrows(NoSuchElementException.class, () -> {userService.getUser(1181);},"Removed User");
//    }

}