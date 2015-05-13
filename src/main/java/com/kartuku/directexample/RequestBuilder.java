/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kartuku.directexample;

import com.kartuku.directclient.config.Config;
import com.kartuku.directclient.model.Request;
import com.kartuku.directclient.model.request.AuthorizeRequest;
import com.kartuku.directclient.model.request.CaptureRequest;
import com.kartuku.directclient.model.request.OttRequest;
import com.kartuku.directclient.model.request.PurchaseRequest;
import com.kartuku.directclient.model.request.QueryRequest;
import com.kartuku.directclient.model.request.RefundRequest;
import com.kartuku.directclient.model.request.TokenListRequest;
import com.kartuku.directclient.model.request.TokenRemoveRequest;
import com.kartuku.directclient.model.request.TokenStoreRequest;
import com.kartuku.directclient.model.request.VoidAuthorizeRequest;
import com.kartuku.directclient.model.request.VoidCaptureRequest;
import com.kartuku.directclient.model.request.VoidPurchaseRequest;
import com.kartuku.directclient.model.request.VoidRefundRequest;
import com.kartuku.directclient.util.CommonUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mfachri
 */
public class RequestBuilder {

    private final String defaultCard = "4811111111111114";
    private final String defaultCVV = "123";
    private final String defaultExp = "2001";
    private final String defaultMerchantUserCode = "java-client-user";
    private final String defaultTxAmount = "500000";
    private final String defaultCurrency = "IDR";
    private final String defaultStoreCode = "200";
    private final String defaultCustom1 = "custom1";
    private final String defaultCustom2 = "custom2";
    private final String defaultCustom3 = "custom3";

    private final Config config;

    public RequestBuilder(Config config) {
        this.config = config;
    }

    public OttRequest buildOttRequest(BufferedReader reader, String type) throws IOException {
        OttRequest req = new OttRequest();
        req.setCardCVV(defaultCVV);

        req.setMerchantToken(config.getMerchantToken());
        req.setMerchantUserCode(defaultMerchantUserCode);
        req.setTxnAmount(defaultTxAmount);
        req.setCardExpiry(defaultExp);
        req.setCardNumber(defaultCard);
        req.setType(type);
        Date txDate = new Date();
        req.setTxnReference(Long.toString(txDate.getTime()));

        showInputFormRequest(req, reader);

        return req;
    }

    public PurchaseRequest buildPurchaseRequest(BufferedReader reader) {
        PurchaseRequest req = new PurchaseRequest();

        req.setCardCVV(defaultCVV);
        req.setCardTokenize(false);
        req.setMerchantToken(config.getMerchantToken());
        req.setMerchantUserCode(defaultMerchantUserCode);
        req.setTxnAmount(defaultTxAmount);
        req.setTxnCurrency(defaultCurrency);
        req.setTxnCustom1(defaultCustom1);
        req.setTxnCustom2(defaultCustom2);
        req.setTxnCustom3(defaultCustom3);
        Date txDate = new Date();
        req.setTxnReference(Long.toString(txDate.getTime()));
        req.setTxnStoreCode(defaultStoreCode);
        req.setTxnTradingDate(CommonUtil.convertDate(txDate));

        showInputFormRequest(req, reader);

        return req;
    }

    public AuthorizeRequest buildAuthorizeRequest(BufferedReader reader) {
        AuthorizeRequest req = new AuthorizeRequest();

        req.setCardCVV(defaultCVV);
        req.setCardTokenize(false);
        req.setMerchantToken(config.getMerchantToken());
        req.setMerchantUserCode(defaultMerchantUserCode);
        req.setTxnAmount(defaultTxAmount);
        req.setTxnCurrency(defaultCurrency);
        req.setTxnCustom1(defaultCustom1);
        req.setTxnCustom2(defaultCustom2);
        req.setTxnCustom3(defaultCustom3);
        Date txDate = new Date();
        req.setTxnReference(Long.toString(txDate.getTime()));
        req.setTxnStoreCode(defaultStoreCode);
        req.setTxnTradingDate(CommonUtil.convertDate(txDate));

        showInputFormRequest(req, reader);

        return req;
    }

    public CaptureRequest buildCaptureRequest(BufferedReader reader) {
        CaptureRequest req = new CaptureRequest();

        req.setMerchantToken(config.getMerchantToken());
        req.setTxnAmount(defaultTxAmount);
        req.setTxnCurrency(defaultCurrency);
        Date txDate = new Date();
        req.setTxnReference(Long.toString(txDate.getTime()));

        showInputFormRequest(req, reader);

        return req;
    }

