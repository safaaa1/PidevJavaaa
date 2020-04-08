/*package pidev.services;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.sms.MessageStatus;
import com.nexmo.client.sms.SmsSubmissionResponse;
import com.nexmo.client.sms.messages.TextMessage;

import static pidev.services.Until.configureLogging;
import static pidev.services.Until.envVar;

public class SendMessage {

    public static void main(String[] args) throws Exception {
        configureLogging();

        String NEXMO_API_KEY = envVar("NEXMO_API_KEY");
        String NEXMO_API_SECRET = envVar("NEXMO_API_SECRET");
        String TO_NUMBER = envVar("TO_NUMBER");
        String NEXMO_BRAND_NAME = envVar("NEXMO_NUMBER");

        NexmoClient client = NexmoClient.builder().apiKey(NEXMO_API_KEY).apiSecret(NEXMO_API_SECRET).build();

        TextMessage message = new TextMessage(NEXMO_BRAND_NAME,
                TO_NUMBER,
                "A text message sent using the Nexmo SMS API"
        );

        SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);

        if (response.getMessages().get(0).getStatus() == MessageStatus.OK) {
            System.out.println("Message sent successfully.");
        } else {
            System.out.println("Message failed with error: " + response.getMessages().get(0).getErrorText());
        }
    }
}*/
