package main;

import configuration.JPAConfig;
import entity.BookEntity;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.web.config.SpringDataJacksonConfiguration;
import org.springframework.stereotype.Repository;
import repository.BookRepository;

import javax.security.sasl.SaslServer;
import java.awt.print.Book;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class MainCRUD {
    static ApplicationContext context = new AnnotationConfigApplicationContext(JPAConfig.class);
    static BookRepository bookRepository = (BookRepository) context.getBean("bookRepository");

    private static void createNewBook(){
        BookEntity bookEntity = new BookEntity();
        bookEntity.setName("Java");
        bookEntity.setAuthor("John");
        bookEntity.setCategory("IT books");
        bookEntity.setIsbn("ISIBF1219323");
        bookEntity.setNumberOfPage(234);
        bookEntity.setPrice(20.5);
        bookEntity.setPublishDate(LocalDate.parse("2016-08-25"));

        BookEntity result = bookRepository.save(bookEntity);

        if (result != null){
            System.out.println("A new book saved successfully, book ID = " + bookEntity.getId());
        }
    }
    private static void createNewBook2(){
        BookEntity bookEntity = new BookEntity();
        bookEntity.setName("Linux");
        bookEntity.setAuthor("Bruno");
        bookEntity.setCategory("IT books");
        bookEntity.setIsbn("ISIBF12232323");
        bookEntity.setNumberOfPage(135);
        bookEntity.setPrice(12.6);
        bookEntity.setPublishDate(LocalDate.parse("2016-08-25"));

        BookEntity result = bookRepository.save(bookEntity);

        if (result != null){
            System.out.println("A new book saved successfully, book ID = " + bookEntity.getId());
        }
    }
    private static void createNewBook3(){
        BookEntity bookEntity = new BookEntity();
        bookEntity.setName("Math");
        bookEntity.setAuthor("Tommy");
        bookEntity.setCategory("IT books");
        bookEntity.setIsbn("IS232F1219323");
        bookEntity.setNumberOfPage(65);
        bookEntity.setPrice(20.9);
        bookEntity.setPublishDate(LocalDate.parse("2016-08-25"));

        BookEntity result = bookRepository.save(bookEntity);

        if (result != null){
            System.out.println("A new book saved successfully, book ID = " + bookEntity.getId());
        }
    }
    private static void createNewBook4() {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setName("Java A-Z");
        bookEntity.setAuthor("Roger");
        bookEntity.setCategory("IT books");
        bookEntity.setIsbn("ISIBF121131212");
        bookEntity.setNumberOfPage(150);
        bookEntity.setPrice(130);
        bookEntity.setPublishDate(LocalDate.parse("2016-08-25"));

        BookEntity result = bookRepository.save(bookEntity);
    }
        private static void readBook(){
            List<BookEntity> bookList = (List<BookEntity>) bookRepository.findAll();
            System.out.println("Found " + bookList.size() + "book(s) in the table book");
            System.out.println("They are: ");
            for(BookEntity book : bookList){
                System.out.println(book.toString());
            }
        }

        private static void readBook(int bookID){
            Optional<BookEntity> bookEntity = bookRepository.findById(bookID);
            if(bookEntity != null){
                System.out.println("Found a book with book ID = " + bookID);
                System.out.println(bookEntity.toString());
            }else {
                System.out.println("Not found any book with book ID = " + bookID);
            }
        }
    private static void updateBook(){
        Optional<BookEntity> optionalBook = bookRepository.findById(1);
        if(optionalBook.isPresent()){
            BookEntity bookEntity = optionalBook.get();
            System.out.println("Book data before updating");
            System.out.println(bookEntity.toString());

            bookEntity.setName("Java");
            bookEntity.setAuthor("Jame");
            bookEntity.setNumberOfPage(199);
            bookEntity.setPrice(100);
            bookEntity.setIsbn("121131212");
            bookRepository.save(bookEntity);

            System.out.println("Book data after updating");
            System.out.println(bookEntity.toString());
        }else{
            System.out.println("Book not found with ID 1");
        }
    }
    private static void deleteBook(){
        Optional<BookEntity> optionalBook = bookRepository.findById(1);
        optionalBook.ifPresent(bookRepository::delete);

        Optional<BookEntity> optionalBook2 = bookRepository.findById(2);
        if(optionalBook2.isPresent()){
            BookEntity bookEntity = optionalBook2.get();
            bookRepository.delete(bookEntity);
        }
        bookRepository.deleteAll();
    }
    //        Tìm sách theo tên
    private static void findByAuthor(){
        List<BookEntity> bookList = bookRepository.findByAuthor("Roger");
        if (!bookList.isEmpty()) {
            System.out.println("Found " + bookList.size() + "book(s) of Roger");
            System.out.println("They are : ");
            for (BookEntity bookEntity : bookList) {
                System.out.println(bookEntity.toString());
            }
        }
    }

    //        Tìm sách theo tên và giá
    private static void findByAuthorAndPrice(){
        List<BookEntity>bookListAuAndPr = bookRepository.findByAuthorAndPrice("Roger",100);
        if(bookListAuAndPr.size() != 0){
            System.out.println("Found " + bookListAuAndPr.size() + "book(s) of Roger");
            System.out.println("They are : ");
            for(BookEntity bookEntity : bookListAuAndPr){
                System.out.println(bookEntity.toString());
            }
        }
    }
    //            Tìm sách theo giá hoặc số trang
    private static void findByPriceOrNumberOfPage(){
        List<BookEntity>bookListPrOrPage =bookRepository.findByPriceOrNumberOfPage(100,150);
        if(bookListPrOrPage.size() != 0){
            System.out.println("Tim thay " + bookListPrOrPage.size() + "gia sach 100 hoac so trang 150");
            System.out.println("They are: ");
            for(BookEntity bookEntity : bookListPrOrPage){
                System.out.println(bookEntity.toString());
            }
        }
    }
//    Tìm sách có giá nhỏ hơn 100
        private static void findByPriceLessThan(){
        List<BookEntity>bookListPrLessThan = bookRepository.findByPriceLessThan(100);
        if(bookListPrLessThan.size() != 0){
            System.out.println("Found " + bookListPrLessThan.size() + "Price Less Than 100");
            System.out.println("They are ");
            for (BookEntity bookEntity:bookListPrLessThan){
                System.out.println(bookEntity.toString());
            }
        }
    }
    //            Tim sách có giá lớn hơn 120
    private static void findByPriceGreaterThanEqual(){
        List<BookEntity>bookListPrGreatThanEqual = bookRepository.findByPriceGreaterThanEqual(120);
        if(!bookListPrGreatThanEqual.isEmpty()){
            System.out.println("Found " + bookListPrGreatThanEqual.size() + "book(s) Price Great Than Equal 120");
            System.out.println("They are: ");
            for(BookEntity bookEntity:bookListPrGreatThanEqual){
                System.out.println(bookEntity.toString());
            }
        }
    }
//    tìm sách có chứa ký tự ja
    private static void findByContaining(){
        List<BookEntity>bookListNameContaining = bookRepository.findByNameContaining("ja");
        if(bookListNameContaining.size() != 0){
            System.out.println("Found " + bookListNameContaining.size() + "book(s) Name Containing ja");
            System.out.println("They are: ");
            for (BookEntity bookEntity: bookListNameContaining){
                System.out.println(bookEntity.toString());
            }
        }
    }
//    tìm sách só Isbn là 121131212
    private static void findByIsbn(){
        BookEntity bookfindByIsbn = bookRepository.findByIsbn("121131212");
        if(bookfindByIsbn != null){
            System.out.println("Found " + bookfindByIsbn.getIsbn() + "book(s) Isbn is 121131212");
            System.out.println("They are: ");
            System.out.println(bookfindByIsbn.toString());
        }
    }
    //            tìm sách có ngày sản xuất sau 2015-12-12
    private static void findByPublishDateAfter(){
        List<BookEntity>bookListPublishDateAfter = bookRepository.findByPublishDateAfter(LocalDate.ofEpochDay(2015-12-12));
        if(bookListPublishDateAfter != null){
            System.out.println("Found " + bookListPublishDateAfter.size() + "book(s) Publish Date After 2015-12-12");
            System.out.println("They are: ");
            for (BookEntity bookEntity: bookListPublishDateAfter){
                System.out.println(bookEntity.toString());
            }
        }
    }
//        Tìm sách ko chứa name Java
        private static void findByNameNot(){
        List<BookEntity> bookNameNotIn = bookRepository.findByNameNot("Java");
        if (!bookNameNotIn.isEmpty()){
            System.out.println("Find " + bookNameNotIn.size() + "book(s)");
            for(BookEntity bookEntity:bookNameNotIn){
                System.out.println(bookEntity.toString());
            }
        }else{
            System.out.println("khong co");
        }
    }
        private static void findByPublishDateBefore(){
        List<BookEntity> bookPublishDateBefore = bookRepository.findByPublishDateBefore(LocalDate.now());
        if (!bookPublishDateBefore.isEmpty()){
            System.out.println("Find " + bookPublishDateBefore.size() + "book(s)");
            for (BookEntity bookEntity: bookPublishDateBefore){
                System.out.println(bookEntity.toString());
            }
        }
        }
        private static void findByPriceGreaterThan(){
        List<BookEntity> bookPriceGreaterThan = bookRepository.findByPriceGreaterThan(100);
        if (!bookPriceGreaterThan.isEmpty()){
            System.out.println("Find " + bookPriceGreaterThan.size() + "book(s)");
            for (BookEntity bookEntity:bookPriceGreaterThan){
                bookEntity.setPrice(90);
            }
            bookRepository.saveAll(bookPriceGreaterThan);
        }
        }
        private static void deleteByNameAndAuthor(){
        List<BookEntity> bookNameAndAuthor = bookRepository.deleteByNameAndAuthor("Java","John");
        if (!bookNameAndAuthor.isEmpty()){
            bookRepository.deleteAll(bookNameAndAuthor);
        }
        }
    public static void main (String[] args) {
//        createNewBook();
//        createNewBook2();
//        createNewBook3();
//        createNewBook4();
//        readBook();
//        updateBook();
//        deleteBook();
//        findByNameNot();
    }
}
