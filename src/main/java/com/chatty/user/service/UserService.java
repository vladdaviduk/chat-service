package com.chatty.user.service;

import java.util.List;

public interface UserService {

    List<String> getLoggedInUserNames();

    String getCurrentLoggedInUserName();
}
