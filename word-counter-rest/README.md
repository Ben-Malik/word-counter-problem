## word-counter-rest

This a rest implementation of the problem using Spring boot.
This implementation does make a dependency injection of the `word-counter-core` project.

1. Find the api endpoints at `WordCounterController`.
2. Find the api endpoints with restricted api Key at `RestrictedWordCounterController`.
3. Run the integration tests of all the endpoints at `WordCounterControllerTest`

### WordCounterController

This is a simple rest controller just like word-counter-jakarta.WordCounterResource class;
but this time done in spring boot way.

### RestrictedWordCounterController

The rest controller is restricted and in order to send requests to its respective endpoints,
you need to specify the apiKey as a request param.

`apiKey: V7HZWp2eiPeoJkwtN3etyiWdsrh
`

### Example output of the endpoints via Postman

1. http://localhost:9090/restricted/api/most-n-word-frequencies/The sun shine over the lake of the
   mountains/6?apiKey=V7HZWp2eiPeoJkwtN3etyiWdsrh3

```
    {
    "message": "You're not authorised to carry this operation out.",
    "type": "UNAUTHORIZED",
    "statusCode": 401
    }
```

2. http://localhost:9090/restricted/api/most-n-word-frequencies/The sun shine over the lake of the
   mountains/6?apiKey=V7HZWp2eiPeoJkwtN3etyiWdsrh

```
[
    {
        "word": "the",
        "frequency": 3
    },
    {
        "word": "lake",
        "frequency": 1
    },
    {
        "word": "mountains",
        "frequency": 1
    }
]
```

http://localhost:9090/restricted/api/word-frequency/The sun shine over the lake of the
mountains/sun?apiKey=V7HZWp2eiPeoJkwtN3etyiWdsrh

```
{
    "word": "sun",
    "wordFrequency": 1,
    "type": "SUCCESS",
    "statusCode": 200
}
```

3. http://localhost:9090/restricted/api/word-frequency/The sun shine over the lake of the
   mountains/The?apiKey=V7HZWp2eiPeoJkwtN3etyiWdsrh

```
{
    "word": "The",
    "wordFrequency": 3,
    "type": "SUCCESS",
    "statusCode": 200
}
```

4. http://localhost:9090/restricted/api/highest-frequency/The sun shine over the lake of the
   mountains?apiKey=V7HZWp2eiPeoJkwtN3etyiWdsrh

```
{
    "highestFrequency": 3,
    "type": "SUCCESS",
    "statusCode": 200
}
```

5. http://localhost:9090/restricted/api/highest-frequency/ ?apiKey=V7HZWp2eiPeoJkwtN3etyiWdsrh

```
{
    "message": "The text you are attempting to analyze is empty.",
    "type": "BAD_REQUEST",
    "statusCode": 400
}
```