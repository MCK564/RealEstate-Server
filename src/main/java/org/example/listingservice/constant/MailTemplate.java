package org.example.listingservice.constant;

public class MailTemplate {
    public static final String RegisterSuccessMail =
                """
                <!DOCTYPE html>
                <html lang="vi">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Chào mừng</title>
                    <style>
                        body { font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px; }
                        .container { max-width: 800px; margin: auto; background-color: white; padding: 20px; border-radius: 8px; box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1); }
                        h1 { text-align: center; color: #333; }
                        p { text-align: center; font-size: 18px; color: #666; }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <h1>Chào mừng tới website của chúng tôi</h1>
                        <p>Chúng tôi rất vui mừng được đón tiếp bạn!</p>
                        <a href="http://localhost:5173/">
                            Về trang chủ
                        </a>
                    </div>
                </body>
                </html>""";


}
