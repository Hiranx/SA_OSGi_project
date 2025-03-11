package sports.producer;

import java.util.Arrays;
import java.util.List;
import org.osgi.service.component.annotations.Component;

@Component(service = SportsEventProviderInterface.class)
public class SportsEventProvider implements SportsEventProviderInterface {

    private List<SportsEvent> sportsEvents = Arrays.asList(
        new SportsEvent("Football Championship", "Football", "2025-06-01", "18:00", "National Stadium", "Team A vs Team B", 4500, true),
        new SportsEvent("Cricket World Cup Final", "Cricket", "2025-07-15", "15:00", "Colombo Cricket Ground", "Team X vs Team Y", 6000, true),
        new SportsEvent("Marathon 2025", "Running", "2025-08-20", "07:00", "City Park", "Open for all", 2000, false)
    );

    @Override
    public List<SportsEvent> getSportsEvents() {
        return sportsEvents;
    }
}

