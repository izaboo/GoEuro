package main.java.helpers;

import java.util.List;

/**
 * Created by xsoroka on 3/6/2016.
 */
public class PrintOptions {

    public static void print(String info)
    {
        System.out.println(info);
    }

    public static void print(Object info)
    {
        System.out.println(info);
    }

    public static void print(List info)
    {
        for (Object object : info)
            System.out.println(object);
    }
}
