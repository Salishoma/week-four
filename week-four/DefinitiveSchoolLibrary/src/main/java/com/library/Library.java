package com.library;

import java.util.*;
import java.util.function.*;

public class Library {
    private final HashMap<Book, Integer> availableCopies = new HashMap<>(); // keeps track of copies of a particular book available in library
    private final HashMap<Borrower, ArrayList<Book>> borrowersCollection = new HashMap<>(); // keeps track of collection of books by borrower
    private final HashMap<Book, PriorityQueue<Borrower>> priorityRequest = new HashMap<>(); //List of potential borrowers of books in order of priority
    private final HashMap<Book, Queue<Borrower>> queueRequest = new HashMap<>(); //List of potential borrowers of books without priority
    private final HashMap<Book, Integer> noOfCopies = new HashMap<>(); // keeps track of total copies supplied to the library

    //Creating Library constructor
    public Library() {
        System.out.println("**********************************************************");
        System.out.println("Welcome to magnifico library, ranked one of the best libraries in the world. In this library, we have" +
                " lots of books you can ever imagine.\nHop in and enjoy the ride");
        System.out.println("**********************************************************");
    }

    //add books to the list of collections in library
    BiFunction<Book, Integer, String> addBook = (book, copies) -> {
        //Increase total number of copies as well as the copies currently present in library
       try{
           noOfCopies.put(book, noOfCopies.getOrDefault(book, 0) + copies);
           availableCopies.put(book, availableCopies.getOrDefault(book, 0) + copies);
           System.out.println(copies + " books of " + book.getName() + " published by " + book.getAuthor() + " has been added to library book collections," +
                   " available copies: " + availableCopies.get(book));
           return book.getName();
       }catch (Exception e){
           System.out.println("Value passed not valid: " + e);
       }
       return "";
    };

    // Request is made to borrow book, check if eligible before adding to list
    BiFunction<Book, Borrower, Boolean> addToPriority = (book, borrower) -> addToList(book, borrower, "makeRequest");

    //If there is no one in priority queue, no books will be given out
    Function<Book, Borrower> selectFromPriority = book -> selectFromList(book, "selectFromPriority");

    BiFunction<Borrower, Book, Boolean> returnBook = (borrower, book) -> {
        /*1. Check if the person is among those who are yet to return borrowed books from the library
         * 2.Check if the book he is returning is among the books he borrowed
         * 3. Check if that particular collection of book is already complete in the library
         */
        if(borrowersCollection.containsKey(borrower) && borrowersCollection.get(borrower).contains(book)
                && noOfCopies.get(book) > availableCopies.get(book)){
            System.out.println("**********************************************************");
            System.out.println(borrowersCollection.get(borrower).size());
            borrowersCollection.get(borrower).remove(book);
            availableCopies.put(book, availableCopies.getOrDefault(book, 0) + 1);
            System.out.println(borrower.getFullName() + " has returned " + book.getName() +". Remaining books to return is: " + borrowersCollection.get(borrower).size());
            System.out.println("**********************************************************");
            return true;
        }
        System.out.println("Wrong book returned");
        return false;
    };

    // Print names of borrowers of a particular book
    Consumer<Book> printBorrowersNames = (book) -> {
        try{
            System.out.println(priorityRequest.get(book).size() == 0 ? "The book \""+ book.getName() + "\" is complete in shelf" :
                    "Name" + (priorityRequest.get(book).size() > 1 ? "s" : "") + " of lender of " + book.getName() +
                    (priorityRequest.get(book).size() > 1 ? " are " : " is") );
            priorityRequest.get(book).forEach(lender -> System.out.println(lender.getFullName()));
        }catch (Exception e){
            System.out.println("illegal argument exception");
        }
    };

    // Returns the total number of copies of a particular book that has been taken from the library
    Function<Book, Integer> totalBooksTaken = book -> {
        try{
            if(!noOfCopies.containsKey(book)){
                System.out.println(book.getName() + " is not yet in our collections");
                return 0;
            }
            int taken = noOfCopies.get(book) - availableCopies.get(book);
            System.out.println("**********************************************************");
            System.out.println((taken == 0 ? "zero" : taken)  +" \"" + book.getName() + "\" " + (taken < 2 ? "book has been " : "books have been ") + "taken from the library\n" +
                    "total \"" +  book.getName() +  "\" books available: " + availableCopies.get(book));
            System.out.println("**********************************************************");
            return taken;
        }catch(Exception e){
            System.out.println("Invalid argument passed: " + e);
        }
        return 0;
    };

