package pl.sda.christmasgifts.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.christmasgifts.entity.Person;
import pl.sda.christmasgifts.entity.Wish;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface ChristmasGiftsService {

     Person addPerson(Person person);
     // dodawanie zyczen

    @Transactional
    // dodajemy nowe zyczenie do istniejacej w bazie osoby
    Optional<Wish> addPersonWish(Wish wish, UUID personId);

    Wish fulfillWishByPerson(long wishId, long personId);

    List<Wish> findAllWishes();
    List<Person> findAllGifters();

    List<Person> findAllPersons();
}