    public RefundRequest buildRefundRequest(BufferedReader reader) {
        RefundRequest req = new RefundRequest();

        req.setMerchantToken(config.getMerchantToken());
        req.setTxnAmount(defaultTxAmount);
        req.setTxnCurrency(defaultCurrency);

        Date txDate = new Date();
        req.setTxnReference(Long.toString(txDate.getTime()));

        showInputFormRequest(req, reader);

        return req;
    }

    public VoidPurchaseRequest buildVoidPurchaseRequest(BufferedReader reader) {
        VoidPurchaseRequest req = new VoidPurchaseRequest();

        req.setMerchantToken(config.getMerchantToken());
        Date txDate = new Date();
        req.setTxnReference(Long.toString(txDate.getTime()));

        showInputFormRequest(req, reader);

        return req;
    }

    public VoidAuthorizeRequest buildVoidAuthorizeRequest(BufferedReader reader) {
        VoidAuthorizeRequest req = new VoidAuthorizeRequest();

        req.setMerchantToken(config.getMerchantToken());
        Date txDate = new Date();
        req.setTxnReference(Long.toString(txDate.getTime()));

        showInputFormRequest(req, reader);

        return req;
    }

    public VoidCaptureRequest buildVoidCaptureRequest(BufferedReader reader) {
        VoidCaptureRequest req = new VoidCaptureRequest();

        req.setMerchantToken(config.getMerchantToken());
        Date txDate = new Date();
        req.setTxnReference(Long.toString(txDate.getTime()));

        showInputFormRequest(req, reader);

        return req;
    }

    public VoidRefundRequest buildVoidRefundRequest(BufferedReader reader) {
        VoidRefundRequest req = new VoidRefundRequest();

        req.setMerchantToken(config.getMerchantToken());
        Date txDate = new Date();
        req.setTxnReference(Long.toString(txDate.getTime()));

        showInputFormRequest(req, reader);

        return req;
    }

    public QueryRequest buildQueryRequest(BufferedReader reader) {
        QueryRequest req = new QueryRequest();

        req.setMerchantToken(config.getMerchantToken());

        showInputFormRequest(req, reader);

        return req;
    }

    public TokenStoreRequest buildTokenStoreRequest(BufferedReader reader) {
        TokenStoreRequest req = new TokenStoreRequest();

        req.setMerchantToken(config.getMerchantToken());
        req.setMerchantUserCode(defaultMerchantUserCode);

        showInputFormRequest(req, reader);

        return req;
    }

    public TokenRemoveRequest buildTokenRemoveRequest(BufferedReader reader) {
        TokenRemoveRequest req = new TokenRemoveRequest();

        req.setMerchantToken(config.getMerchantToken());
        req.setMerchantUserCode(defaultMerchantUserCode);

        showInputFormRequest(req, reader);

        return req;
    }

    public TokenListRequest buildTokenListRequest(BufferedReader reader) {
        TokenListRequest req = new TokenListRequest();

        req.setMerchantToken(config.getMerchantToken());
        req.setMerchantUserCode(defaultMerchantUserCode);

        showInputFormRequest(req, reader);

        return req;
    }

    private <T extends Request> void showInputFormRequest(T req, BufferedReader reader) {
        // it's not good to use reflection for a form input, it's just an example
        Method[] methods = req.getClass().getMethods();
        for (Method method : methods) {
            String name = method.getName();
            if (name.startsWith("get") && !name.equals("getClass")) {
                try {
                    name = name.replaceFirst("get", "");
                    String defaultInput = "";
                    try {
                        defaultInput = method.invoke(req, new Object[]{}).toString();
                    } catch (NullPointerException e) {
                    }
                    System.out.print(String.format("%s [%s] : ", name, defaultInput));
                    String input = reader.readLine();
                    Method setMethod = req.getClass().getMethod("set" + name, String.class);
                    setMethod.invoke(req, new Object[]{useDefault(input, defaultInput)});
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | IOException | NoSuchMethodException | SecurityException ex) {
                    Logger.getLogger(RequestBuilder.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (name.startsWith("is")) {
                try {
                    name = name.replaceFirst("is", "");
                    boolean defaultInput = false;
                    try {
                        defaultInput = Boolean.parseBoolean(method.invoke(req, new Object[]{}).toString());
                    } catch (NullPointerException e) {}
                    System.out.print(String.format("%s [%s] : ", name, defaultInput));
                    String input = reader.readLine();
                    Method setMethod = req.getClass().getMethod("set" + name, Boolean.class);
                    setMethod.invoke(req, new Object[]{Boolean.parseBoolean(input.toLowerCase())});
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | IOException | NoSuchMethodException | SecurityException ex) {
                    Logger.getLogger(RequestBuilder.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private String useDefault(String input, String defaultInput) {
        return input.trim().length() > 0 ? input : defaultInput;
    }

}
