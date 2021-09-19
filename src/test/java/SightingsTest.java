import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.*;

public class SightingsTest {

    @Rule
    public DatabaseRule databaseRule=new DatabaseRule();

    @Test
    public void createInstanceOfSightingsClass_true() {

        Sightings sighting= setUpNewSighting();
        assertEquals(true,sighting instanceof Sightings);
    }
    @Test
    public void Sightings_instantiatesWithAnimalId_int() {
        Sightings testSightings = new Sightings(1,1,1);
        assertEquals(1, testSightings.getAnimal_id());
    }

    @Test
    public void Sightings_instantiatesWithLocationId_int() {
        Sightings testAnimals = new Sightings(1,1,1);
        assertEquals(1, testAnimals.getLocation_id());
    }

    @Test
    public void Sightings_instantiatesWithRangerId_int() {
        Sightings testAnimals = new Sightings(1,1,1);
        assertEquals(1, testAnimals.getRanger_id());
    }

    @Test
    public  void equals_returnsTrueIfNameHealthTypeAndAgeAreSame_true(){
        Sightings testSightings = new Sightings(1,1,1);
        Sightings anotherSightings = new Sightings(1,1,1);
        assertTrue(testSightings.equals(anotherSightings));
    }

    @Test
    public void allInstancesAreSaved() {
        Sightings sightings=setUpNewSighting();
        Sightings otherSighting=new Sightings(-1,1,1);
        try {
            sightings.save();
            otherSighting.save();
            assertTrue(Sightings.find(sightings.getId()).equals(sightings));
        }catch (IllegalArgumentException e){
            System.out.println(e);
        }
    }
    //
    @Test
    public void findSightingByID() {
        Sightings sighting=setUpNewSighting();
        sighting.save();
        Sightings foundSighting=Sightings.find(sighting.getId());
        assertTrue(foundSighting.equals(sighting));

    }
    @Test
    public void deleteSightingByID() {
        Sightings sighting=setUpNewSighting();
        sighting.save();
        sighting.delete();
        assertEquals(null,Sightings.find(sighting.getId()));

    }
    @Test
    public void deleteAll() {
        Sightings sighting=setUpNewSighting();
        Sightings otherSightings=setUpNewSighting();
        sighting.save();
        otherSightings.save();
        Sightings.deleteAll();

        assertEquals(0,Sightings.all().size());
    }

    private Sightings setUpNewSighting() {
        return new Sightings(1,1,1);
    }
}