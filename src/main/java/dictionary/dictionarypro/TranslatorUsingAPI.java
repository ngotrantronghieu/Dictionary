package dictionary.dictionarypro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * This is a public TranslatorUsingAPI class represents the API translator feature of the
 * dictionary UI.
 */

public class TranslatorUsingAPI {

    /**
     * This is a public static String translate method to translate a word or a sentence using API.
     * @param text is the String we want to translate
     * @return the String explanation
     * @throws IOException is an Exception being thrown if we can't read the output of the
     * translation
     */

    public static String translate(String text) throws IOException {

        // Create a String URL.

        // Tạo liên kết URL kiểu String.

        String urlStr = "https://script.google.com/macros/s/AKfycbxsjptjQYjsO7MZ3bOtV7v"
                + "IZoCIesUCqs6KUZgINNU6Ms2djo0C6VfA7PngeA101bje/exec"
                + "?q=" + URLEncoder.encode(text, "UTF-8") + "&target=vi&source=en";

        // Send Java HTTP request using HttpURLConnection class.

        // Gửi yêu cầu Java HTTP sử dụng lớp HttpURLConnection.

        URL url = new URL(urlStr);

        // Open a connection to the URL, allowing a client to communicate with the source.

        // Mở một kết nối tới địa chỉ URL, cho phép 1 người dùng có thể giao tiếp với nguồn.

        HttpURLConnection obj = (HttpURLConnection) url.openConnection();

        /**
         * Set the request property.
         * Mozilla/5.0 is the User Agent of Mozilla.
         * User Agent is a browser identification string.
         */

        /**
         * Thiết lập thuộc tính yêu cầu.
         * Mozilla/5.0 là User Agent của Mozilla.
         * User Agent là chuỗi nhận dạng trình duyệt.
         */

        obj.setRequestProperty("User-Agent", "Mozilla/5.0");

        /**
         * Create an InputStreamReader to read the Input Stream of the obj.
         * The method obj.getInputStream() returns the reader input from an open connection.
         */

        /**
         * Tạo một biến InputStreamReader để đọc luồng obj.getInputStream().
         * obj.getInputStream() trả về luồng đầu vào đọc từ kết nối mở.
         */

        InputStreamReader str = new InputStreamReader(obj.getInputStream());
        BufferedReader input = new BufferedReader(str);

        /**
         * Create a StringBuilder response variable to store the translated word.
         * StringBuilder is used to create a changeable String.
         */

        /**
         * Tạo biến response kiểu StringBuilder để lưu từ đã dịch.
         * StringBuilder được sử dụng để tạo chuỗi có thể thay đổi.
         */

        StringBuilder response = new StringBuilder();

        // String response = "";

        String inputLine;

        // Using readLine method to read a line one by one.

        // Sử dụng readLine() để đọc từng dòng.

        while ((inputLine = input.readLine()) != null) {
            response.append(inputLine);
            // response += inputLine;
        }
        input.close();
        return response.toString();
    }
}
