import java.util.Arrays;
/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version    2016.02.29
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    private int[] dayCounts;
    private int[] monthCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer()
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        dayCounts = new int[28];
        monthCounts = new int [12];
        // Create the reader to obtain the data.
        reader = new LogfileReader("demo.log");
    }
    /**
     * Sends the file name to LogfileReader.
     */
    public LogAnalyzer(String fileName)
    {
        reader = new LogfileReader(fileName);
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        reader.reset();
        while(reader.hasNext()) 
        {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }
    
    /**
     * Analyze the daily access data from the log file.
     */
    public void analyzeDailyData()
    {
        reader.reset();
        while(reader.hasNext()) 
        {
            LogEntry entry = reader.next();
            int day = entry.getDay();
            hourCounts[day]++;
        }
    }
    
    /**
     * Analyze the monthly access data from the log file.
     */
    public void analizeMonthlyData()
    {
        reader.reset();
        while(reader.hasNext()) 
        {
            LogEntry entry = reader.next();
            int month = entry.getMonth();
            hourCounts[month]++;
        }
    }
    
    /**
     * Analyze the yearly access data from the log file.
     */
    public void analizeYearlyData()
    {
        reader.reset();
        while(reader.hasNext()) 
        {
            LogEntry entry = reader.next();
            int year = entry.getYear();
            hourCounts[year]++;
        }
    }
    
    /**
     * returns the time the website was accesed most.
     * returns -1 if something went wrong.
     */
    public int busiestHour()
    {
        int hourlyAcceses = -1000;
        int busyHour = -1; 
        for(int i = 0; i < hourCounts.length; i++)
        {
            if (hourCounts[i] > hourlyAcceses)
            {
                hourlyAcceses = hourCounts[i];
                busyHour = i;
            }
        }
        return busyHour;
    }
    
    /**
     * returns the time the website was accesed most over the corse of two hours.
     * returns -1 if something went wrong.
     */
    public int busiestTwoHours()
    {
        int twoHourAcceses = -1000;
        int busyHours = -1; 
        for(int i = 0; i < hourCounts.length; i++)
        {
            if (hourCounts[i] + hourCounts[i + 1] > twoHourAcceses)
            {
                twoHourAcceses = hourCounts[i] + hourCounts[i + 1];
                busyHours = i;
            }
        }
        return busyHours;
    }
    
    /**
     * returns the time the website was accesed least.
     * returns -1 if something went wrong.
     */
    public int quietestHour()
    {
        int hourlyAcceses = Integer.MAX_VALUE;
        int quietHour = -1;
        for(int i = 0; i < hourCounts.length; i++)
        {
            if (hourCounts[i] < hourlyAcceses)
            {
                hourlyAcceses = hourCounts[i];
                quietHour = i;
            }
        }
        return quietHour;
    }
    
    /**
     * returns the number of acceses stored in the log file.
     */
    public int totalAccessesPerMonth()
    {
        int total = 0;
        for(int i : dayCounts)
        {
            total += i;
        }
        return total;
    }
    
    /**
     * Returns the quietest day out of the month.
     * returns -1 if something went wrong.
     */
    public int quietestDay()
    {
        int dailyAcceses = Integer.MAX_VALUE;
        int quietDay = -1; 
        for(int i = 0; i < dayCounts.length; i++)
        {
            if (dayCounts[i] < dailyAcceses)
            {
                dailyAcceses = dayCounts[i];
                quietDay = i;
            }
        }
        return quietDay;
    }
    
    /**
     * Returns the busiest day out of the month.
     * returns -1 if something went wrong.
     */
    public int busiestDay()
    {
        int dailyAcceses = -1000;
        int busyDay = -1; 
        for(int i = 0; i < dayCounts.length; i++)
        {
            if (dayCounts[i] > dailyAcceses)
            {
                dailyAcceses = dayCounts[i];
                busyDay = i;
            }
        }
        return busyDay;
    }
    
    /**
     * Returns the busiest month out of the year.
     * returns -1 if something went wrong.
     */
    public int busiestMonth()
    {
        int monthlyAcceses = -1000;
        int busyMonth = -1; 
        for(int i = 0; i < monthCounts.length; i++)
        {
            if (monthCounts[i] > monthlyAcceses)
            {
                monthlyAcceses = monthCounts[i];
                busyMonth = i;
            }
        }
        return busyMonth;
    }
    
    /**
     * Returns the quietest month out of the year.
     * returns -1 if something went wrong.
     */
    public int quietestMonth()
    {
        int monthlyAcceses = Integer.MAX_VALUE;
        int quietMonth = -1; 
        for(int i = 0; i < dayCounts.length; i++)
        {
            if (monthCounts[i] < monthlyAcceses)
            {
                monthlyAcceses = monthCounts[i];
                quietMonth = i;
            }
        }
        return quietMonth;
    }
    
    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        for(int hour = 0; hour < hourCounts.length; hour++) 
        {
            System.out.println(hour + ": " + hourCounts[hour]);
        }
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
    
    /**
    * Return the number of accesses recorded in the log file.
    */
    public int numberOfAccesses()
    {
        int total = 0;
        for(int i : hourCounts)
        {
            total += i;
        }
        return total;
    }
}
