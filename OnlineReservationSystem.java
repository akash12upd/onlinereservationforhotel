import java.util.*;

class Room {
    private int roomNumber;
    private boolean isAvailable;

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
        this.isAvailable = true;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}

class Reservation {
    private int reservationId;
    private String guestName;
    private Room room;

    public Reservation(int reservationId, String guestName, Room room) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.room = room;
    }

    public int getReservationId() {
        return reservationId;
    }

    public String getGuestName() {
        return guestName;
    }

    public Room getRoom() {
        return room;
    }
}

class ReservationSystem {
    private List<Room> rooms;
    private List<Reservation> reservations;
    private int nextReservationId;

    public ReservationSystem() {
        rooms = new ArrayList<>();
        reservations = new ArrayList<>();
        nextReservationId = 1;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void removeRoom(Room room) {
        rooms.remove(room);
    }

    public void displayAvailableRooms() {
        System.out.println("Available Rooms:");
        for (Room room : rooms) {
            if (room.isAvailable()) {
                System.out.println("Room Number: " + room.getRoomNumber());
            }
        }
    }

    public boolean makeReservation(String guestName, int roomNumber) {
        Room selectedRoom = null;
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber && room.isAvailable()) {
                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom != null) {
            Reservation reservation = new Reservation(nextReservationId, guestName, selectedRoom);
            reservations.add(reservation);
            selectedRoom.setAvailable(false);
            nextReservationId++;
            System.out.println("Reservation successful. Reservation ID: " + reservation.getReservationId());
            return true;
        } else {
            System.out.println("Room not available for reservation.");
            return false;
        }
    }

    public boolean cancelReservation(int reservationId) {
        Reservation reservationToRemove = null;
        for (Reservation reservation : reservations) {
            if (reservation.getReservationId() == reservationId) {
                reservationToRemove = reservation;
                break;
            }
        }

        if (reservationToRemove != null) {
            reservationToRemove.getRoom().setAvailable(true);
            reservations.remove(reservationToRemove);
            System.out.println("Reservation cancelled successfully.");
            return true;
        } else {
            System.out.println("Reservation not found.");
            return false;
        }
    }
}

public class OnlineReservationSystem {
    public static void main(String[] args) {
        ReservationSystem reservationSystem = new ReservationSystem();

        // Create some rooms
        Room room1 = new Room(101);
        Room room2 = new Room(102);
        Room room3 = new Room(103);

        // Add rooms to the reservation system
        reservationSystem.addRoom(room1);
        reservationSystem.addRoom(room2);
        reservationSystem.addRoom(room3);

        // Display available rooms
        reservationSystem.displayAvailableRooms();

        // Make reservations
        reservationSystem.makeReservation("John", 101);
        reservationSystem.makeReservation("Alice", 102);

        // Display available rooms after reservations
        reservationSystem.displayAvailableRooms();

        // Cancel a reservation
        reservationSystem.cancelReservation(1);

        // Display available rooms after cancellation
        reservationSystem.displayAvailableRooms();
    }
}