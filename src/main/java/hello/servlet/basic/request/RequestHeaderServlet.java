package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

import static java.lang.System.*;

@WebServlet(name = "requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        printStartLine(request);
        printHeaders(request);
        printHeaderUtils(request);
        printEtc(request);
    }

    private void printStartLine(HttpServletRequest request) {
        out.println("--- REQUEST-LINE - start ---");

        out.println("request.getMethod() = " + request.getMethod());
        out.println("reqeust.getProtocal() = " + request.getProtocol());
        out.println("request.getScheme() = " + request.getScheme());

        out.println("request.getRequestURL() = " + request.getRequestURL());
        out.println("request.getRequestURI() = " + request.getRequestURI());
        out.println("request.getQueryString() = " + request.getQueryString());
        out.println("request.isSecure() = " + request.isSecure());
        out.println("--- REQUEST_LINE - end ---");
        out.println();
    }

    //Header 모든 정보
    private void printHeaders(HttpServletRequest request) {
        out.println("--- Headers - start ---");

//        Enumeration<String> headerNames = request.getHeaderNames();
//        while (headerNames.hasMoreElements()) {
//            String headerName = headerNames.nextElement();
//            System.out.println(headerName + ": " + request.getHeader(headerName));
//        }

        request.getHeaderNames().asIterator()
                        .forEachRemaining(headerName -> out.println(headerName + ": " + headerName));

        out.println("--- Headers - end ---");
        out.println();
    }

    //Header 편리한 조회
    private void printHeaderUtils(HttpServletRequest request) {
        System.out.println("--- Header 편의 조회 start ---");
        System.out.println("[Host 편의 조회]");
        System.out.println("request.getServerName() = " + request.getServerName()); //Host 헤더
        System.out.println("request.getServerPort() = " + request.getServerPort()); //Host 헤더
        System.out.println();

        System.out.println("[Accept-Language 편의 조회]");
        request.getLocales().asIterator()
                .forEachRemaining(locale -> System.out.println("locale = " + locale));
        System.out.println("request.getLocale() = " + request.getLocale());
        System.out.println();

        System.out.println("[cookie 편의 조회]");

        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                System.out.println(cookie.getName() + ": " + cookie.getValue());
            }
        }
        System.out.println();

        System.out.println("[Content 편의 조회]");
        System.out.println("request.getContentType() = " + request.getContentType());
        System.out.println("request.getContentLength() = " + request.getContentLength());
        System.out.println("request.getCharacterEncoding() = " + request.getCharacterEncoding());

        System.out.println("--- Header 편의 조회 end ---");
        System.out.println();
    }

    private void printEtc(HttpServletRequest request) {
        out.println("--- 기타 조회 start ---");

        out.println("[Remote 정보]");
        out.println("request.getRemoteHost() = " + request.getRemoteHost());
        out.println("request.getRemoteAddr() = " + request.getRemoteAddr());
        out.println("request.getRemotePort() = " + request.getRemotePort());
        out.println();

        out.println("[Local 정보]");
        out.println("request.getLocalName() = " + request.getLocalName());
        out.println("request.getLocalAddr() = " + request.getLocalAddr());
        out.println("request.getLocalPort() = " + request.getLocalPort());

        out.println("--- 기타 조회 end ---");
        out.println();
    }
}
