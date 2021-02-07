
/**
 * The ClockDisplay class implements a digital clock display for a
 * 12 hour clock. The clock shows hours and minutes. The 
 * range of the clock is 12:00 AM (midnight) to 11:59 PM (one minute before 
 * midnight).
 * 
 * The clock display receives "ticks" (via the timeTick method) every minute
 * and reacts by incrementing the display. This is done in the usual clock
 * fashion: the hour increments when the minutes roll over to zero.
 * 
 * 3.39
 * The option to only change the displayValues in the Strings is much much
 * easier. I think the 12-hr intervals clock is better because it processes
 * the meridian state much more correctly. This is important for manually
 * setting the object's time
 * 
 * @author Michael Kölling and David J. Barnes, modified by Jay Ganji
 * @version 2021.02.06
 */
public class ClockDisplay
{
    private NumberDisplay hours;
    private NumberDisplay minutes;
    private String displayString;    // simulates the actual display
    
    /**
     * Constructor for ClockDisplay objects. This constructor 
     * creates a new clock set at 00:00.
     */
    public ClockDisplay()
    {
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        updateDisplay();
    }

    /**
     * Constructor for ClockDisplay objects. This constructor
     * creates a new clock set at the time specified by the 
     * parameters.
     */
    public ClockDisplay(int hour, int minute)
    {
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        setTime(hour, minute);
    }

    /**
     * This method should get called once every minute - it makes
     * the clock display go one minute forward.
     */
    public void timeTick()
    {
        minutes.increment();
        if(minutes.getValue() == 0) {  // it just rolled over!
            hours.increment();
        }
        updateDisplay();
    }

    /**
     * Set the time of the display to the specified hour and
     * minute.
     */
    public void setTime(int hour, int minute)
    {
        hours.setValue(hour);
        minutes.setValue(minute);
        updateDisplay();
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
    private void updateDisplay()
    {
        if (hours.getValue() == 0){
            displayString = "12:" + minutes.getDisplayValue() + " AM";
        }
        else if (hours.getValue() >= 13){
            hours.setValue(hours.getValue()-12);
            displayString = hours.getDisplayValue() + ":" + 
                        minutes.getDisplayValue() + " PM";
        }
        else{
            displayString = hours.getDisplayValue() + ":" + 
                        minutes.getDisplayValue() + " AM";
        }    
    }
}
