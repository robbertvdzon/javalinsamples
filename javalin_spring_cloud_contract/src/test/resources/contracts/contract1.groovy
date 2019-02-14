import org.springframework.cloud.contract.spec.Contract
Contract.make {
    description "should return something nice"
    request{
        method GET()
        url("/")
    }
    response {
        body("Hello World from javalin")
        status 200
    }
}