package sks.domain.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CleaningEvent {
    private final int cleaningTypeId;
    private final String cleaningUser;
    private final LocalDate cleaningDate;
    private final String registerUser;
    private final LocalDate registerDate;
}
