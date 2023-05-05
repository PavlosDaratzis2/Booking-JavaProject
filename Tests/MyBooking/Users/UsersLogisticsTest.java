package MyBooking.Users;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class UsersLogisticsTest {
    UsersLogistics usersLogistics;
    @Before
    public void setUp() throws Exception {
        usersLogistics = new UsersLogistics();
    }

    @After
    public void tearDown() throws Exception {
        Users.code = 0;
    }

    @Test
    public void createNewClient() {
        usersLogistics.createNewClient("Maria", "1111", "Around the corner",true,false);
        assertEquals(1, usersLogistics.getUsers().size());
        assert(usersLogistics.getUsers().get(0) instanceof Client);
    }

    @Test
    public void createNewProvider() {
        usersLogistics.createNewProvider("Kostas", "1234", "Around the corner",true,false);
        assertEquals(1, usersLogistics.getUsers().size());
        usersLogistics.createNewClient("Mike", "1111", "Around the corner",true,false);
        assertEquals(2, usersLogistics.getUsers().size());
        assert(usersLogistics.getUsers().get(0) instanceof Provider);
        assert(usersLogistics.getUsers().get(1) instanceof Client);
    }

    @Test
    public void createNewAdmin() {
        usersLogistics.createNewAdmin("Kostas", "1234");
        assertEquals(1, usersLogistics.getUsers().size());
        assert(usersLogistics.getUsers().get(0) instanceof Admin);
    }

    @Test
    public void setCurrentUser() {
        usersLogistics.createNewProvider("Kostas", "1234", "Around the corner",true,false);
        usersLogistics.createNewAdmin("Mike", "1234");
        usersLogistics.setCurrentUser(usersLogistics.getUsers().get(0));
        assert(usersLogistics.getCurrentUser() instanceof Provider);
        usersLogistics.setCurrentUser(usersLogistics.getUsers().get(1));
        assert(usersLogistics.getCurrentUser() instanceof Admin);
    }

    @Test
    public void getCurrentUser() {
        usersLogistics.createNewProvider("Kostas", "1234", "Around the corner",true,false);
        usersLogistics.createNewAdmin("Mike", "1234");
        usersLogistics.setCurrentUser(usersLogistics.getUsers().get(0));
        assert (usersLogistics.getCurrentUser() instanceof Provider);
        usersLogistics.setCurrentUser(usersLogistics.getUsers().get(1));
        assert (usersLogistics.getCurrentUser() instanceof Admin);
    }

    @Test
    public void getUsers() {
        usersLogistics.createNewProvider("Kostas", "1234", "Around the corner",true,false);
        usersLogistics.createNewAdmin("Mike", "1234");
        assertEquals(2, usersLogistics.getUsers().size());
    }

    @Test
    public void getActiveUsersArray() {
        usersLogistics.createNewProvider("Kostas", "1234", "Around the corner",true,false);
        usersLogistics.createNewClient("Xenia", "5555", "egnatia", false, true);
        usersLogistics.createNewAdmin("Mike", "1234");
        assertEquals(1, usersLogistics.getActiveUsersArray().size());
    }
    @Test
    public void getNameFromIdTest() {
        usersLogistics.createNewProvider("Kostas", "1234", "Around the corner",true,false);
        usersLogistics.getUsers().stream().filter(users -> users.getLoginName().equals("Kostas")).collect(Collectors.toCollection(ArrayList::new)).get(0).getId();
        String name = usersLogistics.getNameFromId(usersLogistics.getUsers().stream().filter(users -> users.getLoginName().equals("Kostas")).collect(Collectors.toCollection(ArrayList::new)).get(0).getId());
        assertEquals("Kostas", name);
    }
}