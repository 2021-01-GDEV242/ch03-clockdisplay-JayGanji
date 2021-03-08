
/**
 * The ClockDisplay class implements a digital clock display for a
 * 12 hour clock. The clock shows hours and minutes. The 
 * range of the clock is 12:00 (midnight) to 11:59 (one minute before 
 * midnight) with meridians
 * 
 * The clock display receives "ticks" (via the timeTick method) every minute
 * and reacts by incrementing the display. This is done in the usual clock
 * fashion: the hour increments when the minutes roll over to zero.
 * 
 * 3.38
 * 
 * @author Michael Kölling and David J. Barnes, modified by Jay Ganji
 * @version 2021.02.06
 */
public class ClockDisplay
{
    private NumberDisplay hours;
    private NumberDisplay minutes;
    private String displayString;    // simulates the actual display
    private boolean meridian;
    
    /**
     * Constructor for ClockDisplay objects. This constructor 
     * creates a new clock set at 00:00.
     */
    public ClockDisplay()
    {
        hours = new NumberDisplay(13);
        if(hours.getValue()==0){
            hours.setValue(12);
        }
        minutes = new NumberDisplay(60);
        meridian = false;
        get12HourInternalDisplay();
    }

    /**
     * Constructor for ClockDisplay objects. This constructor
     * creates a new clock set at the time specified by the 
     * parameters.
     * false for AM
     * true for PM
     */
    public ClockDisplay(int hour, int minute, boolean meridian)
    {
        hours = new NumberDisplay(13);
        if(hours.getValue()==0){
            hours.setValue(12);
        }
        minutes = new NumberDisplay(60);
        setTime(hour, minute, meridian);
    }

    /**
     * This method should get called once every minute - it makes
     * the clock display go one minute forward.
     */
    public void timeTick()
    {
        minutes.increment();
        if(minutes.getValue() == 0) {  // it just rolled over!
            if (hours.getValue()!=12){
                hours.increment();
                if(hours.getValue()==12){
                    meridian = !meridian;
                }
            }
            else{
                hours.setValue(1);
            }
        }
        get12HourInternalDisplay();
    }

    /**
     * Set the time of the display to the specified hour and
     * minute.
     * false for AM
     * true for PM
     */
    public void setTime(int hour, int minute, boolean meridian)
    {
        hours.setValue(hour);
        minutes.setValue(minute);
        this.meridian = meridian;
        get12HourInternalDisplay();
    }

    /**
     * Return the current time of this display in the format HH:MM.
     */
    public String getTime()
    {
        return displayString;
    }
    
    /**
     * Update the internal string that represents the display.
     */
    private void get12HourInternalDisplay()
    {
        if (!meridian){
            displayString = hours.getDisplayValue() + ":" + 
                        minutes.getDisplayValue() + " AM";
        }
        if (meridian){
            displayString = hours.getDisplayValue() + ":" + 
                        minutes.getDisplayValue() + " PM";
        }
    }
}
