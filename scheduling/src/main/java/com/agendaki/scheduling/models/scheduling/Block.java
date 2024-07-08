package com.agendaki.scheduling.models.scheduling;

import com.agendaki.scheduling.models.user.Instance;
import jakarta.persistence.*;

@Entity
@Table(name = "user_Block")
public class Block {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Instance instance;
    private String ipBlocked;
    @Column(length = 120)
    private String motive;

    public Block() {
    }
}
