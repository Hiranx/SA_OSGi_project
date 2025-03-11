package sports.producer;

public class SportsEvent {
    private String name, sportType, date, time, location, teams;
    private double ticketPrice;
    private boolean ticketAvailable;

    public SportsEvent(String name, String sportType, String date, String time, String location, String teams, double ticketPrice, boolean ticketAvailable) {
        this.name = name;
        this.sportType = sportType;
        this.date = date;
        this.time = time;
        this.location = location;
        this.teams = teams;
        this.ticketPrice = ticketPrice;
        this.ticketAvailable = ticketAvailable;
    }

    public String getName() { return name; }
    public String getSportType() { return sportType; }
    public String getDate() { return date; }
    public String getTime() { return time; }
    public String getLocation() { return location; }
    public String getTeams() { return teams; }
    public double getTicketPrice() { return ticketPrice; }
    public boolean isTicketAvailable() { return ticketAvailable; }
}

