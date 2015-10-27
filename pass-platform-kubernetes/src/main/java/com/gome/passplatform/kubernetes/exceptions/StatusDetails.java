package com.gome.passplatform.kubernetes.exceptions;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jersey.repackaged.com.google.common.base.MoreObjects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StatusDetails {
    private String id, kind;
    private List<StatusDetailsCause> causes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public List<StatusDetailsCause> getCauses() {
        return causes;
    }

    public void setCauses(List<StatusDetailsCause> causes) {
        this.causes = causes;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("id", id).add("kind", kind).add("causes", causes).toString();
    }
}
