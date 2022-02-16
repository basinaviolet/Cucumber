package api.utils;

public class ApiValue {

    public static String APIKEY = ConfProperties.getProperty("apikey");
    public static String TOKEN = ConfProperties.getProperty("token");
    public static String APIKEY_TOKEN_STRING = "key=" + APIKEY + "&token=" + TOKEN;
 //   public static String TOKEN_FOR_API_TESTS = "&key=" + APIKEY_TOKEN_STRING;
    public static String API_URL = "https://api.trello.com/1/";
    public static String AUTHORIZED_URL = API_URL + "members/me/?" + APIKEY_TOKEN_STRING;
// https://api.trello.com/1/members/me/?key=3428f10e03eefade174a647aa155af34&token=94b9db6d636170a9f9bee11f4c81dca52f2b96e9d3801b2b80c2a232e8784bc5
    public static String getBoardInfoRequest = "members/me/boards?fields=name,url&";
    public static String getBoardInfoFilterRequest = "boards/?name=";



}
