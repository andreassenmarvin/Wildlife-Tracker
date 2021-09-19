import org.junit.rules.ExternalResource;
import org.sql2o.Sql2o;
import org.sql2o.*;
public class DatabaseRule extends ExternalResource {

    @Override
    public void before(){
        DB.sql2o=new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test", "moringa", "family");
    }

    @Override
    public void after(){
        try (Connection con = DB.sql2o.open()){
            String deleteAnimalQuery="DELETE FROM animals ";
            String deleteSightingsQuery="DELETE FROM sightings *";
            String deleteRangerQuery="DELETE FROM rangers";
            con.createQuery(deleteAnimalQuery).executeUpdate();
            con.createQuery(deleteSightingsQuery).executeUpdate();
            con.createQuery(deleteRangerQuery).executeUpdate();
            String deleteLocationQuery="DELETE FROM locations *";
            con.createQuery(deleteLocationQuery).executeUpdate();
        }
    }

}