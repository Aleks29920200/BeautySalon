package com.example.dealership1.domain.dto.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.function.Supplier;

public class ObjectNotFoundException extends Exception {
    private final long objectId;
    private final String objectType;

    public ObjectNotFoundException(long objectId, String objectType) {

        super("Object with ID " + objectId + " and type " + objectType + " not found!");

        this.objectId = objectId;
        this.objectType = objectType;
    }

    public long getObjectId() {
        return objectId;
    }

    public String getObjectType() {
        return objectType;
    }
}
