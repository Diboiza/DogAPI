package org.test.PetStoreAPI

import com.github.javafaker.Faker
import groovy.json.JsonBuilder
import spock.lang.Specification

class NewPet extends Specification
{
    //instance of java faker with South African data

    static Faker faker = new Faker(new Locale("en-ZA"))

    static payLoads(){
        """
            {
                "id": ${faker.number().randomNumber()},
                "category": {
                    "id":  ${faker.number().randomNumber()},
                    "name": "${faker.dog().name()}"
                },
                "name": "${faker.dog().name()}",
                "photoUrls": [
                    "string"
                ],
                "tags": [
                    {
                        "id": 0,
                        "name": "string"
                    }
                ],
                "status": "available"
        }
        """
    }

//    static def payLoad = [ "id": faker.number().randomNumber(),
//                           "category": [
//                              "id": payLoad.id,
//                              "name": "${faker.dog().name()}"
//                          ],
//                          "name": "${payLoad.category.name}",
//                          "photoUrls": [
//                                  "string"
//                          ],
//                          "tags": [
//                                  [
//                                      "id": payLoad.id,
//                                      "name": "${payLoad.name}"
//                                  ]
//                          ],
//                          "status": "available"
//                        ]
//
//
//    static def json = new JsonBuilder(payLoad).toPrettyString()
//
//    static void main(String[] args)
//    {
//        print(json)
//    }
}
