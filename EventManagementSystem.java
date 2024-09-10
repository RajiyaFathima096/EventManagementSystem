import java.util.*;

public class EventManagementSystem {

    
    static class User {
        protected Long userId;
        protected String name;
        protected String email;
        protected String password;

        public User(Long userId, String name, String email, String password) {
            this.userId = userId;
            this.name = name;
            this.email = email;
            this.password = password;
        }

    
        public String getName() {
            return name;
        }

        public void registerForEvent(Event event) {
            System.out.println(this.name + " registered for " + event.getTitle());
        }
    }

  
    static class Admin extends User {
        public Admin(Long userId, String name, String email, String password) {
            super(userId, name, email, password);
        }

        public void createEvent(Event event) {
            System.out.println(this.name + " created the event: " + event.getTitle());
        }

        public void deleteEvent(Event event) {
            System.out.println(this.name + " deleted the event: " + event.getTitle());
        }

        public void manageUser(User user) {
            System.out.println(this.name + " managed the user: " + user.getName());
        }
    }

    
    static class Event {
        private Long eventId;
        private String title;
        private String description;
        private Date date;
        private String location;
        private int capacity;
        private int registeredCount = 0;

        public Event(Long eventId, String title, String description, Date date, String location, int capacity) {
            this.eventId = eventId;
            this.title = title;
            this.description = description;
            this.date = date;
            this.location = location;
            this.capacity = capacity;
        }

        public String getTitle() {
            return title;
        }

        public void registerAttendee(User user) {
            if (registeredCount < capacity) {
                registeredCount++;
                System.out.println(user.name + " has successfully registered for " + title);
            } else {
                System.out.println("Sorry, " + title + " is full.");
            }
        }
    }

    static class Registration {
        private Long regId;
        private User user;
        private Event event;
        private String paymentStatus;

        public Registration(Long regId, User user, Event event, String paymentStatus) {
            this.regId = regId;
            this.user = user;
            this.event = event;
            this.paymentStatus = paymentStatus;
        }
    }


    static class Notification {
        private Long notifId;
        private String type;
        private String message;
        private User recipient;

        public Notification(Long notifId, String type, String message, User recipient) {
            this.notifId = notifId;
            this.type = type;
            this.message = message;
            this.recipient = recipient;
        }

        public void sendNotification() {
            System.out.println("Sending " + type + " notification to " + recipient.name + ": " + message);
        }
    }

    
    static class Payment {
        private Long paymentId;
        private double amount;
        private String paymentMethod;
        private String status;
        private User user;
        private Event event;

        public Payment(Long paymentId, double amount, String paymentMethod, String status, User user, Event event) {
            this.paymentId = paymentId;
            this.amount = amount;
            this.paymentMethod = paymentMethod;
            this.status = status;
            this.user = user;
            this.event = event;
        }

        public void processPayment() {
            System.out.println("Processing " + paymentMethod + " payment of " + amount + " for " + user.name + " to attend " + event.getTitle());
            this.status = "Completed";




        }
    }

    
    static class SearchService {
        private List<Event> events = new ArrayList<>();

        public void addEvent(Event event) {
            events.add(event);
        }

        public List<Event> findEventsByKeyword(String keyword) {
            List<Event> result = new ArrayList<>();
            for (Event event : events) {
               // System.out.println(event);
                if (event.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                    result.add(event);
                }
            }
            return result;
        }

        public List<Event> findEventsByDate(Date date) {
            List<Event> result = new ArrayList<>();
            for (Event event : events) {
                if (event.date.equals(date)) {
                    result.add(event);
                }
            }
            return result;
        }

        public List<Event> findEventsByLocation(String location) {
            List<Event> result = new ArrayList<>();
            for (Event event : events) {
                if (event.location.toLowerCase().contains(location.toLowerCase())) {
                    result.add(event);
                }
            }
            return result;
        }
    }

    
    public static void main(String[] args) {
        
        User user1 = new User(1L, "Rajiya fathima", "rajiya@example.com", "password123");
        Admin admin1 = new Admin(2L, "Admin ", "admin@example.com", "adminpass");

        
        Event event1 = new Event(1L, "Tech Conference", "A conference about technology", new Date(), "madurai", 100);
        Event event2 = new Event(2L, "Oops olympiad", "object oriented programming language", new Date(), "chennai", 200);

        
        SearchService searchService = new SearchService();
        searchService.addEvent(event1);
        searchService.addEvent(event2);

        
        admin1.createEvent(event1);


        user1.registerForEvent(event1);
        event1.registerAttendee(user1);

        Payment payment1 = new Payment(1L, 50.0, "Credit Card", "Pending", user1, event1);
        payment1.processPayment();

        
        Notification notification1 = new Notification(1L, "Email", "Your registration is confirmed!", user1);
        notification1.sendNotification();

        
        admin1.deleteEvent(event1);

        
        List<Event> searchResults = searchService.findEventsByKeyword("Con");
        System.out.println("Search Results: ");
        for (Event event : searchResults) {
            System.out.println(event.getTitle());
        }
    }
}
