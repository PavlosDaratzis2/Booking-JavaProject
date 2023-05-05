package MyBooking.SearchTools;

import MyBooking.Users.Admin;
import MyBooking.Users.Client;
import MyBooking.Users.Provider;
import MyBooking.Users.Users;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SearchModeUsersTest {
    Client client, client1;
    Provider provider, provider1;

    @Before
    public void setUp() throws Exception {
        client = new Client("Helen", "1111");
        client.setAddress("St Bernard's Str London");
        client1 = new Client("George", "1345");
        client1.setAddress("Thessaloniki");
        provider = new Provider("Miles", "1234");
        provider.setAddress("Kypoyroy 15, Melenekitsi");
        provider1 = new Provider("Joe", "4321");
        provider1.setAddress("Typography Lane 3");


    }

    @After
    public void tearDown() throws Exception {


    }

    @Test
    public void searchName() {
        ArrayList<Users> usersArrayList = new ArrayList<>();
        usersArrayList.add(client);
        usersArrayList.add(client1);
        usersArrayList.add(provider);
        usersArrayList.add(provider1);
        usersArrayList = new SearchModeUsers().searchName("le",usersArrayList);
        assertEquals(usersArrayList.size(), 2);
    }

    @Test
    public void searchAddress() {
        ArrayList<Users> usersArrayList1 = new ArrayList<>();
        usersArrayList1.add(client);
        usersArrayList1.add(client1);
        usersArrayList1.add(provider);
        usersArrayList1.add(provider1);
        usersArrayList1 = new SearchModeUsers().searchAddress("po",usersArrayList1);
        assertEquals(usersArrayList1.size(), 2);

    }
}