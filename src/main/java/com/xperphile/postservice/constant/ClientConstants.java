package com.xperphile.postservice.constant;

public class ClientConstants {
    public static final String CLIENT_CONTROLLER_MAPPING = "/post";
    public static final String SEARCH_CONTROLLER_MAPPING = "/search";
    public static final String CLIENT_ADD_POSTS = "/add";

    public static final int DIRECT_COMMENT = 0;
    public static final int EMOJI = 1;
    public static final int REPLY = 2;

    public static final int RECOMMENDATION_FROM_ENGINE = 0;
    public static final int RECOMMENDATION_FROM_USER = 1;
    public static final int TAGGED = 2;

    public static final String DELIMITER = ",";

    public static final String MESSAGE_TO_MAPPING_FILE = "message_to_mapping.json";
    public static final String POSTMESSAGE_TO_FUNNELMESSAGE_FILE = "postmessage_to_funnelmessage.json";
    public static final String POSTMESSAGE_TO_FUNNELMETHOD_FILE = "postmessage_to_funnelmethod.json";

}
