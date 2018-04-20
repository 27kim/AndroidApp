package com.m01_3contact;

import io.realm.Realm;
import io.realm.RealmObject;

public class ContactVO extends RealmObject {
    public String name;
    public String phone;
    public String email;

}
