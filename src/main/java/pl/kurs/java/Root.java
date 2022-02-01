package pl.kurs.java;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Root {
    public List<Participant> participants;
    public List<Message> messages;
    public String title;
    @JsonProperty("is_still_participant")
    public boolean isStillParticipant;
    public String thread_type;
    public String thread_path;
    public List<Object> magic_words;
}
