package pl.coderslab;

import pl.coderslab.dao.UserDAO;
import pl.coderslab.models.User;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        // stworzenie dwoch uzytkownikow (powiedzie sie tylko raz, bo email musi byc unikalny!)
        User user = new User("Mariusz", "mariusz@coderslab.pl", "haslo");
        User user2 = new User("Krzysztof", "krzysztof@coderslab.pl", "haslo");

        UserDAO dao = new UserDAO();
        dao.create(user);
        dao.create(user2);

        // czytanie istniejacego uzytkownika
        // otrzymamy nowa instancje klasy User reprezentujaca ten sam rekord!
        User user3 = dao.read(user.getId());
        System.out.println(user3);

        // czytanie nieistniejacego uzytkownika
        User user100 = dao.read(100);
        System.out.println(user100);

        // modyfikacja istniejacego uzytkownika
        user3.setName("Inne imie");
        dao.update(user3);

        // modyfikacja nieistniejacego uzytkownika - nic nie powinno sie stac
        User notExisting = new User("Ja nie istnieje", "Ja nie istnieje", "haslo");
        notExisting.setId(1000);

        dao.update(notExisting);

        // pobranie wszystkich uzytkownikow z bazy i wypisanie ich na ekran
        List<User> allUsers = dao.findAll();
        System.out.println("All users: ");
        System.out.println(allUsers);

        // usuniecie uzytkownikow
        dao.delete(user.getId());
        dao.delete(user2.getId());
    }

}
