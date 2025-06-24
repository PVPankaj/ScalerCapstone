package com.scaler.capstone.jobportal.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sequence")
@Data
@Schema(description = "Represents a sequence generator for maintaining incremental IDs")
public class Sequence {
    @Schema(description = "Name or type of the sequence")
    private String id;

    @Schema(description = "Current sequence value")
    private Long seq;
}
