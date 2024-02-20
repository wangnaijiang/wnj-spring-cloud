package com.wnj.javabase;

import com.wnj.user.User;

/**
 * @author WangNaiJiang
 * @since 2023-12-21 10:40
 */
public class ParamPassTest {
    public static void main(String[] args) {
        int i1 = 1;
        primitivePass(i1);
        System.out.println("primitivePassAfter, i1="+i1);

        User user = new User();
        user.setName("jiang");
        referencePass(user);
        System.out.println("referencePassAfter, user.name=" + user.getName());

    }

    static void primitivePass(int i1){
        i1 = i1 + 1;
        System.out.println("primitivePassInner, i1=" + i1);
    }

    static void referencePass(User user){
        user.setName("river");
        System.out.println("referencePassInner, user.name=" + user.getName());

        // code to assign a new reference to circle。 方法返回后，会丢弃
        user = new User();
        user.setName("river2");
        System.out.println("referencePassInner2, user.name=" + user.getName());

    }
}
