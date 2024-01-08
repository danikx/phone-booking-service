
create service that should allow a phone to be booked / returned

 Should have this api points:
 - [GET] get list of available phones
 - [GET] get list of all phones
 - [POST] book the phone with id
 - [UPDATE] return the phone with id ???

 db schema

phone: 
   - phone_id
   - phone_name
   - brand
   - availability
   - count
   - date of booked
   - who booked the phone

phone_issue
   - phone_id


Response:
- list of all phones {api_host}/api/v1/phone
{
  phones:[
    {
      "name": "",
      "brand": "",
      "date_of_booking": "",
      "booked_by": ""
    },
    {},
    {}
  ]
} 

- list of all available phones
{
  
}

- book the phone with id: {api_host}/api/v1/phone/{phone_id}/book
{
  "days_count": 14,
  "booked_by": "who booked the phone"
}
response:
  - 200: OK
  - 401 : phone id is invalid
  - 402 : phone is already booked
  - 403 :
  - 500 : server error

- return the phone with id: {api_host}/api/v1/phone/{phone_id}/return
{
  "message" : "successfully returned"
}
response:
  - 200 : OK
  - 401 : phone id is invalid
  - 500 : server error 






