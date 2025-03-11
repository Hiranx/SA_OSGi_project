package event.consumer;

import event.producer.Event;
import event.producer.EventProviderInterface;
import sports.producer.SportsEvent;
import sports.producer.SportsEventProviderInterface;
import java.util.List;
import java.util.Scanner;

public class EventConsumer {

    private EventProviderInterface eventProvider;
    private SportsEventProviderInterface sportsEventProvider;
    private Scanner scanner;

    public EventConsumer(EventProviderInterface eventProvider, SportsEventProviderInterface sportsEventProvider) {
        this.eventProvider = eventProvider;
        this.sportsEventProvider = sportsEventProvider;
        this.scanner = new Scanner(System.in);
    }

    public void startBooking() {
        boolean continueBooking = true;

        while (continueBooking) {
            System.out.println("\nSelect event type:");
            System.out.println("1. General Events");
            System.out.println("2. Sports Events");
            System.out.println("3. Exit");

            System.out.print("Enter your choice: ");
            int categoryChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (categoryChoice == 3) {
                continueBooking = false;
                System.out.println("Thank you for using the Event Ticket Booking System!");
                break;
            }

            if (categoryChoice == 1 && eventProvider != null) {
                bookGeneralEvent();
            } else if (categoryChoice == 2 && sportsEventProvider != null) {
                bookSportsEvent();
            } else {
                System.out.println("Invalid choice or service unavailable.");
            }
        }
    }

    private void bookGeneralEvent() {
        List<Event> events = eventProvider.getEvents();

        if (events.isEmpty()) {
            System.out.println("No general events available.");
            return;
        }

        System.out.println("\nAvailable General Events:");
        for (int i = 0; i < events.size(); i++) {
            System.out.println((i + 1) + ". " + events.get(i).getName());
        }

        System.out.print("Select an event (1-" + events.size() + "): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (choice < 1 || choice > events.size()) {
            System.out.println("Invalid selection! Try again.");
            return;
        }

        Event selectedEvent = events.get(choice - 1);
        displayGeneralEventDetails(selectedEvent);

        if (confirmBooking()) {
            processPayment(selectedEvent.getAdultPrice(), selectedEvent.getChildPrice());
        }
    }

    private void bookSportsEvent() {
        List<SportsEvent> sportsEvents = sportsEventProvider.getSportsEvents();

        if (sportsEvents.isEmpty()) {
            System.out.println("No sports events available.");
            return;
        }

        System.out.println("\nAvailable Sports Events:");
        for (int i = 0; i < sportsEvents.size(); i++) {
            System.out.println((i + 1) + ". " + sportsEvents.get(i).getName());
        }

        System.out.print("Select a sports event (1-" + sportsEvents.size() + "): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (choice < 1 || choice > sportsEvents.size()) {
            System.out.println("Invalid selection! Try again.");
            return;
        }

        SportsEvent selectedEvent = sportsEvents.get(choice - 1);
        displaySportsEventDetails(selectedEvent);

        if (!selectedEvent.isTicketAvailable()) {
            System.out.println("Tickets for this event are sold out.");
            return;
        }

        if (confirmBooking()) {
            processPayment(selectedEvent.getTicketPrice(), 0);
        }
    }

    private void displayGeneralEventDetails(Event event) {
        System.out.println("\nEvent Details:");
        System.out.println("Name: " + event.getName());
        System.out.println("Description: " + event.getDescription());
        System.out.println("Date: " + event.getDate());
        System.out.println("Time: " + event.getTime());
        System.out.println("Location: " + event.getLocation());
        System.out.println("Adult Ticket Price: LKR " + event.getAdultPrice());
        System.out.println("Child Ticket Price: LKR " + event.getChildPrice());
    }

    private void displaySportsEventDetails(SportsEvent event) {
        System.out.println("\nSports Event Details:");
        System.out.println("Name: " + event.getName());
        System.out.println("Sport Type: " + event.getSportType());
        System.out.println("Date: " + event.getDate());
        System.out.println("Time: " + event.getTime());
        System.out.println("Location: " + event.getLocation());
        System.out.println("Teams: " + event.getTeams());
        System.out.println("Ticket Price: LKR " + event.getTicketPrice());
        System.out.println("Tickets Available: " + (event.isTicketAvailable() ? "Yes" : "No"));
    }

    private boolean confirmBooking() {
        System.out.print("\nDo you want to book tickets? (yes/no): ");
        String response = scanner.nextLine();
        return response.equalsIgnoreCase("yes");
    }

    private void processPayment(double adultPrice, double childPrice) {
        System.out.print("Enter number of adult tickets: ");
        int adultTickets = scanner.nextInt();
        System.out.print("Enter number of child tickets: ");
        int childTickets = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        double totalPrice = (adultTickets * adultPrice) + (childTickets * childPrice);

        System.out.println("\nReceipt:");
        System.out.println("Adult Tickets: " + adultTickets);
        System.out.println("Child Tickets: " + childTickets);
        System.out.println("Total Price: LKR " + totalPrice);
    }
}
