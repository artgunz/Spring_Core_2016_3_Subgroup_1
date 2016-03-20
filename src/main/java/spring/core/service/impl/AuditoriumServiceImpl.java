package spring.core.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.core.dao.AuditoriumDAO;
import spring.core.data.Auditorium;
import spring.core.data.Seat;
import spring.core.exception.ElementNotFoundException;
import spring.core.service.AuditoriumService;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuditoriumServiceImpl implements AuditoriumService {

    private static final Logger LOGGER = LogManager.getLogger(AuditoriumServiceImpl.class);

    @Autowired
    AuditoriumDAO auditoriumDAO;

    @Override
    public List<Auditorium> getAuditoriums() {
        return auditoriumDAO.getAuditoriums();
    }

    @Override
    public Integer getSeatsNumber(final String auditoriumName) throws ElementNotFoundException {
        for (Auditorium auditorium : getAuditoriums()
                ) {
            if (auditorium.getName().equals(auditoriumName)) {
                Integer count = auditorium.getSeatsCount();

                LOGGER.debug("Auditorium {} has {} seats", auditoriumName, count);

                return count;
            }
        }

        throw new ElementNotFoundException(String.format("Auditorium '%s' not found", auditoriumName));
    }

    @Override
    public List<Integer> getVipSeats(final String auditoriumName) throws ElementNotFoundException {
        for (Auditorium auditorium : getAuditoriums()) {
            if (auditorium.getName().equals(auditoriumName)) {
                List<Seat> vipSeats = auditorium.getVipSeats();

                List<Integer> vipSeatsAsInt = new ArrayList<>();
                for(Seat seat:vipSeats){
                    vipSeatsAsInt.add(seat.getId());
                }

                LOGGER.debug("Auditorium {} has {} vip seats", auditoriumName, vipSeatsAsInt);

                return vipSeatsAsInt;
            }
        }

        throw new ElementNotFoundException(String.format("Auditorium '%s' not found", auditoriumName));
    }

    @Override
    public Auditorium searchAuditoriumByName(final String name) {
        return auditoriumDAO.searchAuditoriumByName(name);
    }

    @Override
    public Auditorium addAuditorium(final Auditorium auditorium) {
        Auditorium auditoriumResult = auditoriumDAO.searchAuditoriumByName(auditorium.getName());
        if (auditoriumResult != null) {
            LOGGER.warn("Auditorium with name {} exists!", auditorium.getName());
            return auditorium;
        }

        return auditoriumDAO.addAuditorium(auditorium);
    }
}
