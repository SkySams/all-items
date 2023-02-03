package org.example.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
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


    List<Integer> one = new ArrayList<>();
    List<Integer> two = new ArrayList<>();

    @Test
    public void concat (){
        this.init();
        //方法1
        List<Integer> result = Stream.of(rooms_one, rooms_two).flatMap(List::stream).map(Room::getId).collect(Collectors.toList());
        System.out.println(result);

        //方法2
        List<Integer> result2 = Stream.concat(rooms_one.stream().map(Room::getId), rooms_two.stream().map(Room::getId)).collect(Collectors.toList());
        System.out.println(result2);

        // 两个list 取出交集
        List<Integer> concat = one.stream().filter(two::contains).collect(Collectors.toList());
        System.out.println(concat);

        //java 将list里面根据实体类某个字段生成新的Map＜字段，实体＞
        List<Room> list = new ArrayList<>();
        Map<Integer, Room> map = list.stream().collect(Collectors.toMap(Room::getId, Function.identity()));

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

        one.add(1);
        one.add(2);
        one.add(3);

        two.add(3);
        two.add(4);
        two.add(5);
        two.add(6);

    }

}
