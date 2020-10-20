package com.rsah.personalia.api;



import static com.rsah.personalia.util.Utility.BASE_URL_API;

public class Server {
    public static ApiService getAPIService() {

        return Client.getClient(BASE_URL_API).create(ApiService.class);
    }


}
