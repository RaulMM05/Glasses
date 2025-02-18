# UsuarioApiApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**borrarRegistro**](UsuarioApiApi.md#borrarRegistro) | **DELETE** /usuario | Borrar registro |
| [**cambiarPassword**](UsuarioApiApi.md#cambiarPassword) | **PUT** /usuario/{id} | Cambiar contraseña |
| [**consultarAlumno**](UsuarioApiApi.md#consultarAlumno) | **GET** /usuario/alumno/{id} | Consultar alumno |
| [**consultarFecha**](UsuarioApiApi.md#consultarFecha) | **GET** /usuario/fecha | Consultar fecha |
| [**consultarRegistros**](UsuarioApiApi.md#consultarRegistros) | **GET** /usuario/{id} | Consultar registros |
| [**consultarTutor**](UsuarioApiApi.md#consultarTutor) | **GET** /usuario/tutor/{id} | Consultar tutor |
| [**crearRegistro**](UsuarioApiApi.md#crearRegistro) | **POST** /usuario | Crear registro |
| [**login**](UsuarioApiApi.md#login) | **GET** /usuario | Realizar login |


<a id="borrarRegistro"></a>
# **borrarRegistro**
> borrarRegistro(id)

Borrar registro

Borra un registro completo y lo elimina del total de registros de ese alumno

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UsuarioApiApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: Authorization
    ApiKeyAuth Authorization = (ApiKeyAuth) defaultClient.getAuthentication("Authorization");
    Authorization.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //Authorization.setApiKeyPrefix("Token");

    UsuarioApiApi apiInstance = new UsuarioApiApi(defaultClient);
    Long id = 56L; // Long | 
    try {
      apiInstance.borrarRegistro(id);
    } catch (ApiException e) {
      System.err.println("Exception when calling UsuarioApiApi#borrarRegistro");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **Long**|  | |

### Return type

null (empty response body)

### Authorization

[Authorization](../README.md#Authorization)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

<a id="cambiarPassword"></a>
# **cambiarPassword**
> Usuario cambiarPassword(id, changePasswordRequest)

Cambiar contraseña

Cambiar contraseña a partir de la ID del usuario y ambas contraseñas en el body

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UsuarioApiApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: Authorization
    ApiKeyAuth Authorization = (ApiKeyAuth) defaultClient.getAuthentication("Authorization");
    Authorization.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //Authorization.setApiKeyPrefix("Token");

    UsuarioApiApi apiInstance = new UsuarioApiApi(defaultClient);
    Long id = 56L; // Long | 
    ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest(); // ChangePasswordRequest | 
    try {
      Usuario result = apiInstance.cambiarPassword(id, changePasswordRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UsuarioApiApi#cambiarPassword");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **Long**|  | |
| **changePasswordRequest** | [**ChangePasswordRequest**](ChangePasswordRequest.md)|  | |

### Return type

[**Usuario**](Usuario.md)

### Authorization

[Authorization](../README.md#Authorization)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

<a id="consultarAlumno"></a>
# **consultarAlumno**
> Alumno consultarAlumno(id)

Consultar alumno

Consulta todos los datos de un alumno.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UsuarioApiApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: Authorization
    ApiKeyAuth Authorization = (ApiKeyAuth) defaultClient.getAuthentication("Authorization");
    Authorization.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //Authorization.setApiKeyPrefix("Token");

    UsuarioApiApi apiInstance = new UsuarioApiApi(defaultClient);
    Long id = 56L; // Long | 
    try {
      Alumno result = apiInstance.consultarAlumno(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UsuarioApiApi#consultarAlumno");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **Long**|  | |

### Return type

[**Alumno**](Alumno.md)

### Authorization

[Authorization](../README.md#Authorization)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

<a id="consultarFecha"></a>
# **consultarFecha**
> List&lt;Fecha&gt; consultarFecha()

Consultar fecha

Consulta la ID de la fecha que se recibe por parámetros.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UsuarioApiApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: Authorization
    ApiKeyAuth Authorization = (ApiKeyAuth) defaultClient.getAuthentication("Authorization");
    Authorization.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //Authorization.setApiKeyPrefix("Token");

    UsuarioApiApi apiInstance = new UsuarioApiApi(defaultClient);
    try {
      List<Fecha> result = apiInstance.consultarFecha();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UsuarioApiApi#consultarFecha");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;Fecha&gt;**](Fecha.md)

### Authorization

[Authorization](../README.md#Authorization)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

<a id="consultarRegistros"></a>
# **consultarRegistros**
> List&lt;Registro&gt; consultarRegistros(id, desde, hasta)

Consultar registros

Consulta todos los registros comprendidos entre dos fechas.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UsuarioApiApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: Authorization
    ApiKeyAuth Authorization = (ApiKeyAuth) defaultClient.getAuthentication("Authorization");
    Authorization.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //Authorization.setApiKeyPrefix("Token");

    UsuarioApiApi apiInstance = new UsuarioApiApi(defaultClient);
    Long id = 56L; // Long | 
    LocalDate desde = LocalDate.now(); // LocalDate | 
    LocalDate hasta = LocalDate.now(); // LocalDate | 
    try {
      List<Registro> result = apiInstance.consultarRegistros(id, desde, hasta);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UsuarioApiApi#consultarRegistros");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **Long**|  | |
| **desde** | **LocalDate**|  | [optional] |
| **hasta** | **LocalDate**|  | [optional] |

### Return type

[**List&lt;Registro&gt;**](Registro.md)

### Authorization

[Authorization](../README.md#Authorization)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

<a id="consultarTutor"></a>
# **consultarTutor**
> Tutor consultarTutor(id)

Consultar tutor

Consulta todos los datos de un tutor.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UsuarioApiApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: Authorization
    ApiKeyAuth Authorization = (ApiKeyAuth) defaultClient.getAuthentication("Authorization");
    Authorization.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //Authorization.setApiKeyPrefix("Token");

    UsuarioApiApi apiInstance = new UsuarioApiApi(defaultClient);
    Long id = 56L; // Long | 
    try {
      Tutor result = apiInstance.consultarTutor(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UsuarioApiApi#consultarTutor");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **Long**|  | |

### Return type

[**Tutor**](Tutor.md)

### Authorization

[Authorization](../README.md#Authorization)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

<a id="crearRegistro"></a>
# **crearRegistro**
> Registro crearRegistro(registroRequest)

Crear registro

Crear un registro completo y lo añade al total de registros de ese alumno

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UsuarioApiApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: Authorization
    ApiKeyAuth Authorization = (ApiKeyAuth) defaultClient.getAuthentication("Authorization");
    Authorization.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //Authorization.setApiKeyPrefix("Token");

    UsuarioApiApi apiInstance = new UsuarioApiApi(defaultClient);
    RegistroRequest registroRequest = new RegistroRequest(); // RegistroRequest | 
    try {
      Registro result = apiInstance.crearRegistro(registroRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UsuarioApiApi#crearRegistro");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **registroRequest** | [**RegistroRequest**](RegistroRequest.md)|  | |

### Return type

[**Registro**](Registro.md)

### Authorization

[Authorization](../README.md#Authorization)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

<a id="login"></a>
# **login**
> Usuario login(nombreUsuario, contraseña)

Realizar login

Realiza login en la plataforma a partir del nombre de usuario y su contraseña

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UsuarioApiApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: Authorization
    ApiKeyAuth Authorization = (ApiKeyAuth) defaultClient.getAuthentication("Authorization");
    Authorization.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //Authorization.setApiKeyPrefix("Token");

    UsuarioApiApi apiInstance = new UsuarioApiApi(defaultClient);
    String nombreUsuario = "nombreUsuario_example"; // String | 
    String contraseña = "contraseña_example"; // String | 
    try {
      Usuario result = apiInstance.login(nombreUsuario, contraseña);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UsuarioApiApi#login");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **nombreUsuario** | **String**|  | |
| **contraseña** | **String**|  | |

### Return type

[**Usuario**](Usuario.md)

### Authorization

[Authorization](../README.md#Authorization)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

