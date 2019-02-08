package model;


import model.function.*;


import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HotelSystem {
    private RulesFactory rulesFactory = new RulesFactory();
    private IGuestStrategy iGuestStrategy;
    private IRoomStrategy iRoomStrategy;
    private ISearchStrategy iSearchStrategy;
    private IMutipleSearchStrategy iMutipleSearchStrategy;
    private ILogInStrategy iLogInStrategy;
    private IGetLimitedTimeRoomStrategy iGetLimitedTimeRoomStrategy;
    private DB db = new DB();

    public void addRoom(Room room)
    {
        try{
            iRoomStrategy = rulesFactory.getRoom(DoTypes.ADD.toString());
            iRoomStrategy.setRoom(room,db);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteRoom(Room room)
    {
        try{
            iRoomStrategy = rulesFactory.getRoom(DoTypes.DELETE.toString());
            iRoomStrategy.setRoom(room,db);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editRoom(Room room)
    {
        try{
            iRoomStrategy = rulesFactory.getRoom(DoTypes.EDIT.toString());
            iRoomStrategy.setRoom(room,db);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addGuest(Guest guest)
    {
        iGuestStrategy = rulesFactory.getGuest(DoTypes.ADD.toString());
        iGuestStrategy.setGuest(guest,db);
    }

    public void editGuest(Guest guest)
    {
        try{
            iGuestStrategy = rulesFactory.getGuest(DoTypes.EDIT.toString());
            iGuestStrategy.setGuest(guest,db);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Room> getMutipleSearch(Room room, Guest guest)
    {
        iMutipleSearchStrategy = rulesFactory.getMutipleSearch(SearchType.MULTIPLE.toString());
        iGetLimitedTimeRoomStrategy = rulesFactory.getLimitedTimeRoom(SearchType.USE_ROOM.toString());

        List<Guest> collect = Stream.of(iGetLimitedTimeRoomStrategy.getLimitedTimeRoom(guest.getCheck_Out(),getALLGuest()),iGetLimitedTimeRoomStrategy.getLimitedTimeRoom(guest.getCheck_In(),getALLGuest()))
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toList());

        List<Room> rooms = new CopyOnWriteArrayList<>();
        rooms.addAll(iMutipleSearchStrategy.searchGuest(guest,room,db));
        for (Guest g : collect) {
            for (Room r : rooms) {
                if (r.getRoom_no() == g.getRoomNum() && r.getRoom_location().equalsIgnoreCase(g.getLocation())) {
                    rooms.remove(r);
                }
            }
        }
        return rooms;
    }


    public List<Guest> getInhouseRoom(String str)
    {
        iGetLimitedTimeRoomStrategy = rulesFactory.getLimitedTimeRoom(SearchType.IN_HOUSE.toString());
        return iGetLimitedTimeRoomStrategy.getLimitedTimeRoom(str,getALLGuest());
    }

    public List<Room> getRoomNUMSearchRoom(String str)
    {
        iSearchStrategy = rulesFactory.getSearch(SearchType.ROOM.toString());
        return iSearchStrategy.searchRoom(str,db);
    }

    public List<Guest> getRoomNUMSearchGuest(String str, List<Guest> guests)
    {
        iSearchStrategy = rulesFactory.getSearch(SearchType.NAME.toString());
        return iSearchStrategy.searchGuest(str,db);
    }

    public List<Guest> getDepartureSearchGuest(String str)
    {
        iSearchStrategy = rulesFactory.getSearch(SearchType.DEPARTURE.toString());
        return iSearchStrategy.searchGuest(str,db);
    }

    public List<Guest> getArrivalSearchGuest(String str)
    {
        iSearchStrategy = rulesFactory.getSearch(SearchType.ARRIVAL.toString());
        return iSearchStrategy.searchGuest(str,db);
    }

    public List<Guest> getALLGuest()
    {
        iSearchStrategy = rulesFactory.getSearch(SearchType.QUERY_ALL.toString());
        return iSearchStrategy.searchGuest("",db);
    }

    public List<Room> queryAllRoom()
    {
        iSearchStrategy = rulesFactory.getSearch(SearchType.QUERY_ALL.toString());
        return iSearchStrategy.searchRoom("",db);
    }

    public boolean logIn(String username, String password)
    {
        iLogInStrategy = rulesFactory.LogIN();
        return iLogInStrategy.login(username, password,db);
    }
}
