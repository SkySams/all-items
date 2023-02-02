package org.example.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Java 将两个对象list里面的 某个字段值抽取到一个list里
 * @author: zyh
 * @date: 2022/3/5
 */
public class StreamBasi {

    List<Room> rooms_one = new ArrayList<>();
    List<Room> rooms_two = new ArrayList<>();

    @Test
    public void concat (){
        this.init();
        //方法1
        List<Integer> result = Stream.of(rooms_one, rooms_two).flatMap(List::stream).map(Room::getId).collect(Collectors.toList());
        System.out.println(result);

        //方法2
        List<Integer> result2 = Stream.concat(rooms_one.stream().map(Room::getId), rooms_two.stream().map(Room::getId)).collect(Collectors.toList());
        System.out.println(result2);

    }

    public void init(){
        Room roomA=new Room();
        roomA.setId(1);
        roomA.setName("1-A");
        roomA.setType((short)1);
        Room roomB=new Room();
        roomB.setId(2);
        roomB.setName("2-A");
        roomB.setType((short)1);

        rooms_one.add(roomA);
        rooms_one.add(roomB);


        Room roomC=new Room();
        roomC.setId(3);
        roomC.setName("3-C");
        roomC.setType((short)2);
        Room roomD=new Room();
        roomD.setId(4);
        roomD.setName("4-D");
        roomD.setType((short)2);

        rooms_two.add(roomC);
        rooms_two.add(roomD);

    }

}
