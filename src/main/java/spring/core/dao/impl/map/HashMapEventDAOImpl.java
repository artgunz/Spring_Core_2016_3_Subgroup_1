package spring.core.dao.impl.map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import spring.core.dao.EventDAO;
import spring.core.data.Event;
import spring.core.data.EventCreationInformation;
import spring.core.data.ShowEvent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
@Profile("test")
public class HashMapEventDAOImpl implements EventDAO {
    private final static Set<Event> eventStorage = new HashSet<>();
    private final static Set<ShowEvent> showEventStorage = new HashSet<ShowEvent>();

    public Event createEvent(final EventCreationInformation eventCreationInformation) {
        Event event = new Event(eventCreationInformation.getName(), eventCreationInformation.getBasePrice(),
                eventCreationInformation.getRating());
        eventStorage.add(event);
        return event;
    }

    public void deleteEvent(final Long eventPk) {
        if (eventPk != null) {
            eventStorage.remove(eventPk);
        }

    }

    @Override
    public Event searchEventByName(final String eventName) {
        for (final Event event : eventStorage) {
            if (event.getName().equals(eventName)) {
                return event;
            }
        }

        return null;
    }

    public List<Event> getAllEvents() {
        final List<Event> mainList = new ArrayList<Event>();
        mainList.addAll(eventStorage);
        return mainList;
    }

    public List<ShowEvent> getAllShows() {
        final List<ShowEvent> mainList = new ArrayList<>();
        mainList.addAll(showEventStorage);
        return mainList;
    }

    @Override
    public void addShowEvent(ShowEvent showEvent) {
        showEventStorage.add(showEvent);
    }

}
