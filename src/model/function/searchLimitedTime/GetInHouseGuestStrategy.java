package model.function.searchLimitedTime;

import model.Guest;
import model.function.IGetLimitedTimeRoomStrategy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class GetInHouseGuestStrategy implements IGetLimitedTimeRoomStrategy {
    @Override
    public List<Guest> getLimitedTimeRoom(String str, List<Guest> guests) {
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            List<Guest> guestList = new ArrayList<>();

            for(Guest guest : guests)
            {
                if(dateFormat.parse(str).compareTo(dateFormat.parse(guest.getCheck_In())) > 0
                        && dateFormat.parse(str).compareTo(dateFormat.parse(guest.getCheck_Out()))<0)
                {
                    guestList.add(guest);
                }
            }
            return guestList;
        } catch (ParseException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
