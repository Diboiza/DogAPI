package org.test.PetStoreAPI


import org.test.builders.Requests
import spock.lang.Shared
import spock.lang.Specification

import static com.jayway.jsonpath.matchers.JsonPathMatchers.hasJsonPath
import static org.hamcrest.MatcherAssert.assertThat

class PetTests extends Specification {

    //Shared Variables
    @Shared
    def petList, petID

    @Shared
    def endpoint = "petstore.swagger.io/v2/pet"

    /**
     * 1. Retrieve all available pets and confirm that the name "doggie" with category id"12" is on the list
     *Note: The api seems to delete pets, so test will fail when there is not pet with above mentioned criteria in available list
     */


    def "Get all available pets"() {
        given: "I want to Get all available pets"
        def url = "https://${endpoint}/findByStatus?status=available"

        when: "I call the getAvailable pets API"
        def response = Requests.aRequest()
                .header("accept", "application/json")
                .get(url).prettyPeek()

        petList = response.jsonPath().get()

        then: "I should get a list of all available pets and confirm doggie with category-id12 is on the list"
        assert response.statusCode() == 200
        assertThat(petList, hasJsonPath('$.*.category[?(@.id == 12)]'))
    }


    /**
     * 2. Add a new pet with an auto generated name and status available
     * Confirm the new pet has been added
     * we'll use Java Faker to create an auto-generated name
     * the payload for this test is located in file NewPet
     * */

    def "Add a new pet with auto-generated name"()
    {
        given: "I wanted to add a new pet with an auto-genrated name"
        def url = "https://${endpoint}"

        when: "I add a new pet to the store API"
        def response = Requests.aRequest()
        .header('accept', 'application/json')
        .body(NewPet.payLoads())
        .post(url)

        def addedPet = response.jsonPath().get()
        petID = response.jsonPath().id
        then: "A new pet must be added and"
        assert response.statusCode() == 200
        print(petID)
    }

    /**
     * Retrieve th created pet using the pet ID from previous API Call
     */

    def "Retrieve created Pet using ID"()
    {
        given: "I want to retrieve a created pet using the ID"
        def url = "https://${endpoint}/${petID}"

        when: "I call the GetPetByID API"
        def response = Requests.aRequest()
        .header('accept', 'application/json')
        .get(url)

        def petDetails = response.jsonPath().get()

        then: "The created pet details must be returned"
        assert response.statusCode() == 200
        print(petDetails)
    }


}