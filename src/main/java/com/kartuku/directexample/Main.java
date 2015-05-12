/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kartuku.directexample;

import com.kartuku.directclient.DirectAPI;
import com.kartuku.directclient.model.request.*;
import com.kartuku.directclient.config.SimpleConfig;
import com.kartuku.directclient.exception.IpgDirectAPIException;
import com.kartuku.directclient.model.Message;
import com.kartuku.directclient.model.response.AuthorizeResponse;
import com.kartuku.directclient.model.response.CaptureResponse;
import com.kartuku.directclient.model.response.OttResponse;
import com.kartuku.directclient.model.response.PurchaseResponse;
import com.kartuku.directclient.model.response.QueryResponse;
import com.kartuku.directclient.model.response.RefundResponse;
import com.kartuku.directclient.model.response.TokenListResponse;
import com.kartuku.directclient.model.response.TokenRemoveResponse;
import com.kartuku.directclient.model.response.TokenStoreResponse;
import com.kartuku.directclient.model.response.VoidAuthorizeResponse;
import com.kartuku.directclient.model.response.VoidPurchaseResponse;
import com.kartuku.directclient.model.response.VoidRefundResponse;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mfachri
 */
public class Main {

    public static void main(String[] args) throws IOException {
        // create config file for hold merchant token and merchant secret token
        // you can create any class to save and hold merchant token and merchant secret key based on your client based on Config interface
        SimpleConfig config = new SimpleConfig();
        config.setMerchantSecretToken("merchantSecretToken");
        config.setMerchantToken("merchantToken");

        if (args.length == 2) {
            config.setMerchantToken(args[1]);
            config.setMerchantSecretToken(args[0]);
        }

        DirectAPI directAPI = new DirectAPI();
        // set config
        directAPI.setConfig(config);
        // set time out connection in ms
        directAPI.setConnectionTimeout(5000);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input;
        boolean loop = true;
        do {
            showMenu();
            input = reader.readLine();
            if (input.equalsIgnoreCase("x")) {
                loop = false;
            } else {
                int selected = Integer.MIN_VALUE;
                try {
                    selected = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid menu selected.");
                }
                switch (selected) {
                    case 0:
                        purchase(directAPI, reader);
                        break;
                    case 1:
                        authorize(directAPI, reader);
                        break;
                    case 2:
                        capture(directAPI, reader);
                        break;
                    case 3:
                        refund(directAPI, reader);
                        break;
                    case 4:
                        voidPurchase(directAPI, reader);
                        break;
                    case 5:
                        voidAuthorize(directAPI, reader);
                        break;
                    case 6:
                        voidCapture(directAPI, reader);
                        break;
                    case 7:
                        voidRefund(directAPI, reader);
                        break;
                    case 8:
                        query(directAPI, reader);
                        break;
                    case 9:
                        tokenStore(directAPI, reader);
                        break;
                    case 10:
                        tokenRemove(directAPI, reader);
                        break;
                    case 11:
                        tokenList(directAPI, reader);
                        break;
                    case 12:
                        ottRequest(directAPI, reader);
                        break;
                    default:
                        System.out.println("Invalid menu selected.");
                        break;
                }
            }
        } while (loop);
    }

    private static void showMenu() {
        System.out.println();
        System.out.println("-----MENU-----");
        System.out.println("[0] Purchase");
        System.out.println("[1] Authorize");
        System.out.println("[2] Capture");
        System.out.println("[3] Refund");
        System.out.println("[4] Void Purchase");
        System.out.println("[5] Void Authorize");
        System.out.println("[6] Void Capture");
        System.out.println("[7] Void Refund");
        System.out.println("[8] Query");
        System.out.println();
        System.out.println("[9] Token Store");
        System.out.println("[10] Token Remove");
        System.out.println("[11] Token List");
        System.out.println();
        System.out.println("[12] OTT Request");
        System.out.println();
        System.out.println("[x] Quit");
        System.out.println();
    }

