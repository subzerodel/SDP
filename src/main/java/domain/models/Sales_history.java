package domain.models;


import java.sql.Date;

public class Sales_history {
    private long id;
    private long shoes_id;
    private Date date;

    public Sales_history(long id, long shoes_id, Date date) {
        this.id = id;
        this.shoes_id = shoes_id;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getShoes_id() {
        return shoes_id;
    }

    public void setShoes_id(long shoes_id) {
        this.shoes_id = shoes_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
