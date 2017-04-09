package com.challenge;

/**
 * Created by ehilario on 4/7/2017.
 */
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DisplayControllerIT {

    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/get");
    }

        @Test
    public void checkSingle() throws Exception {
        ResponseEntity<String> response = template.getForEntity(base.toString()+"?start=1&end=4",
                String.class);
        assertThat(response.getBody(), equalTo("{\"3\":\"ME\"}"));
    }
    @Test
    public void checkNone() throws Exception {
        ResponseEntity<String> response = template.getForEntity(base.toString(),
                String.class);
        assertThat(response.getBody(), equalTo("{\"code\":500,\"description\":\"Required int parameter 'start' is not present\"}"));
    }

    @Test
    public void checkOutBoundLow() throws Exception {
        ResponseEntity<String> response = template.getForEntity(base.toString()+"?start=-1&end=4",
                String.class);
        assertThat(response.getBody(), equalTo("{\"code\":400,\"description\":\"Parameters out side of range\"}"));
    }

    @Test
    public void checkOutBoundHigh() throws Exception {
        ResponseEntity<String> response = template.getForEntity(base.toString()+"?start=1&end=201",
                String.class);
        assertThat(response.getBody(), equalTo("{\"code\":400,\"description\":\"Parameters out side of range\"}"));
    }

    @Test
    public void checkOutEndNeg() throws Exception {
        ResponseEntity<String> response = template.getForEntity(base.toString()+"?start=1&end=-1",
                String.class);
        assertThat(response.getBody(), equalTo("{\"code\":400,\"description\":\"Parameters out side of range\"}"));
    }

    @Test
    public void checkCount3() throws Exception {
        ResponseEntity<String> response = template.getForEntity(base.toString()+"?start=1&end=200",
                String.class);
        int a = 1;
        int b = 200;
        int k = 3;
        int count= 0;
        int cnt = solution(a,b,k);
        String st = response.getBody();
        String[] sb = st.split(",");
        for(String s: sb){
            if(s.contains("ME")){
                count++;
            }
        }
        assertThat(cnt , equalTo(count));
   }

    @Test
    public void checkCount7() throws Exception {
        ResponseEntity<String> response = template.getForEntity(base.toString()+"?start=1&end=200",
                String.class);
        int a = 1;
        int b = 200;
        int k = 7;
        int count= 0;
        int cnt = solution(a,b,k);
        String st = response.getBody();
        String[] sb = st.split(",");
        for(String s: sb){
            if(s.contains("MS3")){
                count++;
            }
        }
        assertThat(cnt , equalTo(count));
    }
    @Test
    public void checkCountCmb() throws Exception {
        ResponseEntity<String> response = template.getForEntity(base.toString()+"?start=1&end=200",
                String.class);
        int a = 1;
        int b = 200;
        int k = 3;
        int count= 0;
        int cnt = solution(a,b,k);
        String st = response.getBody();
        String[] sb = st.split(",");
        for(String s: sb){
            if(s.contains("MS3 and ME")){
                count++;
            }
        }
        assertThat(9 , equalTo(count));
    }



    private  int solution(int A, int B, int K) {
        int firstDivisble = A%K == 0 ? A : A + (K - A%K);
        int lastDivisible = B%K == 0 ? B : B - B%K; //B/K behaves this way by default.
        return (lastDivisible - firstDivisble)/K + 1;
    }
}