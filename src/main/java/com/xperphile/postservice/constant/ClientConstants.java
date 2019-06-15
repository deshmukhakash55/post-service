package com.xperphile.postservice.constant;

public class ClientConstants {
    public static final String CLIENT_CONTROLLER_MAPPING = "/post";
    public static final String SEARCH_CONTROLLER_MAPPING = "/search";
    public static final String SUBSCRIPTION_CONTROLLER_MAPPING = "/subscription";

    public static final String CLIENT_ADD_POSTS = "/add";
    public static final String CLIENT_GET_POSTS = "/get";
    public static final String CLIENT_ADD_TAG_TO_POST = "/tag/add";
    public static final String CLIENT_REMOVE_TAG_FROM_POST = "/tag/remove";
    public static final String CLIENT_REMOVE_TAGGED_USER_FROM_POST = "/tagged_user/remove";
    public static final String CLIENT_ADD_TAGGED_USER_TO_POST = "/tagged_user/add";
    public static final String CLIENT_UPDATE_POST = "/update/{post_id}";
    public static final String CLIENT_ADD_COMMENT_OR_EMOJI = "/comment_emoji";
    public static final String CLIENT_ADD_RECOMMENDATION = "/recommendation/add";
    public static final String CLIENT_REMOVE_RECOMMENDATION = "/recommendation/remove";
    public static final String CLIENT_GET_RECOMMENDATION = "/recommendation/get";

    public static final String CLIENT_SEARCH_BY_TAG = "/tag";
    public static final String CLIENT_SEARCH_BY_TAGGED_USER = "/tagged_user";
    public static final String CLIENT_SEARCH_BY_TEXT = "/text";

    public static final String CLIENT_ADD_POST_SUBSCRIPTION = "/add";
    public static final String CLIENT_REMOVE_POST_SUBSCRIPTION = "/remove";

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
