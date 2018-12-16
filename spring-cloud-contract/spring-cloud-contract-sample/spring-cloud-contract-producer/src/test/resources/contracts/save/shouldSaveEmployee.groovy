package contracts

org.springframework.cloud.contract.spec.Contract.make {

    request {
        method 'POST'
        url '/employee/save'
        body("""
    {
      "name":"ALLEN",
      "id":1
    }
    """)
        headers {
            header('Content-Type', 'application/json;charset=UTF-8')
        }
    }
    response {
        status 200
        body("""
  {
    "message": "Saved employee ALLEN",
  }
  """)
        headers {
            header('Content-Type': 'application/json;charset=UTF-8')
        }
    }
}