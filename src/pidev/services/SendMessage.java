/*package pidev.services;

import static pidev.services.Until.configureLogging;
import static pidev.services.Until.envVar;




public class SendMessage {

    public static void main(String[] args) throws Exception {
        configureLogging();

        String NEXMO_API_KEY = envVar("372d5729");
        String NEXMO_API_SECRET = envVar("n3FnzJypbJxuwj5G");
        String TO_NUMBER = envVar("+216899579");
        String NEXMO_BRAND_NAME = envVar("NEXMO_NUMBER");

        NexmoClient client = NexmoClient.builder().apiKey("372d5729").apiSecret("n3FnzJypbJxuwj5G").build();
        

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

