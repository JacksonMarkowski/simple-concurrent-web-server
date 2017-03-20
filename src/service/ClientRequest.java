package service;

import http.HttpMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ClientRequest {

    private HttpMethod method;
    private String uri;
    private String version;

    public ClientRequest() {}

    /**
     * Reads a stream to create the request.
     *
     * @param input Stream of data coming from the client socket.
     */
    public void read(InputStream input) {
        parseInputStream(input);
    }

    /**
     * Parses the input from a service.  The input should have an initial
     * service line, and can have optional header lines and content body
     *
     * @param input The stream of data from the service
     */
    private void parseInputStream(InputStream input) {
        try {
            BufferedReader buffer = new BufferedReader(new InputStreamReader(input));
            parseInitRequest(buffer.readLine());

            String line;
            while (!(line = buffer.readLine()).equals("")) {
                //System.out.println(line);
                //ToDo: handle the rest of the request lines
            }
        } catch (IOException e) {

        }
    }

    /**
     * Parses the initial line for the service.  As per HTTP standards,
     * the initial line should include a method name, local path of requested
     * item, and HTTP version.
     *
     * @param line The initial(first) line of the HTTP service
     */
    private void parseInitRequest(String line) {
        //ToDo: test if string is null
        String[] lineSplit = line.split("\\s+");
        //ToDo: check if method,uri,version are all there
        try {
            method = HttpMethod.valueOf(lineSplit[0]);
        } catch (Exception e) {
            //ToDo: throw exception or something
        }
        uri = lineSplit[1];
        version = lineSplit[2];

    }

    private void parseHeaderRequest(String line) {
        //ToDo: handle headers
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getUri() {
        return uri;
    }

    public String getVersion() {
        return version;
    }
}