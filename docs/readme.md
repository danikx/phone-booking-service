INSTRUCTIONS: (Backend Code Test)
• You should aim to spend between 3 to 4 hours on the exercise
• Return the solution or make it available to us on completion
• You may use any either Kotlin or Java

  TASK:

• The mobile software testing team has 10 mobile phones that it needs to share for testing purposes.
  - Samsung Galaxy S9
  - 2x Samsung Galaxy S8
  - Motorola Nexus 6
  - Oneplus 9
  - Apple iPhone 13
  - Apple iPhone 12
  - Apple iPhone 11
  - iPhone X
  - Nokia 3310

• Please create an service that allows a phone to be booked / returned.

• The following information should also be available for each phone
  - Availability (Yes / No)
  - When it was booked
  - Who booked the phone

• Please use Fonoapi to expose the following information for each phone and create work-around if it isn’t working:
- Technology
- 2g bands
- 3g bands
- 4g bands

REFLECTION:
• What aspect of this exercise did you find most interesting?
• What did you find most cumbersome?

to overcome service unavailability we can use several strategies 

1. Retry mechanism. You can retry the request a certain number of times with a delay between each attempt. This gives the service some time to recover.
2. Circuit Breaker pattern. This pattern prevents a system from performing an operation that's likely to fail. When the failure rate is high, the circuit breaker is 'tripped', and further requests are blocked fro a predefined period. this helps prevent cascading failures.
3. Fallback mechanism. this allows your app to use a default or cached value if the service is unavailable. This is especially useful for non-critical functionalities where a temporary loss of data is acceptable.
4. Timeouts. set reasonable timeouts for your HTTP request to prevent them from blocking indefinitely. If the service is not responding within a specified time frame, consider it as a failure and trigger the appropriate handling.
5. Health checks. Regularly check the health of the external service using health checks. if the service is consistently unhealthy, it might be better to avoid making requests to it until it recovers.
6. Caching. Cache responses from the external service for a certain period. If the service is temporarily unavailable you can serve cached data to users or processes.
7. Monitoring and alerting. Implement a strategy for graceful degradation, where the application continues to function with reduced functionality if the external service is unavailable.
8. Graceful degradation. Implement a strategy for graceful degradation, where the app continues to function with reduced functionality if the external service is unavailable.
9. Fallback endpoints. Implement alternative, simplified, or degraded functionality when the primary service is unavailable. users or systems can then use the fallback endpoints to perform essential tasks.



import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker