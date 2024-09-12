package com.agendaki.scheduling.models.scheduling;

import com.agendaki.scheduling.models.template.Input;
import jakarta.persistence.*;

@Entity
public class ResponseForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Input input;
    private String response;
    @ManyToOne
    private Scheduling scheduling;

    public ResponseForm() {

    }

    public ResponseForm(Long idInput, String response, Scheduling scheduling) {
        this.input = new Input(idInput);
        this.response = response;
        this.scheduling = scheduling;
    }

    public Input getInput() {
        return input;
    }

    public String getResponse() {
        return response;
    }

    public Scheduling getScheduling() {
        return scheduling;
    }
}
