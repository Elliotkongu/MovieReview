package com.example.moviereview.storage.Person;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class Person {
    protected Long userId;
    protected String firstName;
    protected String surName;
    protected String dateOfBirth;
    protected String gender;
}
