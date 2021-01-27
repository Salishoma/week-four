package com.library;

public class Borrower implements Comparable<Borrower> {
    private final String firstName;
    private final String lastName;
    private final String designation;
    public int rank;

    public Borrower(String firstName, String lastName, String designation) {
        String des = String.join("", designation.split(" ")).toLowerCase();

        // assign rank based on destination
        switch (des){
            case "teacher":
                this.rank = 1;
                break;
            case "seniorstudent":  case "senior":
                this.rank = 2;
                break;
            case "juniorstudent": case "junior":
                this.rank = 3;
                break;
            default:
                throw new IllegalArgumentException(firstName + " " + lastName + " is not authorised to perform this action");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.designation = designation;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getRank() {
        return this.rank;
    }

    public String getFullName(){
        return getFirstName() + " " + getLastName();
    }

    @Override
    public int compareTo(Borrower o) {
        if(o.rank < this.rank) {
            return 1;
        }else if(o.rank > getRank()){
            return -1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return
                "First Name=" + firstName +
                ", Last Name=" + lastName +
                ", designation='" + designation + '\'' +
                ", rank=" + rank;
    }
}
