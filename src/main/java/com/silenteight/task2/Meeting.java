package com.silenteight.task2;

import java.util.List;

public class Meeting {

    private long id;
    private String subject;
    private Category category;
    private Room room;
    private List<Resource> resourceList;
    private List<Employee> requiredParticipantList;
    private List<Employee> optionalParticipantList;
    private Time time;
    private Recurrence recurrence;
    private Meeting nextRecurrenceMeeting;
    private Meeting previousRecurrenceMeeting;
}
