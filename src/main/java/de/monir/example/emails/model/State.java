package de.monir.example.emails.model;

import lombok.Getter;

@Getter
public enum State {
    DRAFT(1), SENT(2), DELETED(3), SPAM(4);
    private final int code;
    State(int code) {
        this.code = code;
    }

    public boolean equals(State state){
        return this.code == state.code;
    }

}
