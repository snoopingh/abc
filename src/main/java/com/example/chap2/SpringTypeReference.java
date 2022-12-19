package com.example.chap2;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class SpringTypeReference {
    public static void main(String[] args) {
        // spring version 3.2 >=
        // 익명클래스의 인스턴스를 만들어서, 익명클래스가 상속하고있는 super 클래스의 제네릭 타입 파라미터 정보를 전달하기 위한 용도.
        ParameterizedTypeReference<?> typeRef = new ParameterizedTypeReference<List<Map<Set<Integer>, String>>>() {};
        System.out.println(typeRef.getType());


        RestTemplate rt = new RestTemplate();
        List<Chap2Application.User> users = rt.exchange("http://localhost:8080",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Chap2Application.User>>() {}).getBody();
        assert users != null;
        users.forEach(System.out::println);
        // 1:44:43
    }
}
