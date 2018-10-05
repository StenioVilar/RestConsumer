
public class RestConsumer{

  private RestTemplate restTemplate;

  protected void initRestTemplate() throws Exception {
      if(restTemplate == null){
        restTemplate = new RestTemplate();
      }
  }
  
}
