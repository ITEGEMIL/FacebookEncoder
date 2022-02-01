package pl.kurs.java;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Message{
    @JsonProperty("sender_name")
    private String senderName;
    private Object timestamp_ms;
    private String content;
    private String type;
    private boolean is_unsent;
    private int call_duration;
    private Sticker sticker;
}
