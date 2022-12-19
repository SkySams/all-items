package org.example.pattern.adapter.boat;

/**
 * @author: zyh
 * @date: 2022/12/19
 */
public class FishingBoatAdapter implements  RowingBoat {

    private final FishingBoat boat = new FishingBoat();

    @Override
    public void row(){
        boat.sail();
    }

}
