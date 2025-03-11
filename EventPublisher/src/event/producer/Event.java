package event.producer;

public class Event {
    private String name, description, date, time, location;
    private double adultPrice, childPrice;

    public Event(String name, String description, String date, String time, String location, double adultPrice, double childPrice) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.time = time;
        this.location = location;
        this.adultPrice = adultPrice;
        this.childPrice = childPrice;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getDate() { return date; }
    public String getTime() { return time; }
    public String getLocation() { return location; }
    public double getAdultPrice() { return adultPrice; }
    public double getChildPrice() { return childPrice; }
}
