package com.library;

public class Main {

    public static void main(String[] args) {
        Borrower oma = new Borrower("Oma", "Salifu", "junior student");
        Borrower david = new Borrower("David", "Odohi", "Teacher");
        Borrower ifeanyi = new Borrower("Ifeanyi", "Ibe", "Senior Student");
        Borrower emmanuel = new Borrower("Emmanuel", "Ogheneovo", "teacher");
        Borrower jeniffer = new Borrower("Jeniffer", "Ezeobi", "senior student");
        Borrower ome = new Borrower("Omenebele", "Ananenu", "junior Student");
        Borrower okoro = new Borrower("Okoro", "Abbey", "Senior Student");
        Book book = new Book("Avatar", "James Cypher", "1997");
        Book book1 = new Book("Things fall apart", "Chinua Achebe", "1985");
        Book book2 = new Book("Riversdale", "Mike Thomase", "1997");
        Book book3 = new Book("The chronicles", "Karl Eindhovewt", "1948");
        Book book4 = null;
        Library library = new Library();
        library.addBook.apply(book, 5);
        library.addBook.apply(book, 7);
        library.addBook.apply(book1, 3);
        library.addBook.apply(book2, 3);
        library.addBook.apply(book2, 7);
        library.totalBooksTaken.apply(book);
        library.addToPriority.apply(book1, oma);
        library.addToPriority.apply(book1, okoro);
        library.addToPriority.apply(book1, ifeanyi);
        library.addToPriority.apply(book3, ome);
        library.addToPriority.apply(book3, jeniffer);
        library.addToPriority.apply(book1, emmanuel);
        library.addToPriority.apply(book2, david);
        library.addToPriority.apply(book3, ome);
        library.selectFromPriority.apply(book3);
        library.addToPriority.apply(book3, emmanuel);
        library.selectFromPriority.apply(book);
        library.addToPriority.apply(book3, emmanuel);
        Borrower val3 = library.selectFromPriority.apply(book2);
        System.out.println(val3);
        System.out.println("********************");
        library.printBorrowersNames.accept(book3);
        System.out.println("********************");
        library.addToQueue.apply(book1, ome);
        library.addToQueue.apply(book1, david);
        library.addToQueue.apply(book3, okoro);
        library.selectFromQueue.apply(book1);
        library.selectFromQueue.apply(book1);
        library.selectFromQueue.apply(book3);
        library.addToPriority.apply(book, emmanuel);
        library.returnBook.apply(emmanuel, book);
        library.selectFromPriority.apply(book);
        library.addToPriority.apply(book2, emmanuel);
        library.returnBook.apply(emmanuel, book);
        library.selectFromPriority.apply(book1);
        library.totalBooksTaken.apply(book);
        library.addToPriority.apply(book1, emmanuel);
        library.returnBook.apply(emmanuel, book2);
        library.selectFromPriority.apply(book);
        library.selectFromPriority.apply(book);
        library.totalBooksTaken.apply(book1);
        library.printBorrowersNames.accept(book1);
    }
}
