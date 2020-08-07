package org.test.builders

import com.github.dzieciou.testing.curl.CurlLoggingRestAssuredConfigFactory
import com.github.dzieciou.testing.curl.Options
import io.restassured.config.RestAssuredConfig
import io.restassured.specification.RequestSpecification

import static io.restassured.RestAssured.given


class Requests
{
    /**
     *Build a curl logger that we can send to developers to easily see the API details
     * Setup a request specification that will taking thw curl builder config so we dont have to build one on every API call
     */


    private static Options options = Options.builder().printMultiliner().useLongForm().build()
    private static RestAssuredConfig curlLoggingConfig = CurlLoggingRestAssuredConfigFactory.createConfig(options)

    static RequestSpecification aRequest(@DelegatesTo(RequestSpecification)Closure closure){
        def requestSpecification = given()
            .config(curlLoggingConfig)
            .contentType("application/json")
            .urlEncodingEnabled(false)

        closure.delegate = requestSpecification
        closure.run()
        requestSpecification
    }

    static RequestSpecification aRequest(){
        aRequest({})
    }
}
