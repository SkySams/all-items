package org.example.pattern.adapter.boat;

/**
 * @author: zyh
 * @date: 2022/12/19
 */
public class Captain {

    private RowingBoat rowingBoat;

    public Captain() {
    }

    public Captain(RowingBoat boat) {
        this.rowingBoat = boat;
    }

    public void setRowingBoat(RowingBoat boat) {
        this.rowingBoat = boat;
    }

    public void row(){
        rowingBoat.row();
    }
}
