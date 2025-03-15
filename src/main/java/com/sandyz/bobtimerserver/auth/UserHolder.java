package com.sandyz.bobtimerserver.auth;

import com.sandyz.bobtimerserver.pojo.User;

public class UserHolder {
    private static ThreadLocal<User> userThreadLocal = new ThreadLocal<>();
    public static void setUser(User user) {
        userThreadLocal.set(user);
    }
    public static User getUser() {
        return userThreadLocal.get();
    }
    public static int getUserId() {
        return userThreadLocal.get() == null ? -1 : userThreadLocal.get().getId();
    }
    public static void removeUser() {
        userThreadLocal.remove();
    }
}
