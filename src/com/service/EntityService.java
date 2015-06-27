package com.service;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.entities.Direction;
import com.entities.Journey;
import com.entities.Passenger;
import com.entities.Route;
import com.entities.Seats;
import com.entities.Shedule;
import com.entities.Station;
import com.entities.Ticket;
import com.entities.Train;
import com.entities.User;
import com.entities.User_Ticket;

public class EntityService {
private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("Trains_JPA");
private static EntityManager em = factory.createEntityManager();
private static Random random = new Random(47);
public static void createTrain(int seats){
	
	em.getTransaction().begin();
	Train train = new Train();
	train.setTrain_seats(seats);
	em.persist(train);
	em.getTransaction().commit();
}

public static void initTrains(){
	clearTrains();
	for (int i=0; i<10; i++){
		createTrain(100);
	}
}
public static Train deleteTrain(int train_id){
	Train t = (Train)em.createQuery("select t from Train t where t.train_id=" + train_id).getSingleResult();
	em.getTransaction().begin();
	em.remove(t);
	em.getTransaction().commit();
	return t;
}
public static void clearTrains(){
	List<Train> trains = em.createQuery("select t from Train t").getResultList();
	for (Train t: trains){
		deleteTrain(t.getTrain_id());
	}
}

public static void createStation(String name){

	em.getTransaction().begin();
	Station station = new Station();
	station.setStation_name(name);
	em.persist(station);
	em.getTransaction().commit();
}

public static void initStations(){
	clearStations();
	String[] cities = {"Moscow","Saint-Petersburg","Ryazan","Vladivistok","Petrozavodsk","Volgograd","Novgorod","Samara"};
	for (String city:cities){
		createStation(city);
	}
}

public static Station deleteStation(int station_id){
	Station s = (Station) em.createQuery("select s from Station s where s.station_id=" + station_id).getSingleResult();
	em.getTransaction().begin();
	em.remove(s);
	em.getTransaction().commit();
	return s;
}

public static void clearStations(){
	List<Station> stations = em.createQuery("select t from Station t").getResultList();
	for (Station s: stations){
		deleteStation(s.getStation_id());
	}
}

public static void createDirection(int st_dep, int st_arr, long time, double cost){
	em.getTransaction().begin();
	Direction direction = new Direction();
	direction.setSt_dep(st_dep);
	direction.setSt_arr(st_arr);
	direction.setTime(time);
	direction.setCost(cost);
	
	Direction direction_reverse = new Direction();
	direction_reverse.setSt_dep(st_arr);
	direction_reverse.setSt_arr(st_dep);
	direction_reverse.setTime(time);
	direction_reverse.setCost(cost);
	
	em.persist(direction);
	em.persist(direction_reverse);
	em.getTransaction().commit();
	
}
public static void initDirections(){
	clearDirections();
	
	Query query = em.createQuery("select s from Station s");
	List<Station> stations = query.getResultList();
	if (stations.size()>1) {
		for (int i = 0; i < stations.size(); i++) {
			int st_dep = stations.get(i).getStation_id();
			for (int j = i + 1; j < stations.size(); j++) {
				int st_arr = stations.get(j).getStation_id();
				int time = 1000 * 60 * 30 + random.nextInt(1000 * 60 * 60 * 2);
				double cost = 400+random.nextInt(200);
				createDirection(st_dep,st_arr, time, cost);
			}
		}
	}
}
public static Direction deleteDirection(int st_dep, int st_arr){
	Direction d = (Direction) em.createQuery
			("select d from Direction d where d.st_sep=" + st_dep + " and st_arr=" + st_arr).getSingleResult();
	em.getTransaction().begin();
	em.remove(d);
	em.getTransaction().commit();
	return d;
}
public static void deleteDirection(int direction_id){
	
		Direction d = (Direction) em.createQuery
				("select d from Direction d where d.direction_id=" + direction_id).getSingleResult();
		int st_dep = d.getSt_dep();
		int st_arr = d.getSt_arr();
		deleteDirection(st_dep, st_arr);
		deleteDirection(st_arr, st_dep);
	
}
public static void clearDirections(){
	Query query = em.createQuery("select t from Direction t");
	List<Direction> directions = query.getResultList();
	em.getTransaction().begin();
	for (Direction d: directions){
		em.remove(d);
	}
	em.getTransaction().commit();
}

public static Route createRoute(String name){
	em.getTransaction().begin();
	Route route = new Route();
	route.setRoute_name(name);
	em.persist(route);
	em.getTransaction().commit();
	return route;
}
public static void initRoutes(){
	clearRoutes();
	String alphabet = "abcdefghijklmnopqrstuvwxyz";
	for (int i=0; i<10; i++){
		createRoute(String.valueOf(100+i) + alphabet.charAt(random.nextInt(alphabet.length())));
	}
}
public static Route deleteRoute(int route_id){
	Route r = (Route) em.createQuery("select r from Route r where r.route_id=" + route_id).getSingleResult();
	em.getTransaction().begin();
	em.remove(r);
	em.getTransaction().commit();
	return r;
}
public static void clearRoutes(){
	List<Route> routes = em.createQuery("select t from Route t").getResultList();
	
	for (Route r: routes){
		deleteRoute(r.getRoute_id());
	}
	
}
//public static LinkedList<Station> getStationsOnRoute(int route_id){
//	List<Shedule> shedules = em.createQuery("select s from Shedule s where s.route_id=" + route_id).getResultList();
//	for
//}

public static void initShedules(){
	clearShedules();
	List<Route> routes = em.createQuery("select r from Route r").getResultList();
	List<Station> stations = em.createQuery("select s from Station s").getResultList();
	
	for (Route r: routes){
		Set<Integer> route_stations = new HashSet<Integer>();
		int steps = 4+random.nextInt(3);
		int currentStep = 0;
		int st_dep_id = stations.get(random.nextInt(stations.size())).getStation_id();
		route_stations.add(st_dep_id);
		
		
		while (currentStep<steps) {
			List<Direction> possibleDirections = em.createQuery(
					"select d from Direction d where d.st_dep=" + st_dep_id)
					.getResultList();
			Direction currentDirection = possibleDirections.get(random
					.nextInt(possibleDirections.size()));
			while (route_stations.contains(currentDirection.getSt_arr())){
				currentDirection = possibleDirections.get(random.nextInt(possibleDirections.size()));
			}
			em.getTransaction().begin();
			Shedule shedule = new Shedule();
			shedule.setDirection_id(currentDirection.getDirection_id());
			shedule.setRoute_id(r.getRoute_id());
			shedule.setStep(currentStep);
			em.persist(shedule);
			em.getTransaction().commit();
			currentStep++;
			st_dep_id = currentDirection.getSt_arr();
			route_stations.add(st_dep_id);
		}
		
	}
}
public static Shedule deleteShedule(int shedule_id){
	Shedule s = (Shedule) em.createQuery("select s from Shedule s where s.shedule_id=" + shedule_id);
	em.getTransaction().begin();
	em.remove(s);
	em.getTransaction().commit();
	return s;
}
public static void clearShedules(){
	List<Shedule> shedules = em.createQuery("select t from Shedule t").getResultList();
	for (Shedule s: shedules){
		deleteShedule(s.getShedule_id());
	}
}

public static Journey createJourney(int train_id, int route_id, Date time_dep){
	em.getTransaction().begin();
	Journey journey = new Journey();
	journey.setTrain_id(train_id);
	journey.setRoute_id(route_id);
	journey.setTime_dep(time_dep);
	em.persist(journey);
	em.getTransaction().commit();
	return journey;
}
public static void initJourneys(){
	clearJourneys();
	Date currentTime = new Date();
	long currentMillis = currentTime.getTime();
	long m = 1000*60;
	long h = m*60;
	long d = h*24;
	
	List<Route> routes = em.createQuery("select r from Route r").getResultList();
	List<Train> trains = em.createQuery("select t from Train t").getResultList();
	
	for (int i=0; i<routes.size(); i++){
		Route r = routes.get(i);
		Train t = trains.get(i%trains.size());
		createJourney(t.getTrain_id(),r.getRoute_id(),new Date(currentMillis+(i+1)*h));
	}
}

public static Journey deleteJourney(int journey_id){
	Journey j = (Journey) em.createQuery("select j from Journey j where j.journey_id=" + journey_id);
	em.getTransaction().begin();
	em.remove(j);
	em.getTransaction().commit();
	return j;
}
public static void clearJourneys(){
	List<Journey> journeys = em.createQuery("select j from Journey j").getResultList();
	for (Journey j: journeys){
		deleteJourney(j.getJourney_id());
	}
}

public static Passenger createPassenger(String name){
	em.getTransaction().begin();
	Passenger p = new Passenger();
	p.setPassenger_name(name);
	em.persist(p);
	em.getTransaction().commit();
	return p;
}

public static Passenger deletePassenger(int passenger_id){
	Query query = em.createQuery("select p from Passenger p where p.passenger_id=" + passenger_id);
	Passenger p = (Passenger)query.getSingleResult();
	em.getTransaction().begin();
	em.remove(p);
	em.getTransaction().commit();
	return p;
}

public static void clearPassengers(){
	List<Passenger> passengers = em.createQuery("select p from Passenger p").getResultList();
	for (Passenger p : passengers){
		deletePassenger(p.getPassenger_id());
	}
}

public static User createUser(String login, String password, boolean account_type){
	em.getTransaction().begin();
	User u = new User();
	u.setUser_login(login);
	u.setUser_password(password);
	u.setAccount_type(account_type);
	em.persist(u);
	em.getTransaction().commit();
	return u;
}
public static void initUsers(){
	clearUsers();
	em.getTransaction().begin();
	User u = new User();
	u.setUser_login("root");
	u.setUser_password("root");
	u.setAccount_type(true);
	em.persist(u);
	em.getTransaction().commit();
}

public static User deleteUser(int user_id){
	Query query = em.createQuery("select u from User u where u.user_id=" + user_id);
	User u = (User)query.getSingleResult();
	em.getTransaction().begin();
	em.remove(u);
	em.getTransaction().commit();
	return u;
}

public static void clearUsers(){
	List<User> users = em.createQuery("select u from User u").getResultList();
	for (User u : users){
		deleteUser(u.getUser_id());
	}
}

public static Ticket createTicket(int passenger_id, int journey_id, int st_dep, int st_arr){
	em.getTransaction().begin();
	Ticket t = new Ticket();
	t.setPassenger_id(passenger_id);
	t.setJourney_id(journey_id);
	t.setSt_dep(st_dep);
	t.setSt_arr(st_arr);
	em.persist(t);
	em.getTransaction().commit();
	return t;
}

public static void initTickets(){
	clearPassengers();
	
	String[] names = {"Ivan", "Alexey", "Aleksandr", "Petr", "Maria", "Daria", "Katya", "Vyacheslav"};
	List<Journey> journeys = em.createQuery("select j from Journey j").getResultList();
	User u = (User) em.createQuery("select u from User u where u.account_type=" + true).getSingleResult();

	for (int i=0; i<names.length; i++){
		Passenger p = createPassenger(names[i]);
		Journey j = journeys.get(i%journeys.size());
		int route_id = j.getRoute_id();
		Shedule route_beginning = (Shedule) em.createQuery
				("select s from Shedule s where s.route_id=" + route_id + " and s.step=" + 0).getSingleResult();
		List<Shedule> steps  = em.createQuery("select s from Shedule s where s.route_id=" + route_id).getResultList();
		int lastStep = 0;
		for (Shedule s : steps){
			lastStep = Math.max(lastStep, s.getStep());
		}
		
		Shedule route_ending = (Shedule) em.createQuery
				("select s from Shedule s where s.route_id=" + route_id + " and s.step=" + lastStep).getSingleResult();
		
		Direction route_beginning_d = (Direction) em.createQuery("select d from Direction d where d.direction_id=" + 
		route_beginning.getDirection_id()).getSingleResult();
		Direction route_ending_d = (Direction) em.createQuery("select d from Direction d where d.direction_id=" + 
				route_ending.getDirection_id()).getSingleResult();
		
		boolean trainHasEmptySeats = decrementEmptySeats
				(j.getJourney_id(), route_beginning_d.getSt_dep(), route_ending_d.getSt_arr());
		if (trainHasEmptySeats){
		Ticket t = createTicket(p.getPassenger_id(),j.getJourney_id(),route_beginning_d.getSt_dep(), route_ending_d.getSt_arr());
		createUser_Ticket(t.getTicket_id(), u.getUser_id());
		}
		
		
	}
}
public static Ticket deleteTicket(int ticket_id){
	Query query = em.createQuery("select t from Ticket t where t.ticket_id=" + ticket_id);
	Ticket t = (Ticket)query.getSingleResult();
	em.getTransaction().begin();
	em.remove(t);
	em.getTransaction().commit();
	return t;
}

public static void clearTickets(){
	List<Ticket> tickets = em.createQuery("select t from Ticket t").getResultList();
	em.getTransaction().begin();
	for (Ticket t:tickets){
		em.remove(t);
	}
	em.getTransaction().commit();
}

public static User_Ticket createUser_Ticket(int ticket_id, int user_id){
	em.getTransaction().begin();
	User_Ticket ut = new User_Ticket();
	ut.setTicket_id(ticket_id);
	ut.setUser_id(user_id);
	em.persist(ut);
	em.getTransaction().commit();
	return ut;
}

public static User_Ticket deleteUser_Ticket(int ticket_id){
	User_Ticket ut = (User_Ticket) em.createQuery("select ut from User_Ticket ut where ut.ticket_id=" + ticket_id);
	em.getTransaction().begin();
	em.remove(ut);
	em.getTransaction().commit();
	return ut;
}

public static Seats createSeats(int journey_id, int step, int empty_seats){
	em.getTransaction().begin();
	Seats s = new Seats();
	s.setJourney_id(journey_id);
	s.setRoute_step(step);
	s.setEmpty_seats(empty_seats);
	em.persist(s);
	em.getTransaction().commit();
	return s;
}

public static Seats deleteSeats(int seats_id){
	Seats s = (Seats) em.createQuery
			("select s from Seats s where s.seats_id=" + seats_id).getSingleResult();
	em.getTransaction().begin();
	em.remove(s);
	em.getTransaction().commit();
	return s;
}

public static void initSeats(){
	List<Journey> journeys = em.createQuery("select j from Journey j").getResultList();
	for (Journey j : journeys){
		int train_id = j.getTrain_id();
		int route_id = j.getRoute_id();
		Train train = (Train) em.createQuery
				("select t from Train t where t.train_id=" + train_id).getSingleResult();
		List<Shedule> steps = em.createQuery
				("select s from Shedule s where s.route_id=" + route_id).getResultList();
		for (Shedule s : steps){
			createSeats(j.getJourney_id(), s.getStep(), train.getTrain_seats());
		}
		
	}
}

public static boolean decrementEmptySeats(int journey_id, int st_dep, int st_arr){
	Journey j = (Journey) em.createQuery
			("select j from Journey j where j.journey_id=" + journey_id).getSingleResult();
	List<Seats> seats = em.createQuery
			("select s from Seats s where s.journey_id=" + journey_id).getResultList();
	
	List<Shedule> steps = em.createQuery
			("select s from Shedule s where s.route_id=" + j.getRoute_id()).getResultList();
	int start_step=0;
	int stop_step=0;
	for (Shedule s : steps){
		Direction d = (Direction) em.createQuery
				("select d from Direction d where d.direction_id=" + s.getDirection_id()).getSingleResult();
		if (d.getSt_dep()==st_dep){
			start_step=s.getStep();
		}
		if (d.getSt_arr()==st_arr){
			stop_step=s.getStep();
		}
	}
	for (Seats s : seats){
		if (s.getRoute_step()>=start_step&&s.getRoute_step()<=stop_step&&s.getEmpty_seats()==0){
			return false;
		}
	}
	
	em.getTransaction().begin();
	for (Seats s : seats){
		int empty_seats = s.getEmpty_seats();
		if (s.getRoute_step()>=start_step&&s.getRoute_step()<=stop_step){
		s.setEmpty_seats(empty_seats-1);
		em.merge(s);
		}
	}
	em.getTransaction().commit();
	return true;
	
}
public static void clearSeats(){
	List<Seats> seats = em.createQuery("select s from Seats s").getResultList();
	for (Seats s : seats){
		deleteSeats(s.getSeats_id());
	}
	
}

public static void initDatabase(){
	initStations();
	initTrains();
	initRoutes();
	initDirections();
	initShedules();
	initJourneys();
	initSeats();
	initUsers();
	initTickets();
}
public static void main(String...args){
	try {
initDatabase();
	} finally{
		em.close();
		factory.close();
	}
}
}
