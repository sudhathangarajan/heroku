package io.spd.csp.fieldmgmt;

public interface Constants {

    interface Web {

        String TECHNICIAN_PATH = "/technician";

        String MANAGER_PATH = "/manager";
    }

    interface Security {

        String TOKEN_KEY = "X-SESSION-ID";

        String ROLE_PREFIX = "ROLE_";
    }
}
