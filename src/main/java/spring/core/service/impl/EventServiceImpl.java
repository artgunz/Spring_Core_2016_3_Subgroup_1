package spring.core.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.core.aop.annotation.Countable;
import spring.core.aop.annotation.EventName;
import spring.core.aop.annotation.Loggable;
import spring.core.aop.handler.impl.DefaultCountableMethodHandler;
import spring.core.dao.EventDAO;
import spring.core.data.Auditorium;
import spring.core.data.Event;
import spring.core.data.EventCreationInformation;
import spring.core.data.ShowEvent;
import spring.core.exception.EventAlreadyExistsException;
import spring.core.service.EventService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    private static final Logger LOGGER = LogManager.getLogger(EventServiceImpl.class);

    @Autowired
    EventDAO eventDAO;

    @Override
    public Event create(final EventCreationInformation creationInformation) throws Exception {
        Event eventSearch = eventDAO.searchEventByName(creationInformation.getName());
        if (eventSearch != null) {
            LOGGER.warn("Event with name {} exists!", creationInformation.getName());
            return eventSearch;
        }

        return eventDAO.createEvent(creationInformation);
    }

    @Override
    public void remove(final Event event) {
        eventDAO.deleteEvent(event.getPk());
    }

    @Loggable
    @Countable(handler = DefaultCountableMethodHandler.class)
    @Override
    public Event getByName(@EventName final String eventName) throws Exception {
        return eventDAO.searchEventByName(eventName);
    }

    @Override
    public List<Event> getAll() throws Exception {
        return eventDAO.getAllEvents();
    }

    @Override
    public List<Event> getForDateRange(final Date fromDate, final Date tillDate) throws Exception {
        List<Event> listEvents = new ArrayList<>();

        for (ShowEvent event : eventDAO.getAllShows()) {
            Date showTime = event.getShowTime();
            if (showTime.after(fromDate) && showTime.before(tillDate)) {
                listEvents.add(event.getEvent());
            }
        }

        return listEvents;
    }

    @Override
    public List<ShowEvent> getNextEvents(final Date tillDate) throws Exception {
        Date current = new Date();
        List<ShowEvent> listEvents = new ArrayList<>();

        for (ShowEvent event : eventDAO.getAllShows()) {
            Date showTime = event.getShowTime();
            if (showTime.after(current) && showTime.before(tillDate)) {
                listEvents.add(event);
            }
        }

        return listEvents;
    }

    @Loggable
    @Countable(handler = DefaultCountableMethodHandler.class)
    @Override
    public List<ShowEvent> getNextEventsByName(final Date tillDate, @EventName final String eventName) throws
            Exception {
        Date current = new Date();
        List<ShowEvent> listEvents = new ArrayList<>();

        for (ShowEvent event : eventDAO.getAllShows()) {
            Date showTime = event.getShowTime();
            if (showTime.after(current) && event.getEvent().getName().equals(eventName)) {
                listEvents.add(event);
            }
        }

        return listEvents;
    }

    @Override
    public ShowEvent assignAuditoriumAndDate(final Event event, final Auditorium auditorium, final Date date) throws
            Exception {
        ShowEvent foundedEvent = null;

        for (final ShowEvent searchEvent : eventDAO.getAllShows()) {
            if (searchEvent.getEvent().equals(event) && searchEvent.getAuditorium().equals(auditorium) && searchEvent
                    .getShowTime().equals(date)) {
                foundedEvent = searchEvent;
                break;
            }
        }

        if (foundedEvent != null) {
            throw new EventAlreadyExistsException("Event Already in base");
        }

        final ShowEvent showEvent = new ShowEvent(event, auditorium, date);

        eventDAO.addShowEvent(showEvent);

        return showEvent;
    }
}
