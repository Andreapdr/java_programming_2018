package backend;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Entry implements Comparable, Serializable {

    private Calendar calendar;
    private double price;
    private long id;
    private Member member;

    private long timestamp = System.currentTimeMillis() / 1000L;


    Entry(Calendar calendar, double price) {

        this.calendar = calendar;
        this.price = price;
        this.id = timestamp;

    }

    // Questo permette di usare una sola classe per entrambi i tipi di ingresso
    Entry(Calendar calendar, Member member) {
        // Chiama l'altro costruttore
        this(calendar, 0);
        this.member = member;
    }

    public String showFullDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM - HH:mm");
        return sdf.format(calendar.getTime());
    }

    // Quando backend.Entry viene richiesto come stringa (esempio quando stampi) viene
    // chiamato questo metodo
    @Override
    public String toString() {
        if (member != null) {
            return member.toString();
        }
        return showFullDate();
    }

    int getDay() {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    int getMonth() {
        return calendar.get(Calendar.MONTH) + 1;
    }

    private int getHour() {
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    private int getMinute() {
        return calendar.get(Calendar.MINUTE);
    }

    double getPrice() {
        return price;
    }

    @Override
    public int compareTo(Object o) {
        Entry o2 = (Entry) o;
        return this.calendar.compareTo(o2.calendar);
    }
}
