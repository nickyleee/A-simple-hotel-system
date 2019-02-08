package model.function;

import model.DoTypes;
import model.SearchType;
import model.function.guest.AddGuestStrategy;
import model.function.guest.EditGuestStrategy;
import model.function.room.AddRoomStrategy;
import model.function.room.DeleteRoomStrategy;
import model.function.room.EditRoomStrategy;
import model.function.search.*;
import model.function.searchLimitedTime.GetInHouseGuestStrategy;
import model.function.searchLimitedTime.GetUsedRoomStrategy;

public class RulesFactory {
    public ISearchStrategy getSearch(String string) {
        if(string == null)
        {
            return null;
        }

        if(string.equalsIgnoreCase(SearchType.ARRIVAL.toString()))
        {
            return new SearchByArrivalListStrategy();
        } else if(string.equalsIgnoreCase(SearchType.DEPARTURE.toString()))
        {
            return new SearchByDepartureListStrategy();
        } else if(string.equalsIgnoreCase(SearchType.ROOM.toString()))
        {
            return new SearchByRoomStrategy();
        } else if(string.equalsIgnoreCase(SearchType.QUERY_ALL.toString()))
        {
            return new QueryAllStrategy();
        } else if(string.equalsIgnoreCase(SearchType.NAME.toString()))
        {
            return new SearchByNameStrategy();
        }
        return null;

    }

    public IGuestStrategy getGuest(String string) {
        if(string == null)
        {
            return null;
        }

        if(string.equalsIgnoreCase(DoTypes.ADD.toString()))
        {
            return new AddGuestStrategy();
        } else if(string.equalsIgnoreCase(DoTypes.EDIT.toString()))
        {
            return new EditGuestStrategy();
        }

        return null;
    }

    public IRoomStrategy getRoom(String string) {
        if(string == null)
        {
            return null;
        }

        if(string.equalsIgnoreCase(DoTypes.ADD.toString()))
        {
            return new AddRoomStrategy();
        } else if(string.equalsIgnoreCase(DoTypes.DELETE.toString()))
        {
            return new DeleteRoomStrategy();
        } else if(string.equalsIgnoreCase(DoTypes.EDIT.toString()))
        {
            return new EditRoomStrategy();
        }

        return null;
    }

    public IMutipleSearchStrategy getMutipleSearch(String string) {
        if(string == null)
        {
            return null;
        }

        if(string.equalsIgnoreCase(SearchType.MULTIPLE.toString()))
        {
            return new MutipleSearchStrategy();
        }

        return null;
    }

    public ILogInStrategy LogIN()
    {
        return new LogInStrategy();
    }

    public IGetLimitedTimeRoomStrategy getLimitedTimeRoom(String string)
    {
        if(string == null)
        {
            return null;
        }

        if(string.equalsIgnoreCase(SearchType.IN_HOUSE.toString()))
        {
            return new GetInHouseGuestStrategy();
        } else if(string.equalsIgnoreCase(SearchType.USE_ROOM.toString()))
        {
            return new GetUsedRoomStrategy();
        }

        return null;
    }
}
