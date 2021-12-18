package pl.sda.christmasgifts.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.christmasgifts.entity.Person;
import pl.sda.christmasgifts.entity.Wish;
import pl.sda.christmasgifts.repository.PersonRepository;
import pl.sda.christmasgifts.repository.WishRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class JpaChristmasGiftsService implements ChristmasGiftsService{
    // wstrzykiwanie construktorow za pomoca repozytorowoi
    private final WishRepository wishRepository;
    private final PersonRepository personRepository;

    public JpaChristmasGiftsService(WishRepository wishRepository, PersonRepository personRepository) {
        this.wishRepository = wishRepository;
        this.personRepository = personRepository;
    }

    @Override
    public Person addPerson(Person person) {
        person.setId(UUID.randomUUID());
        return personRepository.save(person);
    }

    @Override
    @Transactional
    // dodajemy nowe zyczenie do istniejacej w bazie osoby
    public Optional<Wish> addPersonWish(Wish wish, UUID personId) {
        final Optional<Person> optionalPerson = personRepository.findById(personId);
        if (!optionalPerson.isPresent()) {
            return Optional.empty();
        } else {
            Person person = optionalPerson.get();
            wish.setOwner(person);
           return Optional.of(wishRepository.save(wish));
        }
    }

    @Override
    public Wish fulfillWishByPerson(long wishId, long personId) {
        return null;
    }

    @Override
    public List<Wish> findAllWishes() {
        return null;
    }

    @Override
    public List<Person> findAllGifters() {
        return null;
    }
    @Override
    public List<Person> findAllPersons(){
        return personRepository.findAll();
    }

}
