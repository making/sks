package sks.domain.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CleaningEvent {
    private final Integer cleaningTypeId;
    private final LocalDateTime cleaningDateTime;
}
