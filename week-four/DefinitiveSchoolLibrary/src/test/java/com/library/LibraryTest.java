package com.library;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    @Test
    void addBook() {
        Book book1 = new Book("The Renaisance", "James Wallace", "1985");
        Library library = new Library();
        assertEquals("The Renaisance", library.addBook.apply(book1, 7));
    }

    @Test
    void addToPriority() {
        Library library = new Library();
        Borrower emmanuel = new Borrower("Emmanuel", "Ogheneovo", "teacher");
        Book book1 = new Book("The Renaisance", "James Wallace", "1985");
        library.addBook.apply(book1, 3);
        assertTrue(library.addToPriority.apply(book1, emmanuel));

    }

    @Test
    void selectFromPriority() {
        Library library = new Library();
        Borrower oma = new Borrower("Oma", "Salifu", "junior student");
        Borrower ifeanyi = new Borrower("Ifeanyi", "Ibe", "Senior Student");
        Borrower emmanuel = new Borrower("Emmanuel", "Ogheneovo", "teacher");
        Borrower jeniffer = new Borrower("Jeniffer", "Ezeobi", "senior student");
        Borrower ome = new Borrower("Omenebele", "Ananenu", "junior Student");
        Borrower david = new Borrower("David", "Odohi", "Teacher");
        Borrower okoro = new Borrower("Okoro", "Abbey", "Senior Student");
        Book book1 = new Book("The Renaisance", "James Wallace", "1985");
        Book book2 = new Book("The Culprit", "Otto Kingsman", "1978");
        library.addBook.apply(book1, 3);
        library.addBook.apply(book2, 5);
        library.addToPriority.apply(book1, oma);
        library.addToPriority.apply(book2, ifeanyi);
        library.addToPriority.apply(book1, david);
        library.addToPriority.apply(book2, emmanuel);
        assertEquals(david, library.selectFromPriority.apply(book1));

    }

    @Test
    void returnBook() {
        Library library = new Library();
        Borrower jeniffer = new Borrower("Jeniffer", "Ezeobi", "senior student");
        Borrower ome = new Borrower("Omenebele", "Ananenu", "junior Student");
        Borrower okoro = new Borrower("Okoro", "Abbey", "Senior Student");
        Book book1 = new Book("The Renaisance", "James Wallace", "1985");
        Book book2 = new Book("Set it up", "Tom Wilkins", "1975");
        library.addBook.apply(book1, 3);
        library.addBook.apply(book1, 3);
        library.addToPriority.apply(book1, okoro);
        library.addToPriority.apply(book1, ome);
        library.addToPriority.apply(book2, jeniffer);
        library.selectFromPriority.apply(book1);
        assertTrue(library.returnBook.apply(okoro, book1));
    }

    @Test
    void totalBooksTaken() {
        Library library = new Library();
        Borrower jeniffer = new Borrower("Jeniffer", "Ezeobi", "senior student");
        Borrower ome = new Borrower("Omenebele", "Ananenu", "junior Student");
        Borrower okoro = new Borrower("Okoro", "Abbey", "Senior Student");
        Borrower emmanuel = new Borrower("Emmanuel", "Ogheneovo", "teacher");
        Book book = new Book("The Renaisance", "James Wallace", "1985");
        library.addBook.apply(book, 3);
        library.addToPriority.apply(book, jeniffer);
        library.addToPriority.apply(book, ome);
        library.addToPriority.apply(book, emmanuel);
        library.addToPriority.apply(book, jeniffer);
        library.selectFromPriority.apply(book);
        library.selectFromPriority.apply(book);
        assertEquals(2, library.totalBooksTaken.apply(book));
    }

    @Test
    void addToQueue() {
        Library library = new Library();
        Borrower jeniffer = new Borrower("Jeniffer", "Ezeobi", "senior student");
        Book book = new Book("The Renaisance", "James Wallace", "1985");
        library.addBook.apply(book, 3);
        assertTrue(library.addToQueue.apply(book, jeniffer));
    }

    @Test
    void selectFromQueue() {
        Library library = new Library();
        Borrower jeniffer = new Borrower("Jeniffer", "Ezeobi", "senior student");
        Borrower ome = new Borrower("Omenebele", "Ananenu", "junior Student");
        Borrower okoro = new Borrower("Okoro", "Abbey", "Senior Student");
        Borrower emmanuel = new Borrower("Emmanuel", "Ogheneovo", "teacher");
        Book book = new Book("The Renaisance", "James Wallace", "1985");
        library.addBook.apply(book, 3);
        library.addToPriority.apply(book, jeniffer);
        library.addToPriority.apply(book, ome);
        library.addToPriority.apply(book, emmanuel);
        library.addToPriority.apply(book, jeniffer);
        assertEquals(emmanuel, library.selectFromPriority.apply(book));
    }
}