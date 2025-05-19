package api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/NaverSearchAPI.do")
public class NaverSerchSevlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String clientId = "6Gkeq9FOu8QqsqMWKQPY"; // 애플리케이션 클라이언트 아이디
		String clientSecret = "IGTNeCDPiT"; // 애플리케이션 클라이언트 시크릿
		
		int startNum = 0;
		String text = null;
		
		try
		{
			startNum = Integer.parseInt(req.getParameter("startNum"));
			String searchText = req.getParameter("keyword");
			text = URLEncoder.encode(searchText, "UTF-8");
			
			String apiURL = "https://openapi.naver.com/v1/search/blog?query=" + text
					+ "&display=10&start=" + startNum;	 // JSON 결과
		    // String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // XML 결과
			
			Map<String, String> requestHeaders = new HashMap<>();
			requestHeaders.put("X-Naver-Client-Id", clientId);
			requestHeaders.put("X-Naver-Client-Secret", clientSecret);
			String responseBody = get(apiURL, requestHeaders);
			
			System.out.println(responseBody);
			
			resp.setContentType("text/html; charset=utf-8");
			resp.getWriter().write(responseBody);
		} catch (UnsupportedEncodingException e)
		{
			throw new RuntimeException("검색어 인코딩 실패", e);
		}
	}

	private String get(String apiURL, Map<String, String> requestHeaders)
	{
		HttpURLConnection con = connect(apiURL);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }


            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 오류 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
	}

	private String readBody(InputStream body)
	{
		InputStreamReader streamReader = new InputStreamReader(body);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }
            
            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는 데 실패했습니다.", e);
        }
	}

	private HttpURLConnection connect(String apiURL)
	{
		try {
            URL url = new URL(apiURL);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiURL, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiURL, e);
        }
	}
}
