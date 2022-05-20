
# Exchange Api

This project is a currency exchange applicaiton.

Application contains;

 - In memory h2 database with pre populated data.
 - Exception handling mechanism
 - Response building mechanism
 - Lombok used for boilerplate codes
 - Dynamic entity to dto mapping paginated responses using generic type and reflection
 -
 - Integration with https://fixer.io/ for exchange rate values

Application have following APIs

## 1. Rate API
**Url:**  localhost:8080/api/rate
**Method:** GET
**Payload:**

    {
        "sourceCurrencyCode"  :  "TRY",
        "targetCurrencyCode"  :  "USD"
    }

**Response:**

    {
    	"returnCode":  0,
    	"returnMessage":  "The operation succeeded.",
    	"isPaginated":  false,
    	"content":  0.062644
    }


## 2. Exchange API
**Url:**  localhost:8080/api/exchange
**Method:** GET
**Payload:**

    {
	    "sourceCurrencyCode"  :  "USD",
	    "targetCurrencyCode"  :  "TRY",
	    "amount":  5
    }

**Response:**

    {
    "returnCode":  0,
    "returnMessage":  "The operation succeeded.",
    "isPaginated":  false,
    "content":  {
    		"calculatedAmount":  79.807775,
    		"transactionId":  "fa89703f-e47c-465d-a3c1-f76f0291546c"
    	}
    }

## 3. Symbols API
**Url:**  localhost:8080/api/symbols
**Method:** GET
**Payload:**

**Response:**

    {
    "returnCode":  0,
    "returnMessage":  "The operation succeeded.",
    "isPaginated":  false,
    "content":  {
		    "AED":  "United Arab Emirates Dirham",
		    "AFN":  "Afghan Afghani",
		    "ALL":  "Albanian Lek",
		    "AMD":  "Armenian Dram",
		    "ANG":  "Netherlands Antillean Guilder",
		    "AOA":  "Angolan Kwanza",
		    "ARS":  "Argentine Peso",
		    "AUD":  "Australian Dollar",
		    .
		    .
	    }
    }

## 4. Exchange History API
**Url:**  localhost:8080/api/exchange/history
**Method:** GET
**Payload:**

    {
    	"transactionDate"  :  "2022-03-10",
    }
OR

    {
    	"transactionId"  :  "transaction-9",
    }

**Response:**

    {
      "returnCode": 0,
      "totalItems": 5,
      "returnMessage": "The operation succeeded.",
      "totalPages": 1,
      "isPaginated": true,
      "currentPage": 0,
      "content": [
        {
          "sourceCurrency": "CHF",
          "targetCurrency": "BRL",
          "exchangeRate": null,
          "amount": null,
          "requestDate": "2022-05-10T21:00:00.000+00:00"
        },
        {
          "sourceCurrency": "KRW",
          "targetCurrency": "MXN",
          "exchangeRate": null,
          "amount": null,
          "requestDate": "2022-05-10T21:00:00.000+00:00"
        },
        {
          "sourceCurrency": "SYP",
          "targetCurrency": "CHF",
          "exchangeRate": null,
          "amount": null,
          "requestDate": "2022-05-10T21:00:00.000+00:00"
        },
        {
          "sourceCurrency": "USD",
          "targetCurrency": "KRW",
          "exchangeRate": null,
          "amount": null,
          "requestDate": "2022-05-11T21:00:00.000+00:00"
        },
        {
          "sourceCurrency": "USD",
          "targetCurrency": "TRY",
          "exchangeRate": null,
          "amount": null,
          "requestDate": "2022-05-20T06:48:07.620+00:00"
        }
      ]
    }
