package com.digital.prescription.entities;

import java.util.Set;

public enum Role {
    ADMIN(Set.of(Permissions.DOCTOR_CREATE,Permissions.DOCTOR_LIST,Permissions.DOCTOR_EDIT,Permissions.DOCTOR_DELETE)),
    DOCTOR(Set.of(Permissions.DOCTOR_LIST,Permissions.DOCTOR_EDIT)),
    ASSISTANT(Set.of());

    private final Set<Permissions> permissions;

    Role(Set<Permissions> permissions){
        this.permissions = permissions;
    }

    public Set<Permissions> getPermissions() {
        return permissions;
    }
}