package event.producer;

import java.util.Arrays;
import java.util.List;
import org.osgi.service.component.annotations.Component;

@Component(service = EventProviderInterface.class)
public class EventProvider implements EventProviderInterface {

    private List<Event> events = Arrays.asList(
        new Event("Concert Night", "Live music show", "2025-04-10", "19:00", "Colombo Stadium", 5000, 2500),
        new Event("Tech Conference", "Latest in technology", "2025-05-20", "10:00", "SL Convention Center", 3000, 1500),
        new Event("Food Festival", "Taste the best foods", "2025-06-15", "12:00", "Galle Face Green", 2000, 1000)
    );

    @Override
    public List<Event> getEvents() {
        return events;
    }
}
