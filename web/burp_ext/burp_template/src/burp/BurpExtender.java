// Author: Hawk3

package burp;

import java.io.*;
import java.util.Iterator;
import java.util.Properties;

import org.json.*;

public class BurpExtender implements IBurpExtender, IHttpListener {
    private IExtensionHelpers helpers;
    private PrintWriter stdout;
    private PrintWriter stderr;

    private static final String PAYLOAD = "{\"test\": 1}";
    private static final String CRLF = "\r\n";
    private static final String HEADER = "HAO";


    //
    // implement IBurpExtender
    //

    @Override
    public void registerExtenderCallbacks(IBurpExtenderCallbacks callbacks) {
        // obtain an extension helpers object
        helpers = callbacks.getHelpers();

        // set our extension name
        callbacks.setExtensionName("Burp_template_plugin");
        // register ourselves as an HTTP listener
        callbacks.registerHttpListener(this);

        // obtain our output stream
        stdout = new PrintWriter(callbacks.getStdout(), true);
        stderr = new PrintWriter(callbacks.getStderr(), true);

        stdout.println("Loaded..\n@Copyright: Hao Ke\n");


    }

    //
    // implement IHttpListener
    //

    @Override
    public void processHttpMessage(int toolFlag, boolean messageIsRequest, IHttpRequestResponse messageInfo) {
        // This method is invoked when an HTTP request is about to be issued, and when an HTTP response has been received.
        // Read the creds from the property file
        Properties properties = new Properties();
        String host2Match = "";
        String cookie = "";
        String csrfToken = "";
        String endpoint = "";
        boolean endpointHttps = true;
        int endpointPort = 8181;

        try {
            properties.load(new FileInputStream("redirector-proxy.properties"));
            host2Match = properties.getProperty("host").trim();
            cookie = properties.getProperty("cookie").trim();
            csrfToken = properties.getProperty("csrf").trim();

        } catch (IOException e) {
            stderr.println("Could not open 'redirector-proxy.properties' file");
            stderr.println("Working Dir: " + System.getProperty("user.dir"));
            e.printStackTrace(stderr);
            return;
        }

        IHttpService httpService = messageInfo.getHttpService();
        String hostname = httpService.getHost();

        String[] request = new String(messageInfo.getRequest()).split("\\r?\\n");
        String req_method = getRequestMethod(request);
        if (messageIsRequest) {
            if (req_method.equals("POST")) {
                messageInfo.setHighlight("cyan");
                return;
            }else if(!req_method.equals("GET")){
                stdout.println("Interesting method.\n");
                messageInfo.setHighlight("magenta");
            }
        }




        if (!messageIsRequest) {
            String[] response = new String(messageInfo.getResponse()).split("\\r?\\n");
            String resp = getPayload(response);
            boolean RESP_IS_JSON = true;
            try {
                String json = resp;
                JSONArray jArr = new JSONArray(json);
                JSONObject obj = jArr.getJSONObject(0);
                if (obj.getString("actionStatus").equals("SUCCESS")) {
                    stdout.println("200 OK" + CRLF);
                }

                for (int i = 0; i < jArr.length(); i++) {
                    JSONObject innerObj = jArr.getJSONObject(i);
                    for (Iterator it = innerObj.keys(); it.hasNext(); ) {
                        String key = (String) it.next();
                        stdout.println(key + ":" + innerObj.get(key));
                    }
                }
            } catch (JSONException ex) {
                // stderr.println("It is not json");
                RESP_IS_JSON = false;
            }
            if (!RESP_IS_JSON) {
                // Handle non json response here
                // stdout.println(resp);
            }
        }
    }

    //@Override


    // Private class:
    private String getFormData(String[] request) {
        String payload = getPayload(request);
        String[] params = payload.split("\\&");
        String output = "";
        String keyVal[];
        String key = "";
        String val = "";

        for (int i = 0; i < params.length; i++) {
            keyVal = params[i].split("\\=");
            key = keyVal[0];
            if (keyVal.length == 1) {
                val = "";
            } else {
                val = keyVal[1];
            }
            output += String.format(HEADER, key, val) + ",";
        }

        return (output.length() > 0) ? output.substring(0, output.length() - 1) : "";
    }

    private static String getHeaders(String[] request) {
        String output = "";

        for (int i = 2; i < request.length; i++) {
            if (request[i].equals("")) {
                break;
            }
            if (!request[i].split("\\:", 2)[0].trim().equals("Content-Type")) {
                if (!request[i].split("\\:", 2)[0].trim().equals("Content-Length")) {
                    output += String.format(HEADER, request[i].split("\\:", 2)[0].trim(), request[i].split("\\:", 2)[1].trim()) + ",";
                }
            }
        }
        return output.substring(0, output.length() - 1);
    }

    private static boolean makeBool(String value, boolean defNull) {
        if (value != null) {
            defNull = !value.toLowerCase().trim().equals("false");
        }

        return defNull;
    }

    private static String getRequestMethod(String[] request) {
        return request[0].split("\\s+")[0];
    }

    private static String getHeaderValue(String aKey, String request[]) {
        for (int i = 0; i < request.length; i++) {
            if (request[i].split("\\:", 2)[0].toLowerCase().trim().equals(aKey.toLowerCase())) {
                return request[i].split("\\:", 2)[1].trim();
            }
        }
        return "";
    }

    private static String getQueryParams(String[] request) {
        try {
            return request[0].split("\\s+")[1].split("\\?")[1];
        } catch (Exception e) {
            return "";
        }
    }

    private static String getPayload(String[] request) {
        String output = "";
        boolean flag = false;
        for (int i = 0; i < request.length; i++) {
            if (flag) {
                output += request[i];
                if (i != request.length - 1) {
                    output += "\r\n";
                }
            }
            if (request[i].equals("")) {
                flag = true;
            }
        }
        return output;
    }
}