package com.zs.blog.config;

import com.zs.blog.model.User;

/**
 * @author zshuo
 * @date 2019-05-13
 **/
public class UserContext implements AutoCloseable {
    static final ThreadLocal<User> current = new ThreadLocal<>();

    public UserContext(User user) {
        current.set(user);
    }

    public static User getCurrentUser() {
        return current.get();
    }

    @Override
    public void close() {
        current.remove();
    }
}
