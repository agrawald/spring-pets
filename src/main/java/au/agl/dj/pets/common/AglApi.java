package au.agl.dj.pets.common;

import lombok.Getter;

/**
 * Enumeration og all the API exposed by AGL
 * We are locking the API down to freeze the contract.
 * If the API contract changes we need someone to modify this code and send it for approval.
 */
public enum AglApi {
    PEOPLE("/people.json");

    @Getter
    private String api;

    AglApi(String api) {
        this.api = api;
    }
}
