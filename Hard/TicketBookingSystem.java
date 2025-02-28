import java.util.*;

class TicketBookingApp {
    private final int totalSeats;
    private final Set<Integer> bookedSeats;

    public TicketBookingApp(int totalSeats) {
        this.totalSeats = totalSeats;
        this.bookedSeats = new HashSet<>();
    }

    public synchronized boolean bookSeat(int seatNumber, String customerType) {
        if (seatNumber < 1 || seatNumber > totalSeats) {
            System.out.println(customerType + " attempted to book an invalid seat: " + seatNumber);
            return false;
        }
        if (!bookedSeats.contains(seatNumber)) {
            bookedSeats.add(seatNumber);
            System.out.println(customerType + " successfully booked seat: " + seatNumber);
            return true;
        } else {
            System.out.println(customerType + " attempted to book an already booked seat: " + seatNumber);
            return false;
        }
    }
}

class BookingThread extends Thread {
    private final TicketBookingApp system;
    private final int seatNumber;
    private final String customerType;

    public BookingThread(TicketBookingApp system, int seatNumber, String customerType, int priority) {
        this.system = system;
        this.seatNumber = seatNumber;
        this.customerType = customerType;
        setPriority(priority);
    }

    @Override
    public void run() {
        system.bookSeat(seatNumber, customerType);
    }
}

public class TicketBookingSystem {
    public static void main(String[] args) {
        TicketBookingApp system = new TicketBookingApp(10);
        
        Thread vip1 = new BookingThread(system, 3, "VIP", Thread.MAX_PRIORITY);
        Thread vip2 = new BookingThread(system, 5, "VIP", Thread.MAX_PRIORITY);
        Thread regular1 = new BookingThread(system, 3, "Regular", Thread.MIN_PRIORITY);
        Thread regular2 = new BookingThread(system, 5, "Regular", Thread.MIN_PRIORITY);
        Thread regular3 = new BookingThread(system, 7, "Regular", Thread.NORM_PRIORITY);
        
        vip1.start();
        vip2.start();
        regular1.start();
        regular2.start();
        regular3.start();
    }
}
