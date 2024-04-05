### [Drone Http Requests](src/main/resources/drone-requests.http)

Register a Drone

```shell
curl -X POST --location "http://127.0.0.1:8080/drone/v1" \
    -H "Content-Type: application/json" \
    -d '{
          "serialNumber": "drone123",
          "model": "LIGHTWEIGHT",
          "weightLimit": 450,
          "batteryPercentage": 80,
          "state": "IDLE"
        }'
```

Sample Response

```text
HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Fri, 05 Apr 2024 20:37:20 GMT
Keep-Alive: timeout=60
Connection: keep-alive

{
  "id": 1,
  "serialNumber": "drone123",
  "model": "LIGHTWEIGHT",
  "weightLimit": 450,
  "batteryPercentage": 80,
  "state": "IDLE"
}
```

Get available drones for loading

```shell
curl -X GET --location "http://localhost:8080/drone/v1/available"
```

Sample Response, notice all of them are of _**IDLE**_ state

```text
[
  {
    "id": 1,
    "serialNumber": "drone123",
    "model": "LIGHTWEIGHT",
    "weightLimit": 450,
    "batteryPercentage": 80,
    "state": "IDLE"
  },
  {
    "id": 7,
    "serialNumber": "drone192021",
    "model": "CRUISERWEIGHT",
    "weightLimit": 300,
    "batteryPercentage": 95,
    "state": "IDLE"
  }
]
```

Load Drone with some Medication providing their IDs in the Request Body

```shell
curl -X POST --location "http://127.0.0.1:8080/drone/v1/1" \
    -H "Content-Type: application/json" \
    -d '[1, 2, 3]'
```

Response returns details for the carry operations

```text
HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Fri, 05 Apr 2024 20:44:23 GMT
Keep-Alive: timeout=60
Connection: keep-alive

[
  {
    "id": 1,
    "startDate": "2024-04-05T20:44:23.676+00:00",
    "pickupLocation": null,
    "destinationLocation": null,
    "endDate": null,
    "drone": {
      "id": 1,
      "serialNumber": "drone123",
      "model": "LIGHTWEIGHT",
      "weightLimit": 450,
      "batteryPercentage": 80,
      "state": "IDLE"
    },
    "medication": {
      "id": 1,
      "name": "Paracetamol",
      "weight": 100,
      "code": "ABC123",
      "imageURL": "https://example.com/image1.jpg"
    },
    "completed": false
  },
  {
    "id": 2,
    "startDate": "2024-04-05T20:44:23.701+00:00",
    "pickupLocation": null,
    "destinationLocation": null,
    "endDate": null,
    "drone": {
      "id": 1,
      "serialNumber": "drone123",
      "model": "LIGHTWEIGHT",
      "weightLimit": 450,
      "batteryPercentage": 80,
      "state": "IDLE"
    },
    "medication": {
      "id": 2,
      "name": "Aspirin",
      "weight": 200,
      "code": "DEF456",
      "imageURL": "https://example.com/image2.jpg"
    },
    "completed": false
  },
  {
    "id": 3,
    "startDate": "2024-04-05T20:44:23.704+00:00",
    "pickupLocation": null,
    "destinationLocation": null,
    "endDate": null,
    "drone": {
      "id": 1,
      "serialNumber": "drone123",
      "model": "LIGHTWEIGHT",
      "weightLimit": 450,
      "batteryPercentage": 80,
      "state": "IDLE"
    },
    "medication": {
      "id": 3,
      "name": "Ibuprofen",
      "weight": 150,
      "code": "GHI789",
      "imageURL": "https://example.com/image3.jpg"
    },
    "completed": false
  }
]
```

Get drones loaded medications

```shell
curl -X GET --location "http://127.0.0.1:8080/drone/v1/1/medications"
```

Returns the medications

```text
HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Fri, 05 Apr 2024 20:46:28 GMT
Keep-Alive: timeout=60
Connection: keep-alive

[
  {
    "id": 1,
    "name": "Paracetamol",
    "weight": 100,
    "code": "ABC123",
    "imageURL": "https://example.com/image1.jpg"
  },
  {
    "id": 2,
    "name": "Aspirin",
    "weight": 200,
    "code": "DEF456",
    "imageURL": "https://example.com/image2.jpg"
  },
  {
    "id": 3,
    "name": "Ibuprofen",
    "weight": 150,
    "code": "GHI789",
    "imageURL": "https://example.com/image3.jpg"
  }
]
```

### [Medication Http Requests](src/main/resources/medication-requests.http)

Add medication to Database

```shell
curl -X POST --location "http://127.0.0.1:8080/medication/v1" \
    -H "Content-Type: application/json" \
    -d '{
          "name": "Paracetamol",
          "weight": 100,
          "code": "ABC123",
          "imageURL": "https://example.com/image1.jpg"
        }'
```

```text
HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Fri, 05 Apr 2024 20:49:11 GMT
Keep-Alive: timeout=60
Connection: keep-alive

{
  "id": 1,
  "name": "Paracetamol",
  "weight": 100,
  "code": "ABC123",
  "imageURL": "https://example.com/image1.jpg"
}
```
```shell
curl -X GET --location "http://127.0.0.1:8080/medication/v1"
```
Returns entire medications
```text
HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Fri, 05 Apr 2024 20:50:04 GMT
Keep-Alive: timeout=60
Connection: keep-alive

[
  {
    "id": 1,
    "name": "Paracetamol",
    "weight": 100,
    "code": "ABC123",
    "imageURL": "https://example.com/image1.jpg"
  },
  {
    "id": 2,
    "name": "Aspirin",
    "weight": 200,
    "code": "DEF456",
    "imageURL": "https://example.com/image2.jpg"
  },
  {
    "id": 3,
    "name": "Ibuprofen",
    "weight": 150,
    "code": "GHI789",
    "imageURL": "https://example.com/image3.jpg"
  },
  {
    "id": 4,
    "name": "Amoxicillin",
    "weight": 300,
    "code": "JKL012",
    "imageURL": "https://example.com/image4.jpg"
  },
  {
    "id": 5,
    "name": "Lisinopril",
    "weight": 250,
    "code": "MNO345",
    "imageURL": "https://example.com/image5.jpg"
  },
  {
    "id": 6,
    "name": "Atorvastatin",
    "weight": 350,
    "code": "PQR678",
    "imageURL": "https://example.com/image6.jpg"
  },
  {
    "id": 7,
    "name": "Metformin",
    "weight": 400,
    "code": "STU901",
    "imageURL": "https://example.com/image7.jpg"
  },
  {
    "id": 8,
    "name": "Levothyroxine",
    "weight": 200,
    "code": "VWX234",
    "imageURL": "https://example.com/image8.jpg"
  },
  {
    "id": 9,
    "name": "Simvastatin",
    "weight": 250,
    "code": "YZA567",
    "imageURL": "https://example.com/image9.jpg"
  },
  {
    "id": 10,
    "name": "Losartan",
    "weight": 300,
    "code": "BCD890",
    "imageURL": "https://example.com/image10.jpg"
  }
]
```