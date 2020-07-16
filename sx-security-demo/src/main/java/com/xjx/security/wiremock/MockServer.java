package com.xjx.security.wiremock;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * @Author XJX
 * @Date 2020/7/9 11:25
 * @Version 1.0
 */
public class MockServer {

    public static void main(String[] args) throws IOException {
        WireMock.configureFor(8099);
        WireMock.removeAllMappings();

        mock("/order/1","01");
        mock("/order/2","02");
    }

    private static void mock(String url, String fileName) throws IOException {
        ClassPathResource resource = new ClassPathResource("mock/response/"+fileName+".txt");

        String content = FileUtils.readFileToString(resource.getFile());
        WireMock.stubFor(WireMock.get(WireMock.urlPathEqualTo(url))
                .willReturn(WireMock.aResponse().withBody(content).withStatus(200)));
    }
}
