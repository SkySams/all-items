package org.example.stream;

import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Java 将两个对象list里面的 某个字段值抽取到一个list里
 * @author: zyh
 * @date: 2022/3/5
 */
public class StreamBasi {

    static List<Room> rooms_one = new ArrayList<>();
    static List<Room> rooms_two = new ArrayList<>();

    static List<Integer> one = new ArrayList<>();
    static List<Integer> two = new ArrayList<>();
    static {
        init();
    }

    @Test
    public void sm(){
        Map<Integer,Integer> map = new HashMap<>();
        map.put(1,23);
        map.put(3,23);
        System.out.println(map);
    }

    @Test
    public void stream_reduce(){
        System.out.println(Stream.of(1,2,3).reduce(0, (a, b) -> a + b));
        System.out.println(Stream.of(1,2,3).reduce((a, b) -> a + b).get());
    }

    @Test
    public void stream_skip(){
        System.out.println(Stream.of(1,2,3,4,5,6,7).skip(1).map(m->m+"").collect(Collectors.joining(",")));
    }

    @Test
    public void stream_sorted(){
        System.out.println(Stream.of(6,1,3,2,5,9).sorted().map(n->n+"").collect(Collectors.joining(",")));
        System.out.println(Stream.of(6,1,3,2,5,9).sorted());
    }

    @Test
    public void stream_noneMatch(){
        System.out.println(Stream.of(1,2,3,4).noneMatch(n->n >= 1));
        System.out.println(Stream.of(1,2,3,4).noneMatch(n->n >= 3 || n < 1));
    }

    @Test
    public void stream_min(){
        /**
         * min 通过给定的比较器，将最小的元素取出来，返回 Optional
         */
        System.out.println(Stream.of(1,2,3,4).min(Comparator.comparingInt(a -> a)).get());
    }

    @Test
    public void stream_max(){
        /**
         * max 通过给定的比较器，将最大的元素取出来，返回 Optional
         * Comparato.comparingInt()
         */
        System.out.println(Stream.of(1,2,3,4).max(Comparator.comparingInt(a -> a)).get());
    }

    @Test
    public void stream_map(){
        /**
            map 方法的作用是依次对 Stream 中的元素进行指定的函数操作，并将按顺序将函数操作的返回值组合到一个新的 Stream 中。
            下面例子将每个元素的值 +1
         */
        System.out.println(Stream.of(1,2,3,4).map(n-> n+1).map(String::valueOf).collect(Collectors.joining(",")));

    }

    @Test
    public void stream_limit(){
        System.out.println(Stream.of(1,2,3,4).limit(2).map(n-> n+ "").collect(Collectors.joining(",")));
    }

    @Test
    public void stream_flatMap(){
        System.out.println(Stream.of(new Integer[] {1,2,3},new Integer[]{4,5,6},new Integer[]{7,8,9})
                .flatMap(a -> Arrays.stream(a))
                .map(n -> n+"").collect(Collectors.joining(",")));
    }
    @Test
    public void stream_findAny(){
        /**
         * 返回任何一个不确定的元素，通过 Optional 来包装。如果在一个固定不变的组合中，返回第一个元素
         */
        System.out.println(Stream.of().findAny().orElse("nice"));
        /**
         * 返回第一个元素。
         */
        System.out.println(Stream.of(10,2,3,4).findFirst().get());
        /**
         * 逐个元素执行 Consumer 操作。
         */
        Stream.of(1,2,3,4).forEach(n -> System.out.println(n+ " "));

    }

    @Test
    public void stream_filter(){
        String result = Stream.of(1,2,3).filter(n-> n > 1).map(n->n+"").collect(Collectors.joining(","));
        System.out.println(result);
    }

    @Test
    public void stream_distinct(){
        System.out.println(Stream.of(1,2,3,3).distinct().map(n->n+"").collect(Collectors.joining(",")));
    }

    @Test
    public void stream_count(){
    }

    @Test
    public void stream_collect(){
        System.out.println(Stream.of("A","C","D").collect(Collectors.joining(",")));
        System.out.println(Stream.of("A","C","D").collect(Collectors.toList()));
        System.out.println(Stream.of("A","C","D").collect(Collectors.toSet()));
        Map<Integer, List<Room>> map = rooms_one.stream().collect(Collectors.groupingBy(Room::getId));
        System.out.println(map);
        Map<Integer,Map<String,List<Room>>> mapMap = rooms_two.stream().collect(Collectors.groupingBy(Room::getId,Collectors.groupingBy(Room::getName)));
        System.out.println(mapMap);

    }


    @Test
    public void stream_allmatch(){
        System.out.println(Stream.of(0,1,2,3).allMatch(n -> n >= 1 ));
        System.out.println(Stream.of(2,32).allMatch(n-> n>=2));
    }

    @Test
    public void concat (){
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

    public static void init(){
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
