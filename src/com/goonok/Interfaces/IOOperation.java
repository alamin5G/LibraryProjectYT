package com.goonok.Interfaces;

import com.goonok.DB.Database;
import com.goonok.User.User;

public interface IOOperation {
    public void oper(Database database, User user);
}
