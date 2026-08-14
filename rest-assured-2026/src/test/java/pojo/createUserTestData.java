package pojo;

public class createUserTestData {

    public String testcase;
    public Request request;
    public Expected expected;

    public static class Request {
        public String name;
        public String email;
        public String gender;
        public String status;
    }


    public static class Expected{
        public int statusCode;
        public String message;
        public String error;
    }

}
