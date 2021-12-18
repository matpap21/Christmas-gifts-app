package pl.sda.christmasgifts.service;

import org.springframework.stereotype.Service;
import pl.sda.christmasgifts.entity.Person;
import pl.sda.christmasgifts.entity.Wish;

import java.util.List;
import java.util.Optional;

@Service
public interface ChristmasGiftsService {

     Person addPerson(Person person);
     // dodawanie zyczen
    Optional<Wish> addPersonWish(Wish wish, long personId);
    Wish fulfillWishByPerson(long wishId, long personId);

    List<Wish> findAllWishes();
    List<Person> findAllGifters();

    List<Person> findAllPersons();
}
