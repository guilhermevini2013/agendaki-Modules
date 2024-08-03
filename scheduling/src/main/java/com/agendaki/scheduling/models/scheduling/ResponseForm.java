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

    public ResponseForm() {

    }
}
