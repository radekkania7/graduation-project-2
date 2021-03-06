package lodz.uni.portal.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="EVENTS")
public class Event implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="EVENT_ID")
	private Integer id; 
	
	@Column(name="DESCRIPTION")
	@NotNull
	private String description;
	
	@Column(name="EVENT_DATE")
	private Date eventDate;
	
	@Column(name="START_TIME")
	private Time startTime;
	
	@Column(name="STOP_TIME")
	private Time stopTime;

	@Column(name="ADDRESS")
	private String address;

	@NotNull
	@Column(name="PLAYERS_LIMIT")
	private Integer playersLimit;

	@NotNull
	@Column(name="TOWN")
	private String town;

	@NotNull
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="SPORT_ID")
	private Sport eventSport;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="EVENT_STATUS_FK")
	private EventStatus status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="USER_CREATOR_FK")
	private PortalUser userCreator;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="event", cascade=CascadeType.ALL)
	private List<Game> eventGames = new LinkedList<>();
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="event", cascade=CascadeType.ALL)
	private List<Mark> eventMarks = new LinkedList<>();
	
	@ManyToMany
	@JoinTable(name="EVENT_USERS",
			joinColumns=@JoinColumn(name="EVENT_ID", referencedColumnName="EVENT_ID"),
			inverseJoinColumns=@JoinColumn(name="USER_ID", referencedColumnName="USER_ID"))
	private List<PortalUser> eventUsers;

	public PortalUser getUserCreator() {
		return userCreator;
	}

	public void setUserCreator(PortalUser userCreator) {
		this.userCreator = userCreator;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public EventStatus getStatus() {
		return status;
	}

	public void setStatus(EventStatus status) {
		this.status = status;
	}

	public List<Mark> getEventMarks() {
		return eventMarks;
	}

	public void setEventMarks(List<Mark> eventMarks) {
		this.eventMarks = eventMarks;
	}

	public Sport getEventSport() {
		return eventSport;
	}

	public void setEventSport(Sport eventSport) {
		this.eventSport = eventSport;
	}
	
	public Integer getPlayersLimit() {
		return playersLimit;
	}

	public void setPlayersLimit(Integer playersLimit) {
		this.playersLimit = playersLimit;
	}

	public List<PortalUser> getEventUsers() {
		return eventUsers;
	}

	public void setEventUsers(List<PortalUser> eventUsers) {
		this.eventUsers = eventUsers;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getEventDate() {
		return eventDate;
	}
	
	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getStopTime() {
		return stopTime;
	}

	public void setStopTime(Time stopTime) {
		this.stopTime = stopTime;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public List<Game> getEventGames() {
		return eventGames;
	}

	public void setEventGames(List<Game> eventGames) {
		this.eventGames = eventGames;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
