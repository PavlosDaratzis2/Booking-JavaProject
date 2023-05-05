package MyBooking.Accomodation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



import static org.junit.Assert.*;

public class AccommodationLogisticsTest {
    AccommodationLogistics accommodationLogistics;
    AccommodationLogistics accommodationLogistics2;

    @Before
    public void setUp() throws Exception {
        accommodationLogistics=new AccommodationLogistics();
        accommodationLogistics2=new AccommodationLogistics();
    }

    @After
    public void tearDown() throws Exception {
        Accomodation.code=0;
    }

    @Test
    public void createNewHotel() {
        accommodationLogistics.createNewHotel("Macedonia Palace",100,30,true,true,true,false,false,"1");
        accommodationLogistics.createNewHotel("Thessaloniki Hotel",80,28,true,true,true,false,false,"1");
        assertEquals(2, accommodationLogistics.getAccommodations().size());
        assert(accommodationLogistics.getAccommodations().get(0) instanceof Hotel);
    }

    @Test
    public void createNewRoom() {
        accommodationLogistics.createNewRoom("Paul's Rooms",50,30,false,false,false,true,false,"2");
        assertEquals(1, accommodationLogistics.getAccommodations().size());
        assert(accommodationLogistics.getAccommodations().get(0) instanceof Room);
    }

    @Test
    public void getAccommodations() {
        accommodationLogistics.createNewHotel("Macedonia Palace",100,30,true,true,true,false,false,"1");
        accommodationLogistics.createNewHouse("Helen's House",70,45,true,false,true,true,false,"3");
        accommodationLogistics2.createNewHouse("Beautiful House",70,45,true,false,true,true,false,"3");
        assertEquals(2, accommodationLogistics.getAccommodations().size());
        assertEquals(1, accommodationLogistics2.getAccommodations().size());
    }

    @Test
    public void getAllAccommodations() {
        accommodationLogistics.createNewHouse("Helen's House", 70, 45, true, false, true, true, false, "3");
        accommodationLogistics.createNewHotel("Macedonia Palace", 100, 30, true, true, true, false, false, "1");
        accommodationLogistics.createNewRoom("Paul's Rooms", 50, 30, false, false, false, true, false, "2");
        accommodationLogistics2.createNewHouse("Helen2's House", 70, 45, true, false, true, true, false, "3");
        assertEquals(3, accommodationLogistics.getAccommodations().size());
        assertEquals(1, accommodationLogistics2.getAccommodations().size());
    }
     @Test
    public void getAccommodationsProvider(){
         accommodationLogistics.createNewHotel("Macedonia Palace",100,30,true,true,true,false,false,"1");
         accommodationLogistics.createNewHouse("Helen's House",70,45,true,false,true,true,false,"3");
         accommodationLogistics.createNewHouse("Beautiful house",95,60,true,false,true,true,false,"3");
         assertEquals(2,accommodationLogistics.getAccommodationsProvider("3").size());


    }

    @Test
    public void getAccommodation() {
        Accomodation a;
        accommodationLogistics.createNewHotel("Macedonia Palace",100,30,true,true,true,false,false,"1");
        accommodationLogistics.createNewHouse("Helen's House",70,45,true,false,true,true,false,"3");
        a= accommodationLogistics.getAccommodation("3");
        assertEquals(a,accommodationLogistics.getAccommodation("3"));
        assertNull(accommodationLogistics.getAccommodation("4"));

    }


}