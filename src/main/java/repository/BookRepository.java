package repository;

import entity.BookEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository

public interface BookRepository extends CrudRepository <BookEntity,Integer> {
    List<BookEntity>findByAuthor(String author);
    List<BookEntity>findByAuthorAndPrice (String author, double price);
    // author = Roger and price = 100
    List<BookEntity>findByPriceOrNumberOfPage (double price, int numOfPage);
    // price = 100 or number of page = 150
    List<BookEntity>findByPriceLessThan (double price);
    // price < 100
    List<BookEntity>findByPriceGreaterThanEqual (double price);
    // price > 120
    List<BookEntity>findByNameContaining (String searchWords);
    // book name containing ,,ja"
    BookEntity findByIsbn(String isbn);
    // isbn - 121131212
    List<BookEntity>findByPublishDateAfter (LocalDate date);
    // publish date is after 2015-12-12
    List<BookEntity>findByNameNotIn (String name);
    List<BookEntity>findByPublishDateBefore(LocalDate date);
    List<BookEntity>findByPriceGreaterThan(double price);
}

