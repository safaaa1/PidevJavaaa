/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.services;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentMethod;
import com.stripe.model.Token;
import com.stripe.net.RequestOptions;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author islem
 */
public class PaymentService {

    /*private int monta;
    private int nc;
    public void  ajouterService(int montant , int numcard){
    monta = montant;
    nc = numcard;
    }*/
    //public void add(Payment e) throws StripeException{
    
    public void payer(String number, String exp_month, String exp_year, String cvc, String mount) {
     /*   try {
            //  public static void main(String[] args) throws StripeException  {
            Stripe.apiKey = "sk_test_eursBjS98N2TlnR7XznT7vQl008CI5m0Z7";

            Map<String, Object> card = new HashMap<>();
            card.put("number", number);
            card.put("exp_month", exp_month);
            card.put("exp_year", exp_year);
            card.put("cvc", cvc);
            Map<String, Object> params = new HashMap<>();
            params.put("card", card);

            Token token = Token.create(params);

            PaymentMethod paymentMethod = PaymentMethod.create(params);

//Stripe.apiKey = "sk_test_eursBjS98N2TlnR7XznT7vQl008CI5m0Z7";
            List<Object> paymentMethodTypes = new ArrayList<>();
            paymentMethodTypes.add("card");
            Map<String, Object> params1 = new HashMap<>();
            //Type = StripeSourceType.Bitcoin,
            params1.put("type", "Bitcoin");
            params1.put("amount", mount);
            params1.put("currency", "usd");
            params1.put("Source", token.getId());
            params1.put("payment_method_types", paymentMethodTypes
            );

            PaymentIntent paymentIntent
                    = PaymentIntent.create(params);
        } catch (StripeException ex) {
            System.out.println(ex.getMessage());
        }*/
    
      Stripe.apiKey = "sk_test_eursBjS98N2TlnR7XznT7vQl008CI5m0Z7";

            Map<String, Object> card = new HashMap<>();
            card.put("number", number);
            card.put("exp_month", exp_month);
            card.put("exp_year", exp_year);
            card.put("cvc", cvc);
            Map<String, Object> params = new HashMap<>();
            params.put("card", card);

            Token token;
        try {
            token = Token.create(params);
        

            final Map<String,Object> chargeParams = new HashMap<String, Object>();
            chargeParams.put("amount", mount);
            chargeParams.put("currency", "usd");
            //chargeParams.put("source","tok_16YxZe2eZvKYlo2C3nzXof9N" ); // obtained with Stripe.js
            chargeParams.put("source", token.getId());
            chargeParams.put("description", "Charge for test@example.com");

            Date date = new Date();
long millis = date.getTime();
 
        final RequestOptions options = RequestOptions
                .builder()
                .setIdempotencyKey("tGW5MOnasgR1ezw"+millis)
                .build();


        final Charge chargeresponse = Charge.create(chargeParams,options);

    } catch ( Exception e) {
        // TODO Auto-generated catch block
            System.out.println(e.getMessage()+"ssssssss");
    }
    }
     
}
