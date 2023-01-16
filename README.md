TalentTech: XQA-TTNetwork
=====================

##### Lightweight http client with methods for testing. A simple test looks like this:
```kotlin
TTNetwork().getHttpClient().get("http://example.com/users")
    .shouldBe(
        Condition.codeEquals(200), 
        Condition.bodyParamEquals("count", 100)
)
```
