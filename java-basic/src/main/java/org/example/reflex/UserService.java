package org.example.reflex;

/**
 * @author: zyh
 * @date: 2022/11/29
 */
public class UserService {

    public static void main(String[] args) throws Exception {
        m1();
        m1(1, 3, 5, 7, 9);
        m2("abc");
        m2("abc", 123, 456, 789);
        Integer[] integers = {333, 666, 999, 111};
        m2("传入一个数组", integers);
    }
    public static void m1(Integer... args) {
        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
        }
    }
    public static void m2(String str, Integer... args) {
        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
        }
        System.out.println(str);
    }

    public boolean login(String username, String password) {
        return "admin".equals(username) && "123".equals(password);
    }

    public String logout() {
        return "Successfully Logout";
    }

}
