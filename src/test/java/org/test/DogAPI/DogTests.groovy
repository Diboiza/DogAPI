package org.test.DogAPI

import org.test.builders.Requests
import spock.lang.Shared
import spock.lang.Specification

class DogTests extends Specification{

    //Shared variables
    def breeds, message, sub_breed, breed_images

    //API URL
    @Shared
    def endpoint = "dog.ceo/api"

    //1. Verify that a successful message is returned when a user searches for random breeds
    //2. Verify that bulldog is on the list of all breeds
    def "Get Random Breeds"()
    {
        given: 'I want to search for random breeds'
        def url = "https://${endpoint}/breeds/list/all"

        when: 'I list all breeds'
        def response = Requests.aRequest()
            .get(url).prettyPeek()

        breeds = response.jsonPath().prettify()
        message = response.jsonPath().status

        then:'status a successful message is retrieved'
        assert response.statusCode() == 200
        assert message == 'success'
        and: 'Verify that bull dog is on the list of all breeds'
        breeds.contains('bulldog')
    }

    //3. Retrieve all sub-breeds for bulldogs and their respective images

    def "Retrieve all sub-breeds for bulldogs and their images"()
    {
        given: 'I want to retrieve a list of bulldog Sub-breeds'
        def url = "https://${endpoint}/breed/bulldog/${sub_breed}/images"

        when: 'I list all sub-breeds and their images'
        def response = Requests.aRequest()
            .get(url).prettyPeek()

        breed_images = response.jsonPath().prettify()
        message = response.jsonPath().status
        then: 'The list of sub-breed images is returned'
        assert response.statusCode() == 200
        assert message == 'success'
        assert breed_images.size() > 0

        where: 'sub-breed is:'
        sub_breed |_
        'boston'  |_
        'english' |_
        'french'  |_
    }
}
