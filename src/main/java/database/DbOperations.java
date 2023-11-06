package database;

import entity.ISSPositionEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DbOperations {


    public List<ISSPositionEntity> ISSPositionDataToList() {

        Session session = DbConnect.getSession();
        Transaction transaction = session.beginTransaction();

        List<ISSPositionEntity> positionsFromDatabase = session.createQuery("From ISSPositionEntity").list();
        List<ISSPositionEntity> positions = positionsFromDatabase;

        transaction.commit();
        session.close();

        return positions;
    }

    public void saveToVariables() {

        Session session = sessionFactory.openSession();

        try {
            session.beginTransaction();

            // Načtení dat z databáze
            List<ISSPositionEntity> positions = session.createQuery("from PositionEntity", ISSPositionEntity.class).list();

            // Procházení načtených dat a uložení do proměnných
            for (ISSPositionEntity position : positions) {
                int id = position.getId();
                double latitude = position.getLatitude();
                double longitude = position.getLongitude();
                Date timestamp = position.getTimestamp();
                int status = position.getStatus();

                // Zde můžete provést operace s načtenými daty
                System.out.println(id + "\t" + latitude + "\t" + longitude + "\t" + timestamp + "\t" + status);
            }

            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }
//---------------------Vyhledat vsechny stanice---------------------

    private static void printAllCrafts() {
        Session session = DbConnect.getSession();
        Transaction transaction = session.beginTransaction();

        List<CraftEntity> crafts = session.createQuery("FROM CraftEntity").getResultList();

        for (CraftEntity craft : crafts) {
            System.out.println("Craft: " + craft.getName());
        }

        transaction.commit();
        session.close();
    }

//---------------------Vyhledat vsechny lidi---------------------

    private static void printAllPeople() {
        Session session = DbConnect.getSession();
        Transaction transaction = session.beginTransaction();

        List<PersonEntity> crafts = session.createQuery("FROM PersonEntity").getResultList();

        for (PersonEntity person : people) {
            System.out.println("Person: " + person.getName() + ", Craft: " +person.getCraft());
        }

        transaction.commit();
        session.close();
    }

//---------------------Vyhledat vsechny lidi dle stanice---------------------
/*
    private static void selects2Tables(Session session){

        MovieEntity movie = session.find(MovieEntity.class, 1);

        System.out.println("----------------------------------");
        System.out.println("FILM-> idfilmu=" + movie.getId() + ";  jmenofilmu=" + movie.getName() + "; directorname=" + movie.getDirector().getName());
        System.out.println("----------------------------------");


        DirectorEntity director = session.find(DirectorEntity.class, 2);
        System.out.println("REJZA-> id=" + director.getId() + "; jmeno=" + director.getName());

        if(director.getMovies()!=null && director.getMovies().size()>0){
            director.getMovies().forEach(movieEntity -> {
                System.out.println("           - film=" + movieEntity.getName() );
            });
        }
    }
*/
//---------------------Vyhledat lidi dle jmena---------------------

    private static void printPersonByName(String personName) {
        Session session = DbConnect.getSession();
        Transaction transaction = session.beginTransaction();

        PersonEntity person = session.find(PersonEntity.class, personName);
        System.out.println("Person: " +person.getNAme()+ ");
        transaction.commit();
        session.close();
    }

//---------------------Pridani nove stanice---------------------

    private static CraftEntity insertNewCraft(String craftName){
        Session session = DbConnect.getSession();
        Transaction transaction = session.beginTransaction();

        CraftEntity newCraft = new CraftEntity();                                   // vytvoreni nove stanice
        newCraft.setName(craftName);                                                // nastaveni jmena stanice
        newCraft.setPosition(null);                                                 // nastaveni polohy stanice
        session.persist(newCraft);                                                  // ulozeni do db

        transaction.commit();
        session.close();
    }

//---------------------Pridani noveho cloveka a k nemu stanici---------------------

    private static void addPersonAndItsCraft(String personName, String craftName){
        Session session = DbConnect.getSession();
        Transaction transaction = session.beginTransaction();

        CraftEntity craft = session.find(CraftEntity.class, craftName);             // nacteni stanice z db
        PersonEntity newPerson = new PersonEntity();                                // vytvoreni nove osoby
        newPerson.setName(personName);                                              // nastaveni jmena osoby
        newPerson.setCraft(craft);                                                  // prirazeni stanice
        session.persist(newPerson);                                                 // ulozeni do db

        transaction.commit();
        session.close();
    }

//---------------------Update jmena stanice---------------------

    private static void updateCraftName(String oldName, String newName){
        Session session = DbConnect.getSession();
        Transaction transaction = session.beginTransaction();

        CraftEntity craft = session.find(CraftEntity.class, oldName);               // nacteni z db
        craft.setName(newName);                                                     // zmena jmena
        session.persist(craft);                                                     // ulozeni do db

        transaction.commit();
        session.close();
    }

//---------------------Update jmena cloveka---------------------

    private static void updatePersonName(String oldName, String newName){
        Session session = DbConnect.getSession();
        Transaction transaction = session.beginTransaction();

        PersonEntity person = session.find(PersonEntity.class, oldName);            // nacteni z db
        craft.setName(newName);                                                     // zmena jmena
        session.persist(person);                                                    // ulozeni do db

        transaction.commit();
        session.close();
    }

//---------------------Update zmena stanice u cloveka---------------------

    private static void updatePersonCraft(String personName, String craftName) {
        Session session = DbConnect.getSession();
        Transaction transaction = session.beginTransaction();
        
        PersonEntity person = session.find(PersonEntity.class, personName);         // nacteni person z db
        CraftEntity craft = session.find(CraftEntity.class, craftName);             // nacteni stanice z db
        person.setCraft(craft);                                                     // nastaveni stanice pro person
        session.persist(person);                                                    // ulozeni person do db

        transaction.commit();
        session.close();
    }

//---------------------Smazat stanici---------------------

    private static void deleteCraftByName(String craftName) {
        Session session = DbConnect.getSession();
        Transaction transaction = session.beginTransaction();

        CraftEntity craft = session.find(CraftEntity.class, craftName);             // vyhledani stanice v db
        if(craft!=null)                                                             // pokud existuje v db
            session.remove(craft);                                                  // mazeme zaznam dle entity

        transaction.commit();
        session.close();
    }

//---------------------Smazat cloveka---------------------

    private static void deletePersonByName(String personName) {
        Session session = DbConnect.getSession();
        Transaction transaction = session.beginTransaction();

        PersonEntity person = session.find(PersonEntity.class, personName);          // vyhledani osoby v db
        if(person!=null)                                                             // pokud existuje v db
            session.remove(person);                                                  // mazeme zaznam dle entity

        transaction.commit();
        session.close();
    }

//---------------------Smazat vsechny lidi---------------------

    private void deletePeople(){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "DELETE FROM PersonEntity";
        Query query = session.createQuery(hql);
        int deletedRows = query.executeUpdate();

        transaction.commit();
        session.close();
    }
    
//---------------------Smazat vsechny stanice---------------------

    private void deleteCrafts(){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "DELETE FROM CraftEntity";
        Query query = session.createQuery(hql);
        int deletedRows = query.executeUpdate();

        transaction.commit();
        session.close();
    }
    
//---------------------Smazat polohy z databaze---------------------

    private void deletePositionDatabase(){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "DELETE FROM ISSPositionEntity";
        Query query = session.createQuery(hql);
        int deletedRows = query.executeUpdate();

        transaction.commit();
        session.close();
    }
  
}
