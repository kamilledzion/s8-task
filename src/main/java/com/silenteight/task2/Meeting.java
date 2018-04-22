package com.silenteight.task2;

import java.util.List;

public class Meeting {

    private long id;
    private String subject;
    private Category category;
    private Room room;
    private List<Resource> resourceList;
    private List<Employee> requiredList;
    private List<Employee> optionalList;
    private Time time;
    private Recurrence recurrence;
    private Meeting nextMeeting;
    private Meeting previousMeeting;
}
