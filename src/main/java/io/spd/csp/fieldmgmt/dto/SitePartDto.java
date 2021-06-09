package io.spd.csp.fieldmgmt.dto;

public record SitePartDto(Integer id, Integer siteId, Integer partId, Integrity integrity) {

    public enum Integrity {

        FUNCTIONING,

        NEED_REPAIR,

        FOR_REPLACEMENT

        ;
    }
}
