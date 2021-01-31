package com.example.chat.Notification;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers(
            {


                    "Content-Type:application/json",
                    "Authorization:key=AAAAP3khQMQ:APA91bFgMadsIkti0rKWJTYBhBD2cZiRmlC4KbD4Yrx2MMmLh307PGvkg49jBzHUT67V78rJF_3IkKN4bDmbY8xyTdl5D-hDdykxJkq1ZogfDMB_om75j618lLYYOt5x6oujd75Ltef"
            }
    )
    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);
}

