package dirOne;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Task {

    private final StringProperty nr;
    private final BooleanProperty check;
    private final StringProperty notes;
    private final StringProperty user;

    public Task(String nr, boolean check, String notes) {
        this.nr = new SimpleStringProperty(nr);
        this.check = new SimpleBooleanProperty(check);
        this.notes = new SimpleStringProperty(notes);
        this.user = new SimpleStringProperty("");
    }
    public String getnr() { return nr.get();}

    public void setnr(String nr) { this.nr.set(nr); }

    public StringProperty nrProperty() { return nr; }

    public boolean getCheck() { return check.get(); }

    public void setCheck(boolean check) { this.check.set(check); }

    public BooleanProperty checkProperty() { return check; }

    public String getNotes() { return notes.get(); }

    public void setNotes(String notes) { this.notes.set(notes); }

    public StringProperty notesProperty() { return notes; }

    public String getUser() { return user.get(); }

    public void setUser(String user) { this.user.set(user); }

    public StringProperty userProperty() { return user; }
}
