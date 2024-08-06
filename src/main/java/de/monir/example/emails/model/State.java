package de.monir.example.emails.model;

import lombok.Getter;

@Getter
public enum State {
    DRAFT(0), SENT(1), DELETED(2), SPAM(3);
    private final int code;
    State(int code) {
        this.code = code;
    }

    public boolean equals(State state){
        return this.code == state.code;
    }

}
