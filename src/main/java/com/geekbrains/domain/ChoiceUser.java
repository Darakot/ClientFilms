package com.geekbrains.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChoiceUser {
    private String user;
    private String look;
    private String favorite;
    private String watched;
    private String dontShowAgain;

    public ChoiceUser(String user, String look, String favorite, String watched, String dontShowAgain) {
        this.user = user;
        this.look = look;
        this.favorite = favorite;
        this.watched = watched;
        this.dontShowAgain = dontShowAgain;
    }
}
