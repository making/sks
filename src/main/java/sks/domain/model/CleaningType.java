package sks.domain.model;

import lombok.Data;

@Data
public class CleaningType {
    private final int cleaningTypeId;
    private final String cleaningTypeName;
    private final int cleaningTypeCycle;
}