    // Check if the borrower is eligible before adding to queue
    BiFunction<Book, Borrower, Boolean> addToQueue = (book, borrower) -> addToList(book, borrower, "addToQueue");

    // Gives the book to the borrower that meets conditions for collecting book
    Function<Book, Borrower> selectFromQueue = book -> selectFromList(book, "selectFromQueue");

    // Adds the potential borrower to the list or queue depending on the type of operation it is called to perform
    private boolean addToList(Book book, Borrower borrower, String typeOfOperation) {

        try {
            if(!noOfCopies.containsKey(book) || availableCopies.get(book) == 0){
                System.out.println("Book not in library");
                return false;
            }
            if (!(borrowersCollection.containsKey(borrower)) || borrowersCollection.get(borrower).size() < 3) {
                // check the type of operation to be performed
                if (typeOfOperation.equals("makeRequest")) {
                    PriorityQueue<Borrower> priority = null;
                    if (!priorityRequest.containsKey(book)) {
                        priority = new PriorityQueue<>();
                    } else {
                        priority = priorityRequest.get(book);
                    }
                    priority.add(borrower);
                    priorityRequest.put(book, priority);
                } else {
                    Queue<Borrower> queue = null;
                    if (!queueRequest.containsKey(book)) {
                        queue = new LinkedList<>();
                    } else {
                        queue = queueRequest.get(book);
                    }
                    queue.add(borrower);
                    queueRequest.put(book, queue);
                    queue.add(borrower);
                }
                if (!(borrowersCollection.containsKey(borrower))) {
                    ArrayList<Book> book1 = new ArrayList<>();
                    borrowersCollection.put(borrower, book1);
                }
                return true;
            }
            System.out.println(borrower.getFullName() + " is not eligible to borrow books from the library");
            if (typeOfOperation.equals("makeRequest")) {
                priorityRequest.get(book).remove(borrower);
            } else {
                queueRequest.get(book).remove(borrower);
            }
        } catch (Exception e) {
            System.out.println("Exception thrown while passing null: " + e);
        }
        return false;
    }

    //attends to borrower's need if eligible, then remove from queue
    private Borrower selectFromList(Book book, String typeOfOperation){
        //If there is no one in list, no book will be given out
        if(typeOfOperation.equals("selectFromPriority")){
            if(!priorityRequest.containsKey(book) || priorityRequest.get(book).peek() == null){
                return null;
            }
        }else if(!queueRequest.containsKey(book) || queueRequest.get(book).peek() == null){
            return null;
        }
        Borrower person = null;

        //If the book to be borrowed is not in library's collection, then no book is handed out
        if(!availableCopies.containsKey(book)){
            System.out.println("\"" + book.getName() + "\" is not yet part of this library's collection.");
            return null;
        }
        int copiesRemaining = availableCopies.get(book);

        //If book required from the library is currently available, then it can be given out to the first person
        if(copiesRemaining > 0){
            person = typeOfOperation.equals("selectFromPriority") ? priorityRequest.get(book).poll() : queueRequest.get(book).poll();

            //If the first person has borrowed a similar copy, then deny request
            if(borrowersCollection.containsKey(person) && borrowersCollection.get(person).contains(book)){
                assert person != null;
                System.out.println(person.getFullName() + ", you already have this book in your list of collections");
                if (typeOfOperation.equals("selectFromPriority")) {
                    priorityRequest.get(book).remove();
                } else {
                    queueRequest.get(book).remove(person);
                }
                return person;
            }

            // reduce the number of available copies
            availableCopies.put(book, copiesRemaining - 1);
            if (typeOfOperation.equals("selectFromPriority")) {
                priorityRequest.get(book).remove(person);
            } else {
                queueRequest.get(book).remove(person);
            }
            //Add borrower to list of borrowers
            addToBorrowersCollection.accept(person, book);
        }else{
            System.out.println("\"" + book.getName() + "\" not in shelf.");
        }
        return person;
    }

    // Method to add borrower to list of borrowers
    BiConsumer<Borrower, Book> addToBorrowersCollection = (person, book) -> {
        ArrayList<Book> collect = borrowersCollection.get(person);
        collect.add(book);
        borrowersCollection.put(person, collect);
        System.out.println("**********************************************************");
        assert person != null;
        System.out.println(person.getFullName() + " has borrowed " + "\"" + book.getName() + "\""+ " from the library, \n"
                + "Total number of books borrowed by " +  person.getFullName() +": "
                + borrowersCollection.get(person).size() + ", total number of \"" + book.getName() + "\" available: " +
                (availableCopies.get(book)));
        System.out.println("**********************************************************");
    };
}
