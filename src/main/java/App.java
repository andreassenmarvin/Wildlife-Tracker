import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        staticFileLocation("/public");

        //homepage
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());


        //rangers
        get("/create/ranger", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "rangers-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/create/ranger/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String badge_number = request.queryParams("badge");
            String phone_number = request.queryParams("phone");
            Rangers ranger = new Rangers(name, badge_number, phone_number);
            ranger.save();
            return new ModelAndView(model, "rangers-form.hbs");
        }, new HandlebarsTemplateEngine());
        get("/view/rangers", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("rangers", Rangers.all());
            return new ModelAndView(model, "rangers.hbs");
        }, new HandlebarsTemplateEngine());
        get("/view/ranger/sightings/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfRanger = Integer.parseInt(request.params(":id"));
            Rangers foundRanger = Rangers.find(idOfRanger);
            List<Sightings> sightings = foundRanger.getRangerSightings();
            ArrayList<String> animals = new ArrayList<>();
            ArrayList<String> types = new ArrayList<>();
            for (Sightings sighting : sightings) {
                String animal_name = Animals.find(sighting.getAnimal_id()).getName();
                String animal_type = Animals.find(sighting.getAnimal_id()).getType();
                animals.add(animal_name);
                types.add(animal_type);
            }
            model.put("sightings", sightings);
            model.put("animals", animals);
            model.put("types", types);
            model.put("rangers", Rangers.all());
            return new ModelAndView(model, "rangers.hbs");
        }, new HandlebarsTemplateEngine());


        //animals
        get("/create/animal", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "animals-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/create/animal/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String type = request.queryParams("type");
            System.out.println(type);
            String health = request.queryParams("health");
            System.out.println(health);
            String age = request.queryParams("age");
            System.out.println(age);
            String name = request.queryParams("name");
            System.out.println(name);
            if (type.equals(EndangeredAnimals.ANIMAL_TYPE)) {
                EndangeredAnimals endangered = new EndangeredAnimals(name, EndangeredAnimals.ANIMAL_TYPE, health, age);
                endangered.save();
            } else {
                Animals animal = new Animals(name, Animals.ANIMAL_TYPE);
                animal.save();
            }

            return new ModelAndView(model, "animals-form.hbs");
        }, new HandlebarsTemplateEngine());


        get("/create/animal/endangered", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<String> health = new ArrayList<>();
            health.add(EndangeredAnimals.HEALTH_HEALTHY);
            health.add(EndangeredAnimals.HEALTH_ILL);
            health.add(EndangeredAnimals.HEALTH_OKAY);
            List<String> age = new ArrayList<>();
            age.add(EndangeredAnimals.AGE_ADULT);
            age.add(EndangeredAnimals.AGE_NEWBORN);
            age.add(EndangeredAnimals.AGE_YOUNG);
            model.put("health", health);
            model.put("age", age);
            String typeChosen = "endangered";
            model.put("endangered", typeChosen);
            return new ModelAndView(model, "animals-form.hbs");
        }, new HandlebarsTemplateEngine());

        get("/view/animals", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("animals", Animals.all());
            return new ModelAndView(model, "animals.hbs");
        }, new HandlebarsTemplateEngine());


        //locations
        get("/create/location", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "locations-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/create/location/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            Locations location = new Locations(name);
            try {
                location.save();
            } catch (IllegalArgumentException e) {
                System.out.println(e);
            }

            return new ModelAndView(model, "locations-form.hbs");
        }, new HandlebarsTemplateEngine());
        get("/view/locations", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("locations", Locations.all());
            return new ModelAndView(model, "locations.hbs");
        }, new HandlebarsTemplateEngine());

        get("/view/location/sightings/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfLocation = Integer.parseInt(request.params(":id"));
            Locations foundLocation = Locations.find(idOfLocation);
            List<Sightings> sightings = foundLocation.getLocationSightings();
            ArrayList<String> animals = new ArrayList<>();
            ArrayList<String> types = new ArrayList<>();
            for (Sightings sighting : sightings) {
                String animal_name = Animals.find(sighting.getAnimal_id()).getName();
                String animal_type = Animals.find(sighting.getAnimal_id()).getType();
                animals.add(animal_name);
                types.add(animal_type);
            }
            model.put("sightings", sightings);
            model.put("animals", animals);
            model.put("types", types);
            model.put("locations", Locations.all());
            return new ModelAndView(model, "locations.hbs");
        }, new HandlebarsTemplateEngine());


        //sightings
        get("/create/sighting",(request, response) -> {
            Map<String,Object> model= new HashMap<>();
            return new ModelAndView(model,"sightings-form.hbs");
        },new HandlebarsTemplateEngine());

        post("/create/sighting/new",(request, response) -> {
            Map<String,Object> model= new HashMap<>();
            int location_id= Integer.parseInt(request.queryParams("location"));
            int ranger_id= Integer.parseInt(request.queryParams("ranger"));
            int animal_id= Integer.parseInt(request.queryParams("animal"));

            Sightings sighting=new Sightings(location_id,ranger_id,animal_id);
            sighting.save();
            model.put("rangers",Rangers.all());
            model.put("locations",Locations.all());
            model.put("animals",Animals.all());
            return new ModelAndView(model,"sightings-form.hbs");
        },new HandlebarsTemplateEngine());

        get("/view/sightings",(request, response) -> {
            Map<String,Object> model= new HashMap<>();
            List<Sightings> sightings=Sightings.all();
            ArrayList<String> animals= new ArrayList<>();
            ArrayList<String> types= new ArrayList<>();
            for (Sightings sighting : sightings){
                String animal_name=Animals.find(sighting.getAnimal_id()).getName();
                String animal_type=Animals.find(sighting.getAnimal_id()).getType();
                animals.add(animal_name);
                types.add(animal_type);
            }
            model.put("sightings",sightings);
            model.put("animals",animals);
            model.put("types",types);
            return new ModelAndView(model,"sightings.hbs");
        },new HandlebarsTemplateEngine());
    }
}
