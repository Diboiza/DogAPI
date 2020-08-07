import org.test.DogAPI.DogTests
import org.test.PetStoreAPI.PetTests

spockReports {
    set 'com.athaydes.spockframework.report.outputDir': 'target/spock-reports'
}

runner {
    def targetedCase = System.properties['testCase']
    switch (targetedCase) {
        case 'Test':
            include DogTests,
                    PetTests
            break
    }
}