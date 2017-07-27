package lodz.uni.portal.dao;

import java.util.List;

import lodz.uni.portal.model.Event;

public interface EventDao {
	public void save(Event event);
	
	public void updateEvent(Event event);
	
	public List<Event> findAllEvents();

	public Event findEventById(Integer id);
	
	public List<Event> findActualEvents();

	public List<Event> findActualEventsByParam(String townName, String sportName);

}