    private static void purchase(DirectAPI directAPI, BufferedReader reader) {
        PurchaseRequest req = new RequestBuilder(directAPI.getConfig()).buildPurchaseRequest(reader);
        try {
            System.out.println("Request purchase with parameter : ");
            showParameter(req);
            PurchaseResponse res = directAPI.purchase(req);
            System.out.println("Response : ");
            showParameter(res);
        } catch (IpgDirectAPIException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void authorize(DirectAPI directAPI, BufferedReader reader) {
        AuthorizeRequest req = new RequestBuilder(directAPI.getConfig()).buildAuthorizeRequest(reader);
        try {
            System.out.println("Request authorize with parameter : ");
            showParameter(req);
            AuthorizeResponse res = directAPI.authorize(req);
            System.out.println("Response : ");
            showParameter(res);
        } catch (IpgDirectAPIException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void capture(DirectAPI directAPI, BufferedReader reader) {
        CaptureRequest req = new RequestBuilder(directAPI.getConfig()).buildCaptureRequest(reader);
        try {
            System.out.println("Request capture with parameter : ");
            showParameter(req);
            CaptureResponse res = directAPI.capture(req);
            System.out.println("Response : ");
            showParameter(res);
        } catch (IpgDirectAPIException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void voidPurchase(DirectAPI directAPI, BufferedReader reader) {
        VoidPurchaseRequest req = new RequestBuilder(directAPI.getConfig()).buildVoidPurchaseRequest(reader);
        try {
            System.out.println("Request voidPurchase with parameter : ");
            showParameter(req);
            VoidPurchaseResponse res = directAPI.voidPurchase(req);
            System.out.println("Response : ");
            showParameter(res);
        } catch (IpgDirectAPIException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void refund(DirectAPI directAPI, BufferedReader reader) {
        RefundRequest req = new RequestBuilder(directAPI.getConfig()).buildRefundRequest(reader);
        try {
            System.out.println("Request refund with parameter : ");
            showParameter(req);
            RefundResponse res = directAPI.refund(req);
            System.out.println("Response : ");
            showParameter(res);
        } catch (IpgDirectAPIException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void voidAuthorize(DirectAPI directAPI, BufferedReader reader) {
        VoidAuthorizeRequest req = new RequestBuilder(directAPI.getConfig()).buildVoidAuthorizeRequest(reader);
        try {
            System.out.println("Request voidAuthorize with parameter : ");
            showParameter(req);
            VoidAuthorizeResponse res = directAPI.voidAuthorize(req);
            System.out.println("Response : ");
            showParameter(res);
        } catch (IpgDirectAPIException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void voidCapture(DirectAPI directAPI, BufferedReader reader) {
        VoidAuthorizeRequest req = new RequestBuilder(directAPI.getConfig()).buildVoidAuthorizeRequest(reader);
        try {
            System.out.println("Request voidCapture with parameter : ");
            showParameter(req);
            VoidAuthorizeResponse res = directAPI.voidAuthorize(req);
            System.out.println("Response : ");
            showParameter(res);
        } catch (IpgDirectAPIException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void voidRefund(DirectAPI directAPI, BufferedReader reader) {
        VoidRefundRequest req = new RequestBuilder(directAPI.getConfig()).buildVoidRefundRequest(reader);
        try {
            System.out.println("Request voidRefund with parameter : ");
            showParameter(req);
            VoidRefundResponse res = directAPI.voidRefund(req);
            System.out.println("Response : ");
            showParameter(res);
        } catch (IpgDirectAPIException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void tokenStore(DirectAPI directAPI, BufferedReader reader) {
        TokenStoreRequest req = new RequestBuilder(directAPI.getConfig()).buildTokenStoreRequest(reader);
        try {
            System.out.println("Request tokenStore with parameter : ");
            showParameter(req);
            TokenStoreResponse res = directAPI.tokenStore(req);
            System.out.println("Response : ");
            showParameter(res);
        } catch (IpgDirectAPIException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void query(DirectAPI directAPI, BufferedReader reader) {
        QueryRequest req = new RequestBuilder(directAPI.getConfig()).buildQueryRequest(reader);
        try {
            System.out.println("Request query with parameter : ");
            showParameter(req);
            QueryResponse res = directAPI.query(req);
            System.out.println("Response : ");
            showParameter(res);
        } catch (IpgDirectAPIException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void tokenRemove(DirectAPI directAPI, BufferedReader reader) {
        TokenRemoveRequest req = new RequestBuilder(directAPI.getConfig()).buildTokenRemoveRequest(reader);
        try {
            System.out.println("Request tokenRemove with parameter : ");
            showParameter(req);
            TokenRemoveResponse res = directAPI.tokenRemove(req);
            System.out.println("Response : ");
            showParameter(res);
        } catch (IpgDirectAPIException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void tokenList(DirectAPI directAPI, BufferedReader reader) {
        TokenListRequest req = new RequestBuilder(directAPI.getConfig()).buildTokenListRequest(reader);
        try {
            System.out.println("Request tokenList with parameter : ");
            showParameter(req);
            TokenListResponse res = directAPI.tokenList(req);
            System.out.println("Response : ");
            showParameter(res);
        } catch (IpgDirectAPIException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void ottRequest(DirectAPI directAPI, BufferedReader reader) {
        try {
            OttRequest req = new RequestBuilder(directAPI.getConfig()).buildOttRequest(reader, "store");
            System.out.println("Request ott with parameter : ");
            showParameter(req);
            OttResponse res = directAPI.ott(req);
            System.out.println("Response : ");
            showParameter(res);
            if (res.getUrl().trim().length() > 0) {
                System.out.println(String.format("3DS is active and need authentication.\n%s\n Open in browser? [yes]", res.getUrl()));
                String input = reader.readLine();
                if ((input.equalsIgnoreCase("yes") || input.trim().length() <= 0) && Desktop.isDesktopSupported()) {
                    try {
                        Desktop.getDesktop().browse(new URI(res.getUrl()));
                    } catch (URISyntaxException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } catch (IOException | IpgDirectAPIException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static <T extends Message> void showParameter(T message) {
        Method[] methods = message.getClass().getMethods();
        for (Method method : methods) {
            String name = method.getName();
            if (name.startsWith("get") && !name.equals("getClass")) {
                name = name.replace("get", "");
                String value = "";
                try {
                    value = method.invoke(message, new Object[]{}).toString();
                } catch (NullPointerException ex) {
                    //  Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                    //  Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println(String.format("%s : %s", name, value));
            }
        }
    }
}
